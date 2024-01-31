package net.cf.form.engine.repository.mongo.convert;


import net.cf.form.engine.repository.data.value.DataType;

import java.util.HashMap;
import java.util.Map;

public class MongoConvertManager {

    private static Map<String, ValueConverter> CONVERTERS = new HashMap<>();

    static {
        CONVERTERS.put(DataType.TEXT.name(), new TextConverter());
        CONVERTERS.put(DataType.OBJECT.name(), new TextConverter());
    }

    public static ValueConverter getConverter(DataType valueType) {
        return CONVERTERS.get(valueType.name());
    }


}
