package io.github.cloudstars.lowcode.value.loader;

import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

import java.lang.reflect.Constructor;

/**
 * 数据格式工厂
 *
 * @author clouds
 */
public class ValueTypeFactory {

    /**
     * 根据数据格式配置实例化一个数据格式实现类
     *
     * @param valueTypeConfig 数据格式配置
     * @return 数据格式
     */
    public static XValueType newInstance(XValueTypeConfig valueTypeConfig) {
        Class<? extends XValueTypeConfig> valueTypeConfigClass = valueTypeConfig.getClass();
        Class<? extends XValueType> valueTypeClass = ValueTypeClassFactory.get(valueTypeConfigClass);
        try {
            Constructor<? extends XValueType> constructor = valueTypeClass.getConstructor(valueTypeConfigClass);
            return constructor.newInstance(valueTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式[" + valueTypeClass.getName() + "]出错！", e);
        }
    }

}
