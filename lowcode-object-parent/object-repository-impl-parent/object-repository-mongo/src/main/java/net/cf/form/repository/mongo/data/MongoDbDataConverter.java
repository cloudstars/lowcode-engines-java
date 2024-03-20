package net.cf.form.repository.mongo.data;

import org.bson.Document;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

import java.util.*;

public class MongoDbDataConverter {


    /**
     * mongo数据转为业务数据
     * @param mongoData
     * @return
     */
    public static Object convertDbData(Object mongoData) {
        if (mongoData == null) {
            return null;
        }
        if (mongoData instanceof Document) {
            return convertDoc((Document) mongoData);
        } else if (mongoData instanceof List) {
            return convertList((List)mongoData);
        } else {
            return convertData(mongoData);
        }

    }


    public static Map<String, Object> convertDoc(Document document) {
        Map<String, Object> data = new HashMap<>();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            data.put(entry.getKey(), convertDbData(entry.getValue()));
        }
        return data;
    }

    private static List<Object> convertList (List mongoData) {
        List<Object> items = new ArrayList<>();
        for (Object listItem : (List)mongoData) {
            if (listItem instanceof Document) {
                items.add(convertDoc((Document)listItem));
            } else {
                items.add(convertDbData(listItem));
            }
        }
        return items;
    }


    private static Object convertData(Object data) {
        if (data instanceof Decimal128) {
            return ((Decimal128)data).bigDecimalValue();
        } else if (data instanceof Date) {
            return ((Date)data).getTime();
        } else if (data instanceof ObjectId) {
            return ((ObjectId)data).toString();
        } else {
            return data;
        }
    }





}
