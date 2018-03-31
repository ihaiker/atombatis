package la.renzhen.atombatis.spring;

import la.renzhen.atombatis.batis.RoutingSqlSessionFactory;
import la.renzhen.atombatis.datasource.ContextHolder;
import la.renzhen.atombatis.shards.ShardException;
import la.renzhen.atombatis.shards.ShardingStrategy;
import la.renzhen.atombatis.shards.StrategyManager;
import la.renzhen.atombatis.spring.boot.AtomBatisProperties;
import la.renzhen.db.atombatis.ShardParam;
import la.renzhen.db.atombatis.ShardSelect;
import la.renzhen.db.atombatis.TableSubfix;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\21 0021 18:21
 */
@Slf4j
public class AtomBatisMapperFactoryBean<S, M> implements FactoryBean<M>, InitializingBean {
    @Setter
    @Getter
    Class<M> mapperInterface;

    ShardingStrategy<S> databaseShards;
    ShardingStrategy<S> tableShards;

    @Autowired
    RoutingSqlSessionFactory sqlSession;

    @Autowired
    AtomBatisProperties config;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StrategyManager strategyManager;

    Map<String, M> mapperMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.databaseShards();
        this.tableShards();
        this.mappers();
    }

    private void mappers() {
        mapperMap = new HashMap<>();
        for (Map.Entry<String, SqlSessionFactory> entry : sqlSession.getTarget().entrySet()) {
            String name = entry.getKey();
            SqlSessionFactory sessionFactory = entry.getValue();
            mapperMap.put(name, sessionFactory.openSession().getMapper(mapperInterface));
        }
    }

    private void databaseShards() {
        if (databaseShards == null) {
            String mapperName = mapperInterface.getSimpleName();
            databaseShards = (ShardingStrategy<S>) strategyManager.getDatabase(mapperName);
        }

        if (databaseShards instanceof DataSourcesAware) {
            List<String> shards = config.getDatabases().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
            ((DataSourcesAware) databaseShards).setShards(shards);
        }
        databaseShards.init();
    }

    private void tableShards() {
        if (tableShards == null) {
            String mapperName = mapperInterface.getSimpleName();
            tableShards = (ShardingStrategy<S>) strategyManager.getTable(mapperName);
        }
        if (tableShards != null) {
            tableShards.init();
        }
    }

    @Override
    public M getObject() throws Exception {
        return (M) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{this.mapperInterface}, (self, method, paramters) -> {

            if (method.getName().equals("toString")) {
                return mapperInterface.getSimpleName() + " in atombatis";
            } else if (method.getName().equals("equals")) {
                return false;
            } else if (method.getName().equals("getClass")) {
                return mapperInterface;
            }

            List<S> partitions = specifyPartition(method, paramters);
            List<String> databases = runShards(partitions, databaseShards);
            List<String> tables = tableShards == null ? Collections.emptyList() : runShards(partitions, tableShards);
            if (databases.size() > 1 || tables.size() > 1) {
                if (method.getReturnType().equals(Integer.TYPE)) {
                    int ret = 0;
                    for (String database : databases) {
                        M mapper = mapperMap.get(database);
                        log.debug("atombatis mapper {} select db {}", mapperInterface, database);
                        if (tables.isEmpty()) {
                            ret += (Integer) method.invoke(mapper, paramters);
                        } else {
                            for (String table : tables) {
                                log.debug("atombatis mapper {} select db:{}, table:{}", mapperInterface, database, table);
                                setTableSubfixParamter(paramters, table);
                                ret += (Integer) method.invoke(mapper, paramters);
                            }
                        }
                    }
                    return ret;
                } else if (method.getReturnType().isAssignableFrom(List.class)) {
                    List ret = new ArrayList();
                    for (String database : databases) {
                        M mapper = mapperMap.get(database);
                        log.debug("atombatis mapper {} select db {}", mapperInterface, database);
                        if (tables.isEmpty()) {
                            ret.addAll((List) method.invoke(mapper, paramters));
                        } else {
                            for (String table : tables) {
                                log.debug("atombatis mapper {} select db:{}, table:{}", mapperInterface, database, table);
                                setTableSubfixParamter(paramters, table);
                                ret.addAll((List) method.invoke(mapper, paramters));
                            }
                        }
                    }
                    return ret;
                } else {
                    throw new ShardException(method);
                }
            } else {
                String database = databases.get(0);
                M mapper = mapperMap.get(database);
                log.debug("atombatis mapper {} select db:{}, table:{}", mapperInterface, database, tables.size() > 0 ? tables.get(0) : "none");
                if (!tables.isEmpty()) {
                    setTableSubfixParamter(paramters, tables.get(0));
                }
                return method.invoke(mapper, paramters);
            }
        });
    }

    /**
     * 设置表后缀
     *
     * @param paramters
     * @param table
     */
    private void setTableSubfixParamter(Object[] paramters, String table) {
        for (Object paramter : paramters) {
            if (paramter != null && paramter instanceof TableSubfix) {
                ((TableSubfix) paramter).setTableSubfix(table);
            }
        }
    }

    /**
     * 获取分区字段
     *
     * @param method
     * @param parameters
     * @return
     */
    private List<S> specifyPartition(Method method, Object[] parameters) {
        List<S> shardings = new ArrayList<>();
        for (Object paramter : parameters) {
            if (paramter == null) {
                continue;
            }
            if (paramter instanceof ShardSelect) {
                List<S> ssf = ((ShardSelect<S>) paramter).getABSlave();
                if (!ssf.isEmpty()) {
                    shardings.addAll(ssf);
                }
            }
        }
        Parameter[] methodParameters = method.getParameters();
        for (int i = 0; i < methodParameters.length; i++) {
            Parameter parameter = methodParameters[i];
            if (parameter.getAnnotation(ShardParam.class) != null) {
                if (parameters[i] != null) {
                    shardings.add((S) parameters[i]);
                }
            }
        }
        return shardings;
    }

    /**
     * 获取分区运行区间
     *
     * @param shardings
     * @param shardingStrategy
     * @return
     */
    private List<String> runShards(List<S> shardings, ShardingStrategy<S> shardingStrategy) {
        //不指定就是全部
        if (shardings.isEmpty()) {
            return shardingStrategy.routers();
        }
        List<String> runShardDatabase = new ArrayList<>();
        for (S sharding : shardings) {
            runShardDatabase.add(shardingStrategy.router(sharding));
        }
        return runShardDatabase.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
