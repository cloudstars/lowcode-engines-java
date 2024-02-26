package net.cf.form.engine.def;

import net.cf.form.engine.def.field.FieldDef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型定义
 *
 * @author clouds
 */
public class ObjectDefinition {

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
    private final List<FieldDef> fields = new ArrayList<>();

    /**
     * 字段映射表
     */
    private final Map<String, FieldDef> fieldMap = new HashMap<>();

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

    public void addField(FieldDef field) {
        this.fields.add(field);
        this.fieldMap.put(field.getName(), field);
    }

}
