package io.github.cloudstars.lowcode.bpm.commons.config.condition;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 条件节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.CONDITION")
public class ConditionConfig extends AbstractConfig {

    private ConditionType type;

    /**
     * 条件表达式
     */
    private String expression;

    public ConditionConfig() {
    }

    public ConditionConfig(JsonObject configJson) {
        super(configJson);

        Object typeValue = configJson.get("type");
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
}
