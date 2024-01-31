package net.cf.form.engine.repository.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据对象
 *
 * @author clouds
 */
@Deprecated
public class DataObject {

    /**
     * 对象的名称
     */
    private String name;

    /**
     * 表的名称
     */
    private String tableName;

    /**
     * 对象的字段列表
     */
    private final List<DataField> fields = new ArrayList<>();

    /**
     * 主键字段
     */
    private DataField primaryField;

    /**
     * 主表记录引用字段
     */
    private MasterDataField masterField;

    public DataObject(String name) {
        this.name = name;
    }

    public DataObject(String name, String tableName) {
        this.name = name;
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<DataField> getFields() {
        return fields;
    }

    /**
     * 根据字段的索引获取字段
     *
     * @param index
     * @return
     */
    public DataField getField(int index) {
        if (index >= 0 && index < this.fields.size()) {
            return this.fields.get(index);
        }

        return null;
    }

    /**
     * 根据字段名称获取字段
     *
     * @param fieldName
     * @return
     */
    public DataField getField(String fieldName) {
        for (DataField field : fields) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }

        return null;
    }

    /**
     * 添加一个字段
     *
     * @param field
     */
    public void addField(DataField field) {
        this.fields.add(field);
        if (field.isPrimary()) {
            this.primaryField = field;
        } else if (field instanceof MasterDataField) {
            this.masterField = (MasterDataField) field;
        }
    }

    /**
     * 添加多个字段
     *
     * @param fields
     */
    public void addFields(List<DataField> fields) {
        this.fields.addAll(fields);
        int flag = 0;
        for (DataField field : fields) {
            if (field.isPrimary()) {
                this.primaryField = field;
                flag++;
            } else if (field instanceof MasterDataField) {
                this.masterField = (MasterDataField) field;
                flag++;
            }
            if (flag >= 2) {
                break;
            }
        }
    }

    /**
     * 获取主键字段
     *
     * @return
     */
    public DataField getPrimaryField() {
        return this.primaryField;
    }

    /**
     * 判断是否当前对象的主对象
     *
     * @param object
     * @return
     */
    public boolean isMasterObject(DataObject object) {
        MasterDataField masterField = getMasterField();
        return masterField != null && masterField.getMasterObjectName().equalsIgnoreCase(object.getName());
    }

    /**
     * 获取主对象字段，当本对象是其它对象的子对象时，才会存在主对象字段
     *
     * @return
     */
    public MasterDataField getMasterField() {
        return this.masterField;
    }
}
