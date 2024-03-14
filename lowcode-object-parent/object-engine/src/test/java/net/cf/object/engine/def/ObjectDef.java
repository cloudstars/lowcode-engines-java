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
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 主键字段编号
     */
    private String primaryFieldCode;

    /**
     * 字段列表
     */
    private List<FieldDef> fields;

    /**
     * 字段映射表
     */
    // private final Map<String, FieldDef> fieldMap = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTableName() {
        return tableName != null ? this.tableName : this.code;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryFieldCode() {
        return primaryFieldCode;
    }

    public void setPrimaryFieldCode(String primaryFieldCode) {
        this.primaryFieldCode = primaryFieldCode;
    }

    public List<FieldDef> getFields() {
        return fields;
    }

    public void setFields(List<FieldDef> fields) {
        this.fields = fields;
        /*if (this.fieldMap.size() > 0) {
            this.fieldMap.clear();
        }
        for (FieldDef field : fields) {
            this.fieldMap.put(field.getCode(), field);
        }*/
    }

    /*public Map<String, FieldDef> getFieldMap() {
        return fieldMap;
    }*/
}
