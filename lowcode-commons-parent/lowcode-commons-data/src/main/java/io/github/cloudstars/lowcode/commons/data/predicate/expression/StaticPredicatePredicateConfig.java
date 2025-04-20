package io.github.cloudstars.lowcode.commons.data.predicate.expression;

import io.github.cloudstars.lowcode.commons.data.predicate.AbstractPredicateConfig;
import io.github.cloudstars.lowcode.commons.data.predicate.PredicateConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 静态断言配置
 *
 * @author clouds
 */
@PredicateConfigClass(name = "STATIC")
public class StaticPredicatePredicateConfig extends AbstractPredicateConfig {

    // 文本配置名称
    private static final String ATTR_TEXT = "text";

    /**
     * 断言文本
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StaticPredicatePredicateConfig() {
    }

    public StaticPredicatePredicateConfig(JsonObject configJson) {
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
