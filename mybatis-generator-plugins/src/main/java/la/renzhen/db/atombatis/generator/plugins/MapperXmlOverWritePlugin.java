package la.renzhen.db.atombatis.generator.plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;

/**
 * 自动覆盖XML设置<p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2017/11/12 下午8:37
 */
public class MapperXmlOverWritePlugin extends PluginAdapterEnhance {
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            java.lang.reflect.Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception e) {
        }
        return true;
    }
}
