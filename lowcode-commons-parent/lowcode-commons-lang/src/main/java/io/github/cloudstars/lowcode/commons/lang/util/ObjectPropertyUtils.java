package io.github.cloudstars.lowcode.commons.lang.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对象中属性提取工具类
 *
 * @author clouds
 */
public final class ObjectPropertyUtils {

    /**
     * 从对象中获取属性的值
     *
     * @param data 对象
     * @param property 属性名
     * @return 属性值
     */
    public static Object getPropertyValue(Object data, String property) {
        if (data == null) {
            return null;
        }

        if (data instanceof Map) {
            return getPropertyValue((Map<String, Object>) data, property);
        }

        if (data instanceof List) {
            return getPropertyValue((List) data, property);
        }

        return ObjectUtils.getFieldValue(data, property);
    }

    /**
     * 从Map中获取属性的值
     *
     * @param dataMap Map
     * @param property 属性名
     * @return 属性值
     */
    private static Object getPropertyValue(Map<String, Object> dataMap, String property) {
        if (dataMap == null) {
            return null;
        }

        int dotIndex = property.indexOf(".");
        if (dotIndex < 0) { // 不存在属性，如：a.b.c
            return dataMap.get(property);
        }

        String ownerName = property.substring(0, dotIndex);
        Object ownerValue = dataMap.get(ownerName);

        String subPropertyName = property.substring(dotIndex + 1);;
        return getPropertyValue(ownerValue, subPropertyName);
    }


    /**
     * 从列表中获取属性的值
     *
     * @param dataList 对象列表
     * @param property 属性名
     * @return 属性值
     */
    private static List<Object> getPropertyValue(List<Object> dataList, String property) {
        if (dataList == null) {
            return null;
        }

        List<Object> values = new ArrayList<>();
        for (Object data : dataList) {
            values.add(getPropertyValue(data, property));
        }

        return isNullItemsList(values) ? null : values;
    }

    /**
     * 是否是一个全为NULL元素的数组
     *
     * @param dataList 数据列表
     * @return boolean值
     */
    private static boolean isNullItemsList(List<Object> dataList) {
        for (Object data : dataList) {
            if (data != null) {
                return false;
            }
        }

        return true;
    }

}
