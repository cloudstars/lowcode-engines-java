package net.cf.form.engine.repository.mongo.statement.insert;


import net.cf.form.engine.repository.data.value.DataType;

public class MongoColumnValue implements BaseColumnValue {

    private DataType valueType;

    private Object value;

    public DataType getValueType() {
        return valueType;
    }

    public void setValueType(DataType valueType) {
        this.valueType = valueType;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
