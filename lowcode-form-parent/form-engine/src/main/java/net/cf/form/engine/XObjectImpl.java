package net.cf.form.engine;

import net.cf.form.engine.field.UniqueFields;
import net.cf.form.engine.field.XField;
import net.cf.form.engine.field.XFieldImpl;
import net.cf.form.engine.object.ValidationResult;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.repository.data.DbType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 动态对象实现类
 *
 * @author clouds
 */
@Deprecated
public class XObjectImpl implements XObject {

    /**
     * 名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否多租户
     */
    private boolean multiTenant;

    /**
     * 字段列表
     */
    private final List<XField> fields = new ArrayList<>();

    /**
     * 主键字段的名称
     */
    private String primaryFieldName;

    /**
     * 名称字段的名称
     */
    private String nameFieldName;

    /**
     * 是否持久化
     */
    private boolean persistence;

    /**
     * 数据库的类型
     */
    @Deprecated
    private DbType dbType;

    /**
     * 数据源编号
     */
    private String dataSourceKey;

    /**
     * 对象持久化的表名
     */
    private String tableName;

    public XObjectImpl(String name) {
        this.name = name;
    }

    public XObjectImpl(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isMultiTenant() {
        return multiTenant;
    }

    public void setMultiTenant(boolean multiTenant) {
        this.multiTenant = multiTenant;
    }

    @Override
    public List<XField> getFields() {
        return fields;
    }

    public void addField(XFieldImpl field) {
        this.fields.add(field);
    }

    public void addFields(List<XFieldImpl> fields) {
        this.fields.addAll(fields);
    }

    @Override
    public boolean isAutoPrimaryField() {
        return false;
    }

    @Override
    public List<UniqueFields> getUniqueFields() {
        return null;
    }

    @Override
    public String getPrimaryFieldName() {
        return this.primaryFieldName;
    }

    public void setPrimaryFieldName(String primaryFieldName) {
        this.primaryFieldName = primaryFieldName;
    }

    public void setNameFieldName(String nameFieldName) {
        this.nameFieldName = nameFieldName;
    }

    @Override
    public boolean isPersistence() {
        return persistence;
    }

    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }

    @Override
    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public void setDataSourceKey(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public ValidationResult validate(Map<String, Object> dataMap) {
        return null;
    }

    @Override
    public List<XObject> getSubObjects() {
        return null;
    }

    @Override
    public List<XObject> getOneByOneRefObjects() {
        return null;
    }

    @Override
    public List<XObject> getOneByManyRefObjects() {
        return null;
    }

    @Override
    public XObject getParentObject() {
        return null;
    }
}
