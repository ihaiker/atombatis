package la.renzhen.atombatis.plugins;

import la.renzhen.db.atombatis.ShardParam;
import la.renzhen.db.atombatis.ShardSelect;
import la.renzhen.db.atombatis.generator.plugins.PluginAdapterEnhance;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\20 0020 17:50
 */
public class AtomBatisSamplePlugin extends PluginAdapterEnhance {

    static class Zone {
        String slaveFieldType;
        String slaveFieldColumn;
        String slaveJavaFiledName;
        Field slaveClassField;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        String slaveFileColumn = introspectedTable.getTableConfigurationProperty("shard.column");
        for (Parameter parameter : method.getParameters()) {
            if (slaveFileColumn != null && parameter.getName().equals(slaveFileColumn.trim())) {
                parameter.addAnnotation(ShardParam.class.getName());
            }
        }
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Zone zone = getZoneField(topLevelClass, introspectedTable);
        if (zone == null) {
            return true;
        }

        topLevelClass.addImportedType(ArrayList.class.getName());
        topLevelClass.addImportedType(List.class.getName());
        topLevelClass.addImportedType(ShardSelect.class.getName());
        for (Method method : topLevelClass.getMethods()) {
            if (method.getName().equals("set" + MethodName(zone.slaveFieldColumn))) {
                method.addBodyLine("this.a_b_slave.add(" + method.getParameters().get(0).getName() + ");");
            }
        }
        return true;
    }

    /**
     * 获取分区字段
     */
    private Zone getZoneField(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String slaveFileColumn = introspectedTable.getTableConfigurationProperty("shard.column");
        if (slaveFileColumn == null) {
            return null;
        }
        IntrospectedColumn tableColumn = introspectedTable.getColumn(slaveFileColumn);
        String slaveFieldType = tableColumn.getFullyQualifiedJavaType().getFullyQualifiedName();

        FullyQualifiedJavaType javaType = new FullyQualifiedJavaType("List");
        javaType.addTypeArgument(new FullyQualifiedJavaType(slaveFieldType));
        Field field = new Field("a_b_slave", javaType);
        field.setInitializationString("new ArrayList<" + slaveFieldType + ">()");
        addField(topLevelClass, introspectedTable, field);

        FullyQualifiedJavaType slaveSelectInter = new FullyQualifiedJavaType(ShardSelect.class.getName());
        slaveSelectInter.addTypeArgument(new FullyQualifiedJavaType(slaveFieldType));
        topLevelClass.addSuperInterface(slaveSelectInter);

        Zone zone = new Zone();
        zone.slaveFieldType = slaveFieldType;
        zone.slaveFieldColumn = slaveFileColumn;
        zone.slaveClassField = field;
        zone.slaveJavaFiledName = tableColumn.getJavaProperty();
        return zone;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Zone zone = getZoneField(topLevelClass, introspectedTable);
        if (zone == null) {
            return true;
        }

        topLevelClass.addImportedType(ArrayList.class.getName());
        topLevelClass.addImportedType(List.class.getName());
        topLevelClass.addImportedType(ShardSelect.class.getName());

        //Criteria 类添加sample字段
        for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
            if (innerClass.getType().getShortName().equals("GeneratedCriteria")) {
                innerClass.addField(new Field("a_b_sample", topLevelClass.getType()));

                Method addSlave = new Method("addSlave");
                addSlave.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), "property"));
                addSlave.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.Object"), "value"));
                addSlave.addBodyLine("if(property.equals(\"" + zone.slaveJavaFiledName + "\")){");
                addSlave.addBodyLine("  List list = new ArrayList();");
                addSlave.addBodyLine("  if(value instanceof List){");
                addSlave.addBodyLine("    list.addAll((List)value);");
                addSlave.addBodyLine("  }else{");
                addSlave.addBodyLine("      list.add(value);");
                addSlave.addBodyLine("  }");
                addSlave.addBodyLine("  for (Object o : list) {");
                addSlave.addBodyLine("      a_b_sample.a_b_slave.add((" + zone.slaveFieldType + ")o);");
                addSlave.addBodyLine("  }");
                addSlave.addBodyLine("}");
                innerClass.addMethod(addSlave);

                for (Method method : innerClass.getMethods()) {
                    if (method.getName().equals("addCriterion") && method.getParameters().size() > 2) {
                        if (method.getParameters().size() == 3) {
                            method.addBodyLine(String.format("%s(%s,%s);", addSlave.getName(),
                                    method.getParameters().get(2).getName(),
                                    method.getParameters().get(1).getName()));
                        } else {
                            method.addBodyLine(String.format("%s(%s,%s);", addSlave.getName(),
                                    method.getParameters().get(3).getName(),
                                    method.getParameters().get(1).getName()));
                            method.addBodyLine(String.format("%s(%s,%s);", addSlave.getName(),
                                    method.getParameters().get(3).getName(),
                                    method.getParameters().get(2).getName()));
                        }
                    }
                }
            }
        }
        for (Method method : topLevelClass.getMethods()) {
            if (method.getName().equals("createCriteriaInternal")) {
                method.addBodyLine(1, "criteria.a_b_sample = this;");
            }
        }
        return true;
    }

}