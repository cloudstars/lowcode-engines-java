package net.cf.form.engine.repository.mongo.convert;

public interface ValueConverter {
    String getType();

    Object convertValue(Object value);

}
