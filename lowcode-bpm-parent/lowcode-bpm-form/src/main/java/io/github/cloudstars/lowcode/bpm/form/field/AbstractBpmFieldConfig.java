package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.SelectorValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public abstract class AbstractBpmFieldConfig<T extends XValueTypeConfig> extends AbstractTypedConfig implements XBpmFieldConfig {

    // 字段编号配置名称
    private static final String ATTR_KEY = "key";
    // 字段名配置名称
    private static final String ATTR_NAME = "name";
    // 字段标签配置名称
    private static final String ATTR_LABEL = "label";

    /**
     * 字段的编号
     */
    private String key;

    /**
     * 字段的名称
     */
    private String name;

    /**
     * 字段的标题
     */
    private String label;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 是否支持标题
     */
    private Boolean titleSupported;

    /**
     * 是否支持摘要
     */
    private Boolean digestSupported;

    /**
     * 数据格式配置
     */
    private T valueType;

    protected AbstractBpmFieldConfig() {
    }

    protected AbstractBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(BpmFieldConfigClass.class).name());
        this.key = (String) configJson.get(ATTR_KEY);
        this.name = (String) configJson.get(ATTR_NAME);
        this.label = (String) configJson.get(ATTR_LABEL);
        this.required = (Boolean) configJson.get(XBpmFieldConfig.ATTR_REQUIRED);
        this.titleSupported = (Boolean) configJson.get(XBpmFieldConfig.ATTR_TITLE_SUPPORTED);
        this.digestSupported = (Boolean) configJson.get(XBpmFieldConfig.ATTR_DIGEST_SUPPORTED);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getTitleSupported() {
        return titleSupported;
    }

    public void setTitleSupported(Boolean titleSupported) {
        this.titleSupported = titleSupported;
    }

    public Boolean getDigestSupported() {
        return digestSupported;
    }

    public void setDigestSupported(Boolean digestSupported) {
        this.digestSupported = digestSupported;
    }

    @Override
    public T getValueType() {
        return valueType;
    }

    public void setValueType(T valueType) {
        this.valueType = valueType;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_KEY, this.key);
        configJson.put(ATTR_NAME, this.name);
        configJson.put(ATTR_LABEL, this.label);
        ConfigUtils.putIfNotNull(configJson, XBpmFieldConfig.ATTR_REQUIRED, this.required);
        ConfigUtils.putIfNotNull(configJson, XBpmFieldConfig.ATTR_TITLE_SUPPORTED, this.titleSupported);
        ConfigUtils.putIfNotNull(configJson, XBpmFieldConfig.ATTR_DIGEST_SUPPORTED, this.digestSupported);

        if (this.valueType != null) { // Other类型无valueType
            // 将数据格式的配置合并到当前配置上（忽略同名属性，如：type）
            if (this.valueType instanceof SelectorValueTypeConfig) {
                // 选项数据格式直接将items的数据格式配置合并到当前字段上
                XValueTypeConfig itemsValueType = ((SelectorValueTypeConfig) this.valueType).getItemsValueType();
                ConfigUtils.putAllIgnorePresent(configJson, itemsValueType);
            } else {
                ConfigUtils.putAllIgnorePresent(configJson, this.valueType);
            }
        }

        return configJson;
    }

    /**
     * 解析字段列表配置
     *
     * @param fields 字段列表的JSON配置
     * @return 字段列表配置
     */
    protected List<AbstractBpmFieldConfig> parseFields(JsonArray fields) {
        List<AbstractBpmFieldConfig> fieldConfigs = new ArrayList<>();
        fields.forEach((field) -> {
            JsonObject fieldConfigJson = (JsonObject) field;
            AbstractBpmFieldConfig fieldConfig = BpmFieldConfigFactory.newInstance(fieldConfigJson);
            if (fieldConfig != null) {
                fieldConfigs.add(fieldConfig);
            }
        });

        return fieldConfigs;
    }
}
