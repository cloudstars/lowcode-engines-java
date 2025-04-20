package io.github.cloudstars.lowcode.commons.data.predicate.json;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * Json逻辑表达式
 *
 * @author clouds
 */
public class BinaryJsonExpression extends AbstractTypedConfig implements JsonExpression {

    // 左值配置名称
    private static final String ATTR_LEFT = "left";

    // 右值配置名称
    private static final String ATTR_RIGHT = "right";

    /**
     * 左值
     */
    private BinaryItem left;

    /**
     * 逻辑操作符
     */
    private BinaryOperatorEnum operator;

    /**
     * 右值
     */
    private BinaryItem right;

    public BinaryItem getLeft() {
        return left;
    }

    public void setLeft(BinaryItem left) {
        this.left = left;
    }

    public BinaryOperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(BinaryOperatorEnum operator) {
        this.operator = operator;
    }

    public BinaryItem getRight() {
        return right;
    }

    public void setRight(BinaryItem right) {
        this.right = right;
    }

    public BinaryJsonExpression() {
    }

    public BinaryJsonExpression(JsonObject configJson) {
        super(configJson);

        this.setType(JsonExpression.TYPE_BINARY);
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
    private BinaryItem parseItem(JsonObject configJson) {
        String type = (String) configJson.get(XTypedConfig.ATTR);
        if (BinaryItem.TYPE_FIELD.equals(type)) {
            return new FieldBinaryItem(configJson);
        } else if (BinaryItem.TYPE_VALUE.equalsIgnoreCase(type)) {
            return new ValueBinaryItem(configJson);
        } else {
            throw new RuntimeException("二元操作表达式左/左值类型[" + type + "]不支持");
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_LEFT, this.left.toJson());
        configJson.put(ATTR_OPERATOR, this.operator);
        configJson.put(ATTR_RIGHT, this.right.toJson());

        return configJson;
    }

}
