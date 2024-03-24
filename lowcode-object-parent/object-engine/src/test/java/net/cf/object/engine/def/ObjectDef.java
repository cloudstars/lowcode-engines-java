package net.cf.object.engine.def;

import net.cf.object.engine.def.field.FieldDef;

import java.util.List;

/**
 * 模型定义
 *
 * @author clouds
 */
public class ObjectDef {

    /**
     * 描述
     */
    private String desc;

    /**
     * 名称
     */
    private String name;

    /**
     * 父模型的名称
     */
    private String masterName;

    /**
     * 表的名称
     */
    private String tableName;

    /**
     * 主键字段名称
     */
    private String primaryFieldName;

    /**
     * 字段列表
     */
    private List<FieldDef> fields;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getTableName() {
        return tableName != null ? this.tableName : this.name;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryFieldName() {
        return primaryFieldName;
    }

    public void setPrimaryFieldName(String primaryFieldName) {
        this.primaryFieldName = primaryFieldName;
    }

    public List<FieldDef> getFields() {
        return fields;
    }

    public void setFields(List<FieldDef> fields) {
        this.fields = fields;
    }
}
