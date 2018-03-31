package la.renzhen.atombatis.spring.boot;

import la.renzhen.atombatis.batis.RoutingSqlSessionFactory;
import la.renzhen.atombatis.datasource.DataSourceBuilder;
import la.renzhen.atombatis.datasource.RoutingDataSource;
import la.renzhen.atombatis.shards.ShardingStrategy;
import la.renzhen.atombatis.shards.StrategyManager;
import la.renzhen.atombatis.spring.boot.AtomBatisProperties.DataSourceConfigure;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\25 0025 16:20
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AtomBatisProperties.class)
@AutoConfigureBefore({DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
public class AtomBatisAutoConfiguration {

    @Autowired
    AtomBatisProperties atomBatis;

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("atombaits.databases")
    public RoutingDataSource dataSource() {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        boolean enableJTA = atomBatis.isEnableJta();
        for (Map.Entry<String, DataSourceConfigure> entry : atomBatis.databases.entrySet()) {
            String dataSourceName = entry.getKey();
            DataSource source = DataSourceBuilder.create(atomBatis.getDefaultDatabaseConfig())
                    .enableJTA(enableJTA).name(dataSourceName).build(entry.getValue());
            log.info("Init RoutingDataSource({} {})", dataSourceName, enableJTA ? "JTA" : "");
            targetDataSources.put(dataSourceName, source);
        }

        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(targetDataSources.entrySet().stream().findFirst().get().getValue());
        return routingDataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public RoutingSqlSessionFactory routingSqlSessionFactory(
            RoutingDataSource routingDataSource,
            MybatisProperties properties,
            ObjectProvider<Interceptor[]> interceptorsProvider,
            ResourceLoader resourceLoader,
            ObjectProvider<DatabaseIdProvider> databaseIdProvider,
            ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider
    ) throws Exception {
        MybatisAutoConfiguration configuration = new MybatisAutoConfiguration(properties, interceptorsProvider,
                resourceLoader, databaseIdProvider, configurationCustomizersProvider);


        Map<String,SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        for (Map.Entry<String, DataSource> entry : routingDataSource.getTarget().entrySet()) {
            //mybatis 会设置这个并保存
            properties.setConfiguration(null);

            String routingName = entry.getKey();
            DataSource dataSource = entry.getValue();
            SqlSessionFactory sessionFactory = configuration.sqlSessionFactory(dataSource);
            sqlSessionFactoryMap.put(routingName, sessionFactory);
        }
        return new RoutingSqlSessionFactory(sqlSessionFactoryMap);
    }

    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean
    public StrategyManager strategyManager(@Autowired ResourceLoader resourceLoader) {
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("/META-INF/atombatis/la.renzhen.atombatis.shards.ShardingStrategy");

        StrategyManager.Builder builder = StrategyManager.builder();
        for (Resource resource : resources) {
            @Cleanup InputStream input = resource.getInputStream();
            Properties prop = new Properties();
            prop.load(input);
            for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                String strategyKey = String.valueOf(entry.getKey());
                String strategyClass = String.valueOf(entry.getValue());
                builder.strategy(strategyKey, (Class<? extends ShardingStrategy>) Class.forName(strategyClass));
            }
        }
        return builder.build();
    }
}
