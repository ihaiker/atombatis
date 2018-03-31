package la.renzhen.atombatis.shards;

import la.renzhen.atombatis.spring.boot.AtomBatisProperties;
import la.renzhen.atombatis.spring.boot.AtomBatisProperties.Strategy;
import lombok.Builder;
import lombok.Singular;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * 策略管理器<p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\28 19:53
 */
@Slf4j
@Builder(builderClassName = "Builder")
public class StrategyManager {

    @Autowired
    AtomBatisProperties config;

    @Autowired
    ApplicationContext application;

    @Singular
    Map<String, Class<? extends ShardingStrategy>> strategies;

    public ShardingStrategy<?> getDatabase(String mapperName) {
        return get(mapperName, "database", Strategy::getDatabase, Strategy::getDatabaseRef, Strategy::getDatabaseParameter);
    }

    public ShardingStrategy<?> getTable(String mapperName) {
        return get(mapperName, "table", Strategy::getTable, Strategy::getTableRef, Strategy::getTableParameter);
    }

    @SneakyThrows
    private void setAttr(ShardingStrategy strategy, Map<String, Object> attr) {
        MutablePropertyValues values = new MutablePropertyValues();
        attr.entrySet().forEach(entry -> {
            values.addPropertyValue(entry.getKey(), entry.getValue());
        });
        DataBinder binder = new DataBinder(strategy);
        binder.bind(values);
        binder.close();
    }

    private ShardingStrategy strategyByName(String strategyName) {
        Class<? extends ShardingStrategy> strategyClass = strategies.get(strategyName);
        if (strategyClass == null) {
            throw new NullPointerException("can't found strategy named " + strategyName);
        }
        return BeanUtils.instantiate(strategyClass);
    }


    private ShardingStrategy get(String mapperName, String forName,
                                 Function<Strategy, String> nameFn, Function<Strategy, String> refFn, Function<Strategy, Map<String, Object>> attrFn
    ) {
        Strategy defStrategy = config.getStrategyDef();
        Strategy configStrategy = config.getStrategy().get(mapperName);

        if (configStrategy == null) {
            log.debug("{} can't found config {} strategy", mapperName, forName);
        } else {
            //defined ref
            String strategyRef = refFn.apply(configStrategy);
            if (StringUtils.hasText(strategyRef)) {
                return application.getBean(strategyRef, ShardingStrategy.class);
            }
            String strategyName = nameFn.apply(configStrategy);
            if ("none".equals(strategyName)) {
                return null;
            }
            if (StringUtils.hasText(strategyName)) {
                ShardingStrategy strategy = strategyByName(strategyName);
                Map<String, Object> parameters = attrFn.apply(configStrategy);
                if (parameters != null && !parameters.isEmpty()) {
                    setAttr(strategy, parameters);
                } else if (defStrategy != null && strategyName.equals(nameFn.apply(defStrategy))) {
                    setAttr(strategy, Optional.ofNullable(attrFn.apply(defStrategy)).orElse(Collections.emptyMap()));
                }
                return strategy;
            }
        }
        log.debug("{} load default config.", mapperName);
        if (defStrategy == null) {
            log.debug("{} cant find any strategy {} config.", forName);
        } else {
            //默认配置
            String strategyRef = refFn.apply(defStrategy);
            if (StringUtils.hasText(strategyRef)) {
                return application.getBean(strategyRef, ShardingStrategy.class);
            }
            String strategyName = nameFn.apply(defStrategy);
            if ("none".equals(strategyName)) {
                return null;
            }
            if (StringUtils.hasText(strategyName)) {
                ShardingStrategy strategy = strategyByName(strategyName);
                Map<String, Object> parameters = attrFn.apply(defStrategy);
                if (parameters != null && !parameters.isEmpty()) {
                    setAttr(strategy, parameters);
                }
                return strategy;
            }
        }
        throw new NullPointerException("can't found any " + forName + " strategy for " + mapperName);
    }
}
