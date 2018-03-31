package la.renzhen.atombatis.spring.boot;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\23 0023 20:08
 */
@Data
@ToString
@ConfigurationProperties("atombatis")
public class AtomBatisProperties {

    @Data
    public static class DataSourceConfigure {
        /**
         * 数据库地址
         */
        String url;

        /**
         * 用户名，如果配置里此项可以不再分库数据源上单独配置i
         */
        String username;

        /**
         * 数据库密码，同用户名
         */
        String password;

        /**
         * 数据库URL
         */
        String driverClassName;

        /**
         * 初始化,最小,最大链接数
         */
        Integer initialSize, minIdle, maxActive;

        /**
         * 配置获取连接等待超时的时间
         */
        Long maxWait;

        /**
         * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
         */
        Long timeBetweenEvictionRunsMillis;

        /**
         * 配置一个连接在池中最小生存的时间，单位是毫秒
         */
        Long minEvictableIdleTimeMillis;
        Long maxEvictableIdleTimeMillis;

        Boolean poolPreparedStatements;
        Integer maxOpenPreparedStatements;
        Integer maxPoolPreparedStatementPerConnectionSize;

        String validationQuery;

        Integer validationQueryTimeout;

        Boolean testOnBorrow;

        Boolean testOnReturn;

        Boolean testWhileIdle;
    }

    @Data
    public static class Strategy {
        String database;
        String databaseRef;
        Map<String, Object> databaseParameter;

        String table;
        String tableRef;
        Map<String, Object> tableParameter;
    }

    boolean enableJta = false;

    Strategy strategyDef;

    Map<String, Strategy> strategy = new LinkedHashMap<>();

    String scanMapper;

    Map<String, DataSourceConfigure> databases = new LinkedHashMap<>();

    DataSourceConfigure defaultDatabaseConfig;
}
