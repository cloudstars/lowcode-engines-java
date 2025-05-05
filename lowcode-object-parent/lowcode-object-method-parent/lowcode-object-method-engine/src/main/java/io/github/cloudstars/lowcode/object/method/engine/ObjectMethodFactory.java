package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;

import java.lang.reflect.Constructor;

/**
 * 模型方法工厂
 *
 * @author clouds
 */
public class ObjectMethodFactory {

    /**
     * 根据模型方法配置实例化一个模型方法实现类
     *
     * @param objectMethodConfig 模型方法配置
     * @return 模型方法
     */
    public static XObjectMethod newInstance(XObjectMethodConfig objectMethodConfig) {
        Class<? extends XObjectMethodConfig> valueTypeConfigClass = objectMethodConfig.getClass();
        Class<? extends XObjectMethod> valueTypeClass = ObjectMethodClassFactory.get(valueTypeConfigClass);
        try {
            Constructor<? extends XObjectMethod> constructor = valueTypeClass.getConstructor(valueTypeConfigClass);
            return constructor.newInstance(objectMethodConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化模型方法[" + valueTypeClass.getName() + "]出错！", e);
        }
    }

}
