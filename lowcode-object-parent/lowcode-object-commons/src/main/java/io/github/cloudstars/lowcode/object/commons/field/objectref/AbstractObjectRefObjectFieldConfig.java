package io.github.cloudstars.lowcode.object.commons.field.objectref;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;

/**
 * 抽象的模型引用字段配置
 *
 * @author clouds
 */
public abstract class AbstractObjectRefObjectFieldConfig extends AbstractObjectFieldConfig implements XObjectRefObjectFieldConfig {

    private static final String ATTR_REF_TYPE = "refType";

    private static final String ATTR_REF_OBJECT_NAME = "refObjectName";

    private static final String ATTR_IS_MULTI_REF = "isMultiRef";

    /**
     * 模型引用的类型
     */
    private ObjectRefType refType;

    /**
     * 引用的模型的名称
     */
    private String refObjectName;

    /**
     * 是否一对多引用
     */
    private Boolean isMultiRef;

    public AbstractObjectRefObjectFieldConfig() {
    }

    public AbstractObjectRefObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        String refTypeValue = ConfigUtils.getString(configJson, ATTR_REF_TYPE);
        this.refType = ObjectRefType.valueOf(refTypeValue.toUpperCase());
        this.refObjectName = ConfigUtils.getString(configJson, ATTR_REF_OBJECT_NAME);
        ConfigUtils.consumeIfPresent(configJson, ATTR_IS_MULTI_REF, (v) -> {
            this.isMultiRef = (Boolean) v;
        });
    }

    @Override
    public ObjectRefType getRefType() {
        return this.refType;
    }

    @Override
    public String getRefObjectName() {
        return this.refObjectName;
    }

    @Override
    public boolean isMultiRef() {
        return this.isMultiRef == null || this.isMultiRef == false;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_REF_TYPE, this.refType.name());
        ConfigUtils.put(configJson, ATTR_REF_OBJECT_NAME, this.refObjectName);
        ConfigUtils.putIfNotNull(configJson, ATTR_IS_MULTI_REF, this.isMultiRef);

        return configJson;
    }
}
