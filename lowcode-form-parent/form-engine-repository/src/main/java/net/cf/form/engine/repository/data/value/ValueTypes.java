package net.cf.form.engine.repository.data.value;

/**
 * 数值类型
 *
 * @author clouds
 *
 */
@Deprecated
public enum ValueTypes {
    BOOLEAN(BooleanValue.class),
    TEXT(TextValue.class),
    INTEGER(IntegerValue.class),
    LONG(LongValue.class),
    BIG_DECIMAL(BigDecimalValue.class),
    DATE(DateValue.class),
    TIME(TimeValue.class),
    JSON_OBJECT(JsonObjectValue.class);

    private Class<? extends Valuable> valuableClass;

    ValueTypes(Class<? extends Valuable> valuableClass) {
        this.valuableClass = valuableClass;
    }
}
