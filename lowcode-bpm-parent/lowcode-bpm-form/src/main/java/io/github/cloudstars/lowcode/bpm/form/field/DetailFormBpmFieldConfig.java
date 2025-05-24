package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.ArrayValueTypeConfig;

import java.util.List;

/**
 * 子表单字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "DETAIL-FORM")
public class DetailFormBpmFieldConfig extends AbstractBpmFieldConfig<ArrayValueTypeConfig> {

    // 子表单字段列表配置名称
    private static final String ATTR_FIELDS = "fields";

    // 是否允许新增行配置名称
    private static final String ATTR_ALLOW_NEWLINE = "allowNewline";

    /**
     * 子表单配置
     */
    // private BpmFormConfig subBpmFormConfig;

    /**
     * 是否允许新增行
     */
    private Boolean allowNewLine;

    /**
     * 字段列表
     */
    private List<AbstractBpmFieldConfig> fields;

    public DetailFormBpmFieldConfig() {
    }

    public DetailFormBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consume(configJson, ATTR_FIELDS, (v) -> {
            this.fields = super.parseFields((JsonArray) v);
        });
        this.allowNewLine = ConfigUtils.getBoolean(configJson, ATTR_ALLOW_NEWLINE);
    }

    public List<AbstractBpmFieldConfig> getFields() {
        return fields;
    }

    public void setFields(List<AbstractBpmFieldConfig> fields) {
        this.fields = fields;
    }

    public Boolean getAllowNewLine() {
        return allowNewLine;
    }

    public void setAllowNewLine(Boolean allowNewLine) {
        this.allowNewLine = allowNewLine;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putJsonArray(configJson, ATTR_FIELDS, this.fields);
        ConfigUtils.put(configJson, ATTR_ALLOW_NEWLINE, this.allowNewLine);

        return configJson;
    }

}
