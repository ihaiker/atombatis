package la.renzhen.atombatis.test.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import la.renzhen.atombatis.datasource.DataSourceBuilder;
import la.renzhen.atombatis.datasource.RoutingDataSource;
import la.renzhen.atombatis.spring.boot.AtomBatisProperties;
import lombok.SneakyThrows;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\29 0029 19:29
 */
@Configuration
public class DataSourceConfig {

  /*  @Autowired
    AtomBatisProperties atombatis;

    @Bean("db1")
    public DataSource dataSourceDB1() {
        String name = "db1";
        return DataSourceBuilder
                .create(atombatis.getDefaultDatabaseConfig())
                .enableJTA(atombatis.isEnableJta()).name(name)
                .build(atombatis.getDatabases().get(name));
    }

    @Bean("db2")
    public DataSource dataSourceDB2() {
        String name = "db2";
        return DataSourceBuilder
                .create(atombatis.getDefaultDatabaseConfig())
                .enableJTA(atombatis.isEnableJta()).name(name)
                .build(atombatis.getDatabases().get(name));
    }

    @Bean
    @Primary
    public DataSource dataSource(
            @Autowired @Qualifier("db1") DataSource dataSource1,
            @Autowired @Qualifier("db1") DataSource dataSource2
    ) {
        RoutingDataSource dataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("db1", dataSource1);
        targetDataSources.put("db2", dataSource2);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(targetDataSources.entrySet().stream().findFirst().get().getValue());
        return dataSource;
    }*/


  /*  @Bean
    public Interceptor interceptor() {
        return new DynamicPlugin();
    }*/


/*
    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager manager = new UserTransactionManager();
        manager.setForceShutdown(true);
        return manager;
    }

    @Bean
    @SneakyThrows
    public UserTransaction userTransaction() {
        UserTransaction userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(3);
        return userTransaction;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            @Autowired UserTransactionManager userTransactionManager,
            @Autowired UserTransaction userTransaction
    ) {
        return new JtaTransactionManager(userTransaction,userTransactionManager);
    }*/
}