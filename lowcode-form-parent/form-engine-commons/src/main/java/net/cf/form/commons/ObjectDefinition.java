package net.cf.form.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象定义
 *
 * @author clouds
 */
public class ObjectDefinition {

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

    public List<FieldDefinition> getFields() {
        return fields;
    }

    public void addField(FieldDefinition field) {
        this.fields.add(field);
    }
}
