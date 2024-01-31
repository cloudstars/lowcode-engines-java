package net.cf.form.engine.repository.mongo.statement;


import net.cf.form.engine.repository.data.value.DataType;

public class CommonVisitValue implements VisitValue {

    private Object value;

    private DataType valueType;

    public CommonVisitValue(Object value, DataType valueType) {
        this.value = value;
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public DataType getValueType() {
        return valueType;
    }

    public void setValueType(DataType valueType) {
        this.valueType = valueType;
    }
}
