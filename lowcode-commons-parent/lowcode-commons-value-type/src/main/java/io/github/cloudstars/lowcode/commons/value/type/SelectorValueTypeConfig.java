package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 选项类型的数据格式
 *
 * @author clouds
 * @param <T> 单个选项的数据格式
 */
@ValueTypeConfigClass(name = "SELECTOR", valueClass = Object.class)
public class SelectorValueTypeConfig<T extends XValueTypeConfig> extends AbstractValueTypeConfig {

    // 是否多选配置名称
    private static final String ATTR_MULTIPLE = "multiple";

    /**
     * 是否多选
     */
    private Boolean multiple;

    /**
     * 选项值的数据格式
     */
    private T itemsValueType;

    public SelectorValueTypeConfig(T itemsValueType) {
        this.itemsValueType = itemsValueType;
    }

    public SelectorValueTypeConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consume(configJson, ATTR_MULTIPLE, (v) -> {
            this.multiple = (Boolean) v;
        });
        ConfigUtils.consume(configJson, AbstractArrayValueTypeConfig.ATTR_ITEMS, (v) -> {
            this.itemsValueType = (T) ValueTypeConfigFactory.newInstance((JsonObject) v);
        });
    }

    @Override
    public DataTypeEnum getDataType() {
        return Boolean.TYPE.equals(this.multiple) ? DataTypeEnum.ARRAY : this.itemsValueType.getDataType();
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_MULTIPLE, this.multiple);
        ConfigUtils.putJson(configJson, AbstractArrayValueTypeConfig.ATTR_ITEMS, this.itemsValueType);

        return configJson;
    }
}
