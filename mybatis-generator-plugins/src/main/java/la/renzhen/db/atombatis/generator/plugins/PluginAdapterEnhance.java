package la.renzhen.db.atombatis.generator.plugins;

import com.google.common.base.CaseFormat;
import lombok.NonNull;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**
 * PluginAdapter的扩展类
 * <p>
 * <pre>
 * 主要包括了:
 * -获取系统分割符
 * -添加字段同时也添加get,set方法
 * </pre>
 *
 * @author Patrick
 */
public abstract class PluginAdapterEnhance extends PluginAdapter {

    /**
     * 取消验证
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 添加字段，同时也添加get,set方法
     *
     * @param topLevelClass
     * @param introspectedTable
     * @param field
     */
    protected void addField(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, Field field) {
        String fieldName = field.getName();
        // 添加Java字段
        topLevelClass.addField(field);

        // 生成Set方法
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + MethodName(fieldName));
        method.addParameter(new Parameter(field.getType(), fieldName));
        method.addBodyLine("this." + fieldName + "=" + fieldName + ";");
        topLevelClass.addMethod(method);

        // 生成Get方法
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName("get" + MethodName(fieldName));
        method.addBodyLine("return " + fieldName + ";");
        topLevelClass.addMethod(method);
    }

    protected static String MethodName(@NonNull String fieldName) {
        if (fieldName.indexOf("_") > -1) {
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, fieldName);
        } else {
            return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,fieldName);
        }
    }
}