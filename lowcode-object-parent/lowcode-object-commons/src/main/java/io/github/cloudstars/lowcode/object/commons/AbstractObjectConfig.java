package io.github.cloudstars.lowcode.object.commons;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;
import io.github.cloudstars.lowcode.object.commons.field.ObjectFieldConfigFactory;
import io.github.cloudstars.lowcode.object.commons.field.objectref.AbstractObjectRefObjectFieldConfig;

import java.util.List;

/**
 * 抽象的模型配置
 *
 * @author clouds
 */
public class AbstractObjectConfig<F extends AbstractObjectFieldConfig, RF extends AbstractObjectRefObjectFieldConfig> extends AbstractResourceConfig implements XObjectConfig<F, RF> {

    // 字段列表配置名称
    private static final String ATTR_FIELDS = "fields";
    // 主键字段配置
    private static final String ATTR_PRIMARY_FIELD_KEY = "primaryFieldKey";
    // 名称字段配置
    private static final String ATTR_NAME_FIELD_KEY = "nameFieldKey";
    // 表名称字段配置
    private static final String ATTR_TABLE_NAME = "tableName";
    // 是否开启多租户配置
    private static final String ATTR_ENABLE_ENTERPRISE = "enableEnterprise";

    /**
     * 字段列表
     */
    private List<F> fields;

    /**
     * 主键字段编号
     */
    private String primaryFieldKey;

    /**
     * 名称字段编号
     */
    private String nameFieldKey;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 是否开启多租户
     */
    private Boolean enableEnterprise;

    public AbstractObjectConfig() {
    }

    public AbstractObjectConfig(JsonObject configJson) {
        super(configJson);

        this.fields = ConfigUtils.getList(configJson, ATTR_FIELDS, (v) -> (F) ObjectFieldConfigFactory.newInstance(v));
        this.primaryFieldKey = ConfigUtils.getNonNullString(configJson, ATTR_PRIMARY_FIELD_KEY);
        //this.nameFieldKey = ConfigUtils.getNonNullString(configJson, ATTR_NAME_FIELD_KEY);
        this.tableName = ConfigUtils.getString(configJson, ATTR_TABLE_NAME);
        this.enableEnterprise = ConfigUtils.getBoolean(configJson, ATTR_ENABLE_ENTERPRISE);
    }

    @Override
    public String getType() {
        return "OBJECT";
    }

    public List<F> getFields() {
        return fields;
    }

    public void setFields(List<F> fields) {
        this.fields = fields;
    }

    @Override
    public F getFieldByKey(String key) {
        return null;
    }

    @Override
    public F getFieldByName(String name) {
        return null;
    }

    @Override
    public String getPrimaryFieldKey() {
        return this.primaryFieldKey;
    }

    public void setPrimaryFieldKey(String primaryFieldKey) {
        this.primaryFieldKey = primaryFieldKey;
    }

    @Override
    public String getNameFieldKey() {
        return this.nameFieldKey;
    }

    public void setNameFieldKey(String nameFieldKey) {
        this.nameFieldKey = nameFieldKey;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getEnableEnterprise() {
        return enableEnterprise;
    }

    public void setEnableEnterprise(Boolean enableEnterprise) {
        this.enableEnterprise = enableEnterprise;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putList(configJson, ATTR_FIELDS, this.fields);
        ConfigUtils.put(configJson, ATTR_PRIMARY_FIELD_KEY, this.primaryFieldKey);
        //ConfigUtils.putIfNotNull(configJson, ATTR_NAME_FIELD_KEY, this.nameFieldKey);
        ConfigUtils.putIfNotNull(configJson, ATTR_TABLE_NAME, this.tableName);
        ConfigUtils.putIfNotNull(configJson, ATTR_ENABLE_ENTERPRISE, this.enableEnterprise);

        return configJson;
    }

}
