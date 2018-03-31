package la.renzhen.db.atombatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * Mapper自动添加@Mapper注释
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 */
public class MapperAnnotationPlugin extends PluginAdapterEnhance {
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addAnnotation("@Mapper");
        interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

}