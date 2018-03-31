package la.renzhen.db.atombatis.generator;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import la.renzhen.db.atombatis.generator.plugins.SqlCommentGenerator;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.ibatis.type.EnumTypeHandler;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.plugins.SerializablePlugin;
import org.mybatis.generator.plugins.ToStringPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 自动生成mapper工具类
 */
@Data
public class IBatisGenerateTools {

    private String module, database;
    private JDBCConnectionConfiguration jdbcConnectionConfiguration;

    List<Class<? extends Plugin>> plugins = new ArrayList<Class<? extends Plugin>>() {{
        add(SerializablePlugin.class);
        add(ToStringPlugin.class);
    }};

    public String modulePath = "src/main/java";
    public String mapperXMLPath = "src/main/resources";
    public String mapperPath = "src/main/java";

    public void addPlugin(Class<? extends Plugin> plugin) {
        plugins.add(plugin);
    }

    public JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {
        JavaModelGeneratorConfiguration configuration = new JavaModelGeneratorConfiguration();
        configuration.setTargetPackage(String.format("%s.entry", getModule()));
        configuration.setTargetProject(getModulePath());
        configuration.addProperty("enableSubPackages", "true");
        configuration.addProperty("trimStrings", "true");
        return configuration;
    }

    public SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration() {
        SqlMapGeneratorConfiguration configuration = new SqlMapGeneratorConfiguration();
        configuration.setTargetPackage(String.format("%s.mapper", getModule()));
        configuration.setTargetProject(getMapperXMLPath());
        configuration.addProperty("enableSubPackages", "true");
        return configuration;
    }

    public JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration() {
        JavaClientGeneratorConfiguration configuration = new JavaClientGeneratorConfiguration();
        configuration.setTargetPackage(String.format("%s.mapper", getModule()));
        configuration.setTargetProject(getMapperPath());
        configuration.setConfigurationType("XMLMAPPER");
        configuration.addProperty("enableSubPackages", "true");
        return configuration;
    }

    public JavaTypeResolverConfiguration getJavaTypeResolverConfiguration() {
        JavaTypeResolverConfiguration configuration = new JavaTypeResolverConfiguration();
        configuration.addProperty("forceBigDecimals", "false");
        return configuration;
    }

    @SneakyThrows
    public void GenerateTable(String table, Consumer<TableConfiguration> consumer) {
        generate(context -> {
            String javaName = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert(table);
            TableConfiguration tableConfiguration = new TableConfiguration(context);
            String database = getDatabase();
            if (database != null) {
                tableConfiguration.setSchema(database);
            }
            tableConfiguration.setTableName(table);
            tableConfiguration.setDomainObjectName(javaName);
            consumer.accept(tableConfiguration);
            context.addTableConfiguration(tableConfiguration);
        });
    }

    @SneakyThrows
    public void GenerateTables(String... tables) {
        generate(context -> {
            for (String table : tables) {
                String javaName = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert(table);
                TableConfiguration tableConfiguration = new TableConfiguration(context);
                String database = getDatabase();
                if (database != null) {
                    tableConfiguration.setSchema(database);
                }
                tableConfiguration.setTableName(table);
                tableConfiguration.setDomainObjectName(javaName);
                context.addTableConfiguration(tableConfiguration);
            }
        });
    }

    public ColumnOverride enumColumnOverwrite(String column, Class<? extends Enum> enumClass, Class<? extends EnumTypeHandler> typeHandler) {
        ColumnOverride columnOverride = new ColumnOverride(column);
        columnOverride.setJavaType(enumClass.getName());
        columnOverride.setTypeHandler(typeHandler.getName());
        return columnOverride;
    }

    /**
     * 设置Entry列的上父类
     *
     * @param configuration
     * @param rootClass
     */
    public void setRootClass(TableConfiguration configuration, Class rootClass) {
        configuration.addProperty("rootClass", rootClass.getName());
    }

    /**
     * 设置mapper的上层类
     *
     * @param configuration
     * @param rootInterface
     */
    public void setRootInterface(TableConfiguration configuration, Class rootInterface) {
        assert rootInterface.isInterface();
        configuration.addProperty("rootInterface", rootInterface.getName());
    }

    @SneakyThrows
    protected void generate(Consumer<Context> consumer) {
        List<String> warnings = new ArrayList<>();
        Configuration config = new Configuration();

        Context context = new Context(ModelType.FLAT);
        context.setId("DB2Tables");
        context.setTargetRuntime("MyBatis3");

        for (Class<? extends Plugin> plugin : plugins) {
            PluginConfiguration configuration = new PluginConfiguration();
            configuration.setConfigurationType(plugin.getName());
            context.addPluginConfiguration(configuration);
        }
        config.addContext(context);

        context.addProperty("javaFileEncoding", "UTF-8");

        context.setJdbcConnectionConfiguration(getJdbcConnectionConfiguration());
        context.setJavaModelGeneratorConfiguration(getJavaModelGeneratorConfiguration());
        context.setSqlMapGeneratorConfiguration(getSqlMapGeneratorConfiguration());
        context.setJavaClientGeneratorConfiguration(getJavaClientGeneratorConfiguration());
        context.setJavaTypeResolverConfiguration(getJavaTypeResolverConfiguration());

        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType(SqlCommentGenerator.class.getName());
        commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        consumer.accept(context);

        DefaultShellCallback callback = new DefaultShellCallback(true);
        new MyBatisGenerator(config, callback, warnings).generate(null);
        warnings.forEach(System.out::println);
    }
}