package net.cf.object.engine.data;

import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.ValueTypeImpl;

import java.util.Arrays;
import java.util.Date;

/**
 * 数据转换器
 *
 * @author clous
 */
public class DataConvert {

    /**
     * 从结果集中的值解析为一个Object
     *
     * @param resultVal
     * @return
     */
    public static Object convert(final Object resultVal, ValueTypeImpl valueType) {
        Object resultValue = resultVal;
        if (resultValue == null) {
            return null;
        }

        // 时间戳数字的格式转换对象格式
        DataType dataType = valueType.getDataType();
        if (valueType.isArray()) {
            if (resultVal instanceof String) {
                resultValue = DataConvert.stringToList((String) resultVal);
            }
        } else {
            if (dataType == DataType.DATE) {
                if (resultVal instanceof Long) {
                    resultValue = new Date(((Long) resultVal).longValue());
                }
            } else if (dataType == DataType.TIME) {
                if (resultVal instanceof Integer) {
                    resultValue = new Date(((Integer) resultVal).intValue());
                }
            }
        }

        return resultValue;
    }

    /**
     * 将 String 转为 List
     *
     * @param s
     * @return
     */
    public static Object stringToList(final String s) {
        String str = s;
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
