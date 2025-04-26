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
    private static final Map<Class<? extends XValueTypeConfig>, Class<? extends XValueType>> valueTypeClassMap = new HashMap<>();


    private ValueTypeFactory() {
    }

    /**
     * 根据数据格式配置实例化一个数据格式实现类
     *
     * @param valueTypeConfig 数据格式配置
     * @return 数据格式
     */
    public static XValueType newInstance(XValueTypeConfig valueTypeConfig) {
        Class<? extends XValueType> valueTypeClass = valueTypeClassMap.get(valueTypeConfig.getClass());
        try {
            Constructor<? extends XValueType> constructor = valueTypeClass.getConstructor(XValueTypeConfig.class);
            return constructor.newInstance(valueTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式类型[" + valueTypeConfig.getClass().getName() + "]出错！", e);
        }
    }

}
