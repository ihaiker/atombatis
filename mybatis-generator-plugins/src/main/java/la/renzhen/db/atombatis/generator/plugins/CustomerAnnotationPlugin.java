package la.renzhen.db.atombatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * Mapper添加自定义注解
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 */
public class CustomerAnnotationPlugin extends PluginAdapterEnhance {

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String annotationName = introspectedTable.getTableConfigurationProperty("annotation.name");
        if (StringUtility.stringHasValue(annotationName)) {
            String annotationContext = introspectedTable.getTableConfigurationProperty("annotation.content");
            if (!StringUtility.stringHasValue(annotationContext)) {
                annotationContext = "@" + annotationName.substring(annotationName.lastIndexOf(".") + 1);
            }
            interfaze.addAnnotation(annotationContext);
            interfaze.addImportedType(new FullyQualifiedJavaType(annotationName));
        }
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }
}