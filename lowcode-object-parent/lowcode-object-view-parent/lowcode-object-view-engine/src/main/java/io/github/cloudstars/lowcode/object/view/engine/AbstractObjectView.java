package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.object.method.engine.XObjectMethod;
import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

/**
 * 抽象的模型视图
 *
 * @author clouds
 * @param <T> 模型视图配置类型
 */
public abstract class AbstractObjectView<T extends AbstractObjectViewConfig> implements XObjectView<T> {

    /**
     * 模型视图配置
     */
    private T viewConfig;

    public AbstractObjectView(T viewConfig) {
        this.viewConfig = viewConfig;
    }

    @Override
    public T getViewConfig() {
        return viewConfig;
    }

    @Override
    public Object executeMethod(String methodType, Object methodParams) {
        XObjectViewMethodBuilder builder = ObjectMethodBuilderFactory.newInstance(methodType, this.viewConfig.getClass());
        XObjectMethod viewMethod = builder.build(viewConfig);
        return viewMethod.execute(methodParams);
    }

    /**
     * 根据接口类型解析模型接口
     *
     * @param methodType
     * @return 模型接口
     */
    protected abstract XObjectMethod resolveMethod(String methodType);

}
