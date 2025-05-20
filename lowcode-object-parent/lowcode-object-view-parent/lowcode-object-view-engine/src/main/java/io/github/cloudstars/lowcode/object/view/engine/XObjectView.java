package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

/**
 * 模型视图接口
 *
 * @author clouds
 * @param <C> 视图的配置类型
 */
public interface XObjectView<C extends AbstractObjectViewConfig> {

    /**
     * 获取视图的配置
     *
     * @return
     */
    C getViewConfig();

    /**
     * 执行视图接口
     *
     * @param methodType 接口类型
     * @param methodParams 接口参数
     * @return 接口运行结果
     */
    Object executeMethod(String methodType, Object methodParams);
}
