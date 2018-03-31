package la.renzhen.atombatis.test;

import com.mysql.jdbc.Driver;
import la.renzhen.atombatis.plugins.AtomBatisSamplePlugin;
import la.renzhen.db.atombatis.generator.IBatisGenerateTools;
import la.renzhen.db.atombatis.generator.plugins.*;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.config.JDBCConnectionConfiguration;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\19 0019 19:28
 */
public class TestGenerator extends IBatisGenerateTools {

    @Before
    public void init() {
        String host = System.getProperty("db1.address");
        String password = System.getProperty("db1.password");

        JDBCConnectionConfiguration configuration = new JDBCConnectionConfiguration();
        configuration.setDriverClass(Driver.class.getName());
        configuration.setConnectionURL("jdbc:mysql://" + host + "/test?characterEncoding=UTF8");
        configuration.setUserId("root");
        configuration.setPassword(password);
        setJdbcConnectionConfiguration(configuration);

        setModule("la.renzhen.atombatis.test");
        setModulePath("src/test/java");
        setMapperXMLPath("src/test/resources");
        setMapperPath("src/test/java");
    }

    @Test
    public void testGenerator() {
        addPlugin(TableSubfixPlugin.class);
        addPlugin(MapperXmlOverWritePlugin.class);
        addPlugin(MapperAnnotationPlugin.class);
        addPlugin(OffsetLimitPlugin.class);
        addPlugin(AtomBatisSamplePlugin.class);

        GenerateTable("account_bill", tableConfiguration -> {
            tableConfiguration.addProperty("shard.column","user_id");
        });
    }
}