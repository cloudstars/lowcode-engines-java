package io.github.cloudstars.lowcode.commons.predicate.config.json.binary;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.predicate.config.json.XJsonPredicateConfig;

/**
 * Json逻辑断言
 *
 * @author clouds
 */
public class BinaryJsonPredicateConfig extends AbstractTypedConfig implements XJsonPredicateConfig {

    // 左值配置名称
    private static final String ATTR_LEFT = "left";

    // 右值配置名称
    private static final String ATTR_RIGHT = "right";

    /**
     * 左值
     */
    private XBinaryItemConfig left;

    /**
     * 逻辑操作符
     */
    private BinaryOperatorEnum operator;

    /**
     * 右值
     */
    private XBinaryItemConfig right;

    public XBinaryItemConfig getLeft() {
        return left;
    }

    public void setLeft(XBinaryItemConfig left) {
        this.left = left;
    }

    public BinaryOperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(BinaryOperatorEnum operator) {
        this.operator = operator;
    }

    public XBinaryItemConfig getRight() {
        return right;
    }

    public void setRight(XBinaryItemConfig right) {
        this.right = right;
    }

    public BinaryJsonPredicateConfig() {
    }

    public BinaryJsonPredicateConfig(JsonObject configJson) {
        super(configJson);

        this.setType(XJsonPredicateConfig.TYPE_BINARY);
        JsonObject leftConfigJson = (JsonObject) configJson.get(ATTR_LEFT);
        this.left = this.parseItem(leftConfigJson);
        String operatorString = (String) configJson.get(ATTR_OPERATOR);
        this.operator = BinaryOperatorEnum.valueOf(operatorString);
        JsonObject rightConfigJson = (JsonObject) configJson.get(ATTR_RIGHT);
        this.right = this.parseItem(rightConfigJson);
    }


    /**
     * 解析左/右值
     *
     * @param configJson
     * @return
     */
    private XBinaryItemConfig parseItem(JsonObject configJson) {
        String type = (String) configJson.get(XTypedConfig.ATTR);
        if (XBinaryItemConfig.TYPE_FIELD.equals(type)) {
            return new FieldBinaryItemConfig(configJson);
        } else if (XBinaryItemConfig.TYPE_VALUE.equalsIgnoreCase(type)) {
            return new ValueBinaryItemConfig(configJson);
        } else {
            throw new RuntimeException("二元操作断言左/左值类型[" + type + "]不支持");
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.putJson(ATTR_LEFT, this.left);
        configJson.put(ATTR_OPERATOR, this.operator);
        configJson.putJson(ATTR_RIGHT, this.right);

        return configJson;
    }

}
