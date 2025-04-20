package io.github.cloudstars.lowcode.commons.data.predicate.json;

import io.github.cloudstars.lowcode.commons.data.predicate.XPredicateConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * Json逻辑断言
 *
 * @author clouds
 */
public class LogicJsonPredicate extends AbstractTypedConfig implements XJsonPredicate {

    /**
     * 逻辑操作符
     */
    private LogicOperatorEnum operator;

    /**
     * 通过操作符加接的断言
     */
    private List<XJsonPredicate> expressions;

    public LogicJsonPredicate() {
        this.setType(XJsonPredicate.TYPE_LOGIC);
    }

    public LogicJsonPredicate(LogicOperatorEnum operator, List<XJsonPredicate> expressions) {
        this();

        this.operator = operator;
        this.expressions = expressions;
    }

    public LogicJsonPredicate(JsonObject configJson) {
        super(configJson);

        String operatorString = (String) configJson.get(ATTR_OPERATOR);
        this.operator = LogicOperatorEnum.valueOf(operatorString);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_OPERATOR, this.operator);
        configJson.put(XPredicateConfig.ATTR, JsonConfigUtils.toJsonArray(this.expressions));
        return configJson;
    }

}
