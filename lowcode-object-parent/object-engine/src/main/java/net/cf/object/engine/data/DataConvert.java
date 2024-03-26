package net.cf.object.engine.data;

import net.cf.object.engine.object.ValueType;

import java.util.Arrays;
import java.util.List;

/**
 * 数据转换器
 *
 * @author clous
 */
public class DataConvert {

    /**
     * 从结果集中的值解析为一个Object
     *
     * @param resultValue
     * @return
     */
    public static Object convert(Object resultValue, ValueType valueType) {
        if (resultValue != null) {
            if (valueType.isArray()) {
                resultValue = DataConvert.convertList(resultValue);
            } else {
                resultValue = DataConvert.convertObject(resultValue);
            }
        }

        return resultValue;
    }

    /**
     * 从结果集中的值解析为一个List
     *
     * @param resultValue
     * @return
     */
    public static Object convertList(Object resultValue) {
        if (resultValue != null) {
            // 数据中可能存在用文本存数组的情况
            if (resultValue instanceof String) {
                resultValue = DataConvert.stringToList((String) resultValue);
            }
        }

        return resultValue;
    }

    /**
     * 从结果集中的值解析为一个Object
     *
     * @param resultValue
     * @return
     */
    public static Object convertObject(Object resultValue) {
        if (resultValue != null) {
            // 数据中可能存在数组转非数组的情况
            if (resultValue instanceof List) {
                resultValue = resultValue.toString();
            }
        }

        return resultValue;
    }

    /**
     * 将 String 转为 List
     *
     * @param str
     * @return
     */
    private static Object stringToList(String str) {
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 1);
            String[] items = str.split(",");
            for (int i = 0, l = items.length; i < l; i++) {
                items[i] = items[i].trim();
            }
            return Arrays.asList(items);
        }

        return str;
    }

}
