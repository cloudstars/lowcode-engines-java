package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据格式工厂
 *
 * @author clouds
 */
public class ValueTypeFactory {

    /**
     * key是数据格式配置，值是数据格式实现的Java类的映射表
     */
    private static final Map<Class<? extends XValueTypeConfig>, Class<? extends XValueType>> VALUE_TYPE_CALSS_MAP = new HashMap<>();


    private ValueTypeFactory() {
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
     * 根据数据格式配置实例化一个数据格式实现类
     *
     * @param valueTypeConfig 数据格式配置
     * @return 数据格式
     */
    public static XValueType newInstance(XValueTypeConfig valueTypeConfig) {
        Class<? extends XValueTypeConfig> valueTypeConfigClass = valueTypeConfig.getClass();
        Class<? extends XValueType> valueTypeClass = VALUE_TYPE_CALSS_MAP.get(valueTypeConfigClass);
        try {
            Constructor<? extends XValueType> constructor = valueTypeClass.getConstructor(valueTypeConfigClass);
            return constructor.newInstance(valueTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式[" + valueTypeClass.getName() + "]出错！", e);
        }
    }

}
