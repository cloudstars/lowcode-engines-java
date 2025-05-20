package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;

import java.lang.reflect.Constructor;

/**
 * 模型视图接口构建器工厂
 *
 * @author clouds
 */
public class ObjectMethodBuilderFactory {

    private ObjectMethodBuilderFactory() {
    }

    /**
     * 根据节点的Json配置实例化一个模型视图接口构建器
     *
     * @param type
     * @return
     */
    public static <T extends XObjectViewConfig>XObjectViewMethodBuilder newInstance(String type, Class<T> viewConfigClass) {
        Class<? extends XObjectViewMethodBuilder> builderClass = ObjectMethodBuilderClassFactory.get(type);
        try {
            Constructor<? extends XObjectViewMethodBuilder> constructor = builderClass.getConstructor(viewConfigClass);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化模型视图接口构建器，类型为[" + type + "]出错！", e);
        }
    }

}
