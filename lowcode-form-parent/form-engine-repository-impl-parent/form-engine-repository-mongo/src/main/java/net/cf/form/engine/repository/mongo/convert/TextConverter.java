package net.cf.form.engine.repository.mongo.convert;


import net.cf.form.engine.repository.data.value.DataType;

public class TextConverter implements ValueConverter{
    @Override
    public String getType() {
        return DataType.TEXT.name();
    }

    @Override
    public Object convertValue(Object value) {
        return value;
    }
}
