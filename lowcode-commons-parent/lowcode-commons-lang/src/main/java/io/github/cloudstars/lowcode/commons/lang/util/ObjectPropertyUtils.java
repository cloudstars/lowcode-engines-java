package io.github.cloudstars.lowcode.commons.lang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对象中属性提取工具类
 *
 * @author clouds
 */
public final class ObjectPropertyUtils {

    // SFL4J Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectPropertyUtils.class);

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
     * 获取对象的属性值
     *
     * @param o
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object o, String fieldName) {
        if (o == null) {
            return null;
        }

        try {
            Field field = o.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(o);
        } catch (NoSuchFieldException e) {
            // 找不到属性时返回null
            LOGGER.warn("获取对象下的属性{}失败", fieldName, e);
            return null;
        } catch (IllegalAccessException e) {
            // 其它的异常情况，取值失败
            throw new RuntimeException(e);
        }
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
