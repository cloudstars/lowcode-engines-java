package io.github.cloudstars.lowcode.predicate.type.json.logic;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.predicate.type.XPredicateConfig;
import io.github.cloudstars.lowcode.predicate.type.json.XJsonPredicateConfig;

import java.util.List;

/**
 * Json逻辑断言
 *
 * @author clouds
 */
public class LogicJsonPredicateConfig extends AbstractTypedConfig implements XJsonPredicateConfig {

    /**
     * 逻辑操作符
     */
    private LogicOperatorEnum operator;

    /**
     * 通过操作符加接的断言
     */
    private List<XJsonPredicateConfig> expressions;

    public LogicJsonPredicateConfig() {
        this.setType(TYPE_LOGIC);
    }

    public LogicJsonPredicateConfig(LogicOperatorEnum operator, List<XJsonPredicateConfig> expressions) {
        this();

        this.operator = operator;
        this.expressions = expressions;
    }

    public LogicJsonPredicateConfig(JsonObject configJson) {
        super(configJson);

        String operatorString = (String) configJson.get(ATTR_OPERATOR);
        this.operator = LogicOperatorEnum.valueOf(operatorString);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_OPERATOR, this.operator);
        ConfigUtils.putJsonArray(configJson, XPredicateConfig.ATTR, this.expressions);
        return configJson;
    }

}
