package la.renzhen.db.atombatis.generator.plugins;

import la.renzhen.db.atombatis.OffsetLimit;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * offset, limit 插件 <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\22 0022 10:59
 */
public class OffsetLimitPlugin extends PluginAdapterEnhance {

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType(OffsetLimit.class.getName());

        addField(topLevelClass, introspectedTable,
                new Field("offset", new FullyQualifiedJavaType("java.lang.Integer")));
        addField(topLevelClass, introspectedTable,
                new Field("limit", new FullyQualifiedJavaType("java.lang.Integer")));

        topLevelClass.addSuperInterface(new FullyQualifiedJavaType(OffsetLimit.class.getName()));
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return insertOffsetLimitElement(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return insertOffsetLimitElement(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return insertOffsetLimitElement(element, introspectedTable);
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return insertOffsetLimitElement(element, introspectedTable);
    }

    private boolean insertOffsetLimitElement(XmlElement element, IntrospectedTable table) {
        XmlElement limitIf = new XmlElement("if");
        limitIf.addAttribute(new Attribute("test", "limit != null"));
        limitIf.addElement(new TextElement("limit "));
        {
            XmlElement offsetIf = new XmlElement("if");
            offsetIf.addAttribute(new Attribute("test", "offset != null"));
            offsetIf.addElement(new TextElement("${offset},"));
            limitIf.addElement(offsetIf);
        }
        limitIf.addElement(new TextElement("${limit}"));
        element.addElement(limitIf);
        return true;
    }
}