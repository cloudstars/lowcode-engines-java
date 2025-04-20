package io.github.cloudstars.lowcode.bpm.commons.config.condition;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 条件节点配置
 *
 * @author clouds
 */
public class ConditionConfig extends AbstractConfig {

    /**
     * 条件类型配置名称
     */
    private static final String ATTR_TYPE = "type";

    /**
     * 条件值配置名称
     */
    private static final String ATTR_VALUE = "value";

    /**
     * 条件类型
     */
    private ConditionType type;

    /**
     * 条件表达式
     */
    private String expression;

    public ConditionConfig() {
    }

    public ConditionConfig(JsonObject configJson) {
        super(configJson);

        Object typeValue = configJson.get(ATTR_TYPE);
        if (typeValue == null) {
            this.type = ConditionType.EXPR;
        } else {
            this.type = ConditionType.valueOf((String) typeValue);
        }

        if (this.type == ConditionType.EXPR) {
            this.expression = (String) configJson.get("value");
        } else {
            // TODO
        }
    }

    public ConditionType getType() {
        return type;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TYPE, this.type);
        if (this.type == ConditionType.EXPR) {
            configJson.put(ATTR_VALUE, this.expression);
        } else {
            // TODO
        }

        return configJson;
    }
}
