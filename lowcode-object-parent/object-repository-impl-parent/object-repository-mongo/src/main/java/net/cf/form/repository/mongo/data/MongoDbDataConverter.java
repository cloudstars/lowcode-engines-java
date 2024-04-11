package net.cf.form.repository.mongo.data;

import org.bson.Document;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoDbDataConverter {


    /**
     * mongo数据转为业务数据
     *
     * @param mongoData
     * @return
     */
    public static Object convertDbData(Object mongoData) {
        if (mongoData == null) {
            return null;
        }
        if (mongoData instanceof Document) {
            return convertDoc((Document) mongoData, null);
        } else if (mongoData instanceof List) {
            return convertList((List) mongoData);
        } else {
            return convertData(mongoData);
        }

    }

    /**
     * @param document
     * @param convertContext
     * @return
     */
    public static Map<String, Object> convertDoc(Document document, DataConvertContext convertContext) {
        Map<String, Object> data = new HashMap<>();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            String key = getOriginFieldName(entry.getKey(), convertContext);
            data.put(key, convertDbData(entry.getValue()));
        }
        return data;
    }

    /**
     * @param key
     * @param dataConvertContext
     * @return
     */
    private static String getOriginFieldName(String key, DataConvertContext dataConvertContext) {
        if (dataConvertContext == null || !dataConvertContext.isReplaceField()) {
            return key;
        }
        if (dataConvertContext.getReplaceFields().containsKey(key)) {
            return dataConvertContext.getReplaceFields().get(key);
        }
        return key;
    }


    /**
     * @param mongoData
     * @return
     */
    private static List<Object> convertList(List mongoData) {
        List<Object> items = new ArrayList<>();
        for (Object listItem : (List) mongoData) {
            if (listItem instanceof Document) {
                items.add(convertDoc((Document) listItem, null));
            } else {
                items.add(convertDbData(listItem));
            }
        }
        return items;
    }

    /**
     * @param data
     * @return
     */
    private static Object convertData(Object data) {
        if (data instanceof Decimal128) {
            return ((Decimal128) data).bigDecimalValue();
        } else if (data instanceof ObjectId) {
            return ((ObjectId) data).toString();
        } else {
            return data;
        }
    }


}
