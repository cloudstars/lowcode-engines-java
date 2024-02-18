package net.cf.form.engine.def;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象定义
 *
 * @author clouds
 */
public class FormDefinition {

    /**
     * 名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 主键字段
     */
    private String primaryFieldName;

    /**
     * 是否自动生成的主键
     */
    private boolean isAutoPrimaryField;

    /**
     * 字段映射表
     */
    private final Map<String, FieldDefinition> fieldMap = new HashMap<>();

    /**
     * 字段列表
     */
    private final List<FieldDefinition> fields = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryFieldName() {
        return primaryFieldName;
    }

    public void setPrimaryFieldName(String primaryFieldName) {
        this.primaryFieldName = primaryFieldName;
    }

    public boolean isAutoPrimaryField() {
        return isAutoPrimaryField;
    }

    public void setAutoPrimaryField(boolean autoPrimaryField) {
        isAutoPrimaryField = autoPrimaryField;
    }

    public List<FieldDefinition> getFields() {
        return fields;
    }

    public void addField(FieldDefinition field) {
        this.fields.add(field);
        this.fieldMap.put(field.getName(), field);
    }


}
