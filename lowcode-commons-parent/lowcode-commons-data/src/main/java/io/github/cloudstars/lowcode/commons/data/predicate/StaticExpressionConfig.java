package io.github.cloudstars.lowcode.commons.data.predicate;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 静态表达式配置
 *
 * @author clouds
 */
@ExpressionConfigClass(name = "STATIC")
public class StaticExpressionConfig extends AbstractExpressionConfig {

    // 文本配置名称
    private static final String ATTR_TEXT = "text";

    /**
     * 表达式文本
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StaticExpressionConfig() {
    }

    public StaticExpressionConfig(JsonObject configJson) {
        super(configJson);

        this.text = (String) configJson.get("text");
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TEXT, this.text);
        return configJson;
    }

}
