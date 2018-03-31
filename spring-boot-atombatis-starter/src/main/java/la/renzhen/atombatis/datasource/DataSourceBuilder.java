package la.renzhen.atombatis.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import la.renzhen.atombatis.spring.boot.AtomBatisProperties.DataSourceConfigure;
import lombok.SneakyThrows;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\27 0027 19:27
 */
public class DataSourceBuilder {

    private DataSourceConfigure def;
    private boolean enableJTA = false;
    private String name;

    private DataSourceBuilder(DataSourceConfigure def) {
        this.def = def;
        this.initDef();
    }

    private void initDef() {
        if (def == null) {
            def = new DataSourceConfigure();
            /*
             * 初始化,最小,最大链接数
             */
            def.setInitialSize(1);
            def.setMinIdle(1);
            def.setMaxActive(5);
            /*
             * 配置获取连接等待超时的时间
             */
            def.setMaxWait(TimeUnit.SECONDS.toMillis(5));
            /*
             * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
             */
            def.setTimeBetweenEvictionRunsMillis(TimeUnit.SECONDS.toMillis(60));
            /*
             * 配置一个连接在池中最小生存的时间，单位是毫秒
             */
            def.setMinEvictableIdleTimeMillis(300000L);
            def.setMaxEvictableIdleTimeMillis(300000L);

            //def.setPoolPreparedStatements(false);
            //def.setMaxOpenPreparedStatements();
            //def.setMaxPoolPreparedStatementPerConnectionSize();

            def.setValidationQuery("select 'x'");
            def.setValidationQueryTimeout(3000);

            def.setTestOnBorrow(false);
            def.setTestOnReturn(false);
            def.setTestWhileIdle(true);
        }
    }

    public static DataSourceBuilder create(DataSourceConfigure def) {
        return new DataSourceBuilder(def);
    }

    public DataSourceBuilder name(String name) {
        this.name = name;
        return this;
    }

    public DataSourceBuilder enableJTA(boolean enableJTA) {
        this.enableJTA = enableJTA;
        return this;
    }

    @SneakyThrows
    public DataSource build(DataSourceConfigure configure) {
        if (enableJTA) {
            AtomikosDataSourceBean bean = new AtomikosDataSourceBean();
            bean.setUniqueResourceName(this.name);
            bean.setDefaultIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
            DruidXADataSource ds = new DruidXADataSource();
            setAttr(configure,ds);
            bean.setXaDataSource(ds);
            return bean;
        } else {
            DruidDataSource dataSource = new DruidDataSource();
            setAttr(configure,dataSource);
            return dataSource;
        }
    }

    private void setAttr(DataSourceConfigure configure, DruidDataSource dataSource) {
        apply(configure, def, DataSourceConfigure::getUrl, dataSource::setUrl);
        apply(configure, def, DataSourceConfigure::getUsername, dataSource::setUsername);
        apply(configure, def, DataSourceConfigure::getUsername, dataSource::setUsername);
        apply(configure, def, DataSourceConfigure::getPassword, dataSource::setPassword);
        apply(configure, def, DataSourceConfigure::getDriverClassName, dataSource::setDriverClassName);
        apply(configure, def, DataSourceConfigure::getInitialSize, dataSource::setInitialSize);
        apply(configure, def, DataSourceConfigure::getMinIdle, dataSource::setMinIdle);
        apply(configure, def, DataSourceConfigure::getMaxActive, dataSource::setMaxActive);
        apply(configure, def, DataSourceConfigure::getMaxWait, dataSource::setMaxWait);
        apply(configure, def, DataSourceConfigure::getTimeBetweenEvictionRunsMillis, dataSource::setTimeBetweenEvictionRunsMillis);
        apply(configure, def, DataSourceConfigure::getMinEvictableIdleTimeMillis, dataSource::setMinEvictableIdleTimeMillis);
        apply(configure, def, DataSourceConfigure::getMaxEvictableIdleTimeMillis, dataSource::setMaxEvictableIdleTimeMillis);


        apply(configure, def, DataSourceConfigure::getValidationQuery, dataSource::setValidationQuery);
        apply(configure, def, DataSourceConfigure::getValidationQueryTimeout, dataSource::setValidationQueryTimeout);


        apply(configure, def, DataSourceConfigure::getTestOnBorrow, dataSource::setTestOnBorrow);
        apply(configure, def, DataSourceConfigure::getTestOnReturn, dataSource::setTestOnReturn);
        apply(configure, def, DataSourceConfigure::getTestWhileIdle, dataSource::setTestWhileIdle);

        apply(configure, def, DataSourceConfigure::getPoolPreparedStatements, dataSource::setPoolPreparedStatements);
        apply(configure, def, DataSourceConfigure::getMaxOpenPreparedStatements, dataSource::setMaxOpenPreparedStatements);
        apply(configure, def, DataSourceConfigure::getMaxPoolPreparedStatementPerConnectionSize, dataSource::setMaxPoolPreparedStatementPerConnectionSize);
    }

    private <T, R> void apply(T config, T def, Function<T, R> fn, Consumer<R> ifPresentConsumer) {
        R r = Optional.ofNullable(fn.apply(config)).orElse(fn.apply(def));
        if (r != null) {
            ifPresentConsumer.accept(r);
        }
    }
}
