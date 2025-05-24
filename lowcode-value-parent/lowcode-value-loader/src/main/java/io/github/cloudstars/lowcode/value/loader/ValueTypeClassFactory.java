package io.github.cloudstars.lowcode.value.loader;

import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据格式类工厂
 *
 * @author clouds
 */
public class ValueTypeClassFactory {

    /**
     * key是数据格式配置，值是数据格式实现的Java类的映射表
     */
    private static final Map<Class<? extends XValueTypeConfig>, Class<? extends XValueType>> VALUE_TYPE_CALSS_MAP = new HashMap<>();


    private ValueTypeClassFactory() {
    }

    /**
     * 注册一种数据格式类
     *
     * @param valueTypeClass 数据格式类
     */
    public static void register(Class<? extends XValueType> valueTypeClass) {
        String typeName = valueTypeClass.getName();
        ValueTypeClass[] annotations = valueTypeClass.getAnnotationsByType(ValueTypeClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("数据格式" + typeName + "必须添加注解@ValueTypeClass，注册失败！");
        }

        Class<? extends XValueTypeConfig> valueTypeConfigClass = annotations[0].valueTypeConfigClass();
        try {
            valueTypeClass.getConstructor(valueTypeConfigClass);
            VALUE_TYPE_CALSS_MAP.put(valueTypeConfigClass, valueTypeClass);
        } catch (Exception e) {
            String valueTypeConfigClassName = valueTypeConfigClass.getName();
            throw new RuntimeException("数据格式" + typeName + "必须有一个带" + valueTypeConfigClassName + "参数的public构造函数！");
        }
    }


    /**
     * 根据数据格式的Java类获取数据格式的Java类
     *
     * @param valueTypeConfigClass 数据格式配置Java类
     * @return 数据格式Java类
     */
    public static Class<? extends XValueType> get(Class<? extends XValueTypeConfig> valueTypeConfigClass) {
        Class<? extends XValueType> valueTypeClass = VALUE_TYPE_CALSS_MAP.get(valueTypeConfigClass);
        if (valueTypeClass == null) {
            throw new RuntimeException("数据格式[" + valueTypeConfigClass.getName() + "]不存在！");
        }

        return valueTypeClass;
    }
}
