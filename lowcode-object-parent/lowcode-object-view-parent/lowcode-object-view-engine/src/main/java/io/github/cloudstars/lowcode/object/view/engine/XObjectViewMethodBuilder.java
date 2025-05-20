package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.object.method.engine.XObjectMethod;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;

/**
 * 视图接口构建器
 *
 * @author clouds
 * @param <P> 视图配置类型
 * @param <T> 模型接口类型
 */
public interface XObjectViewMethodBuilder<P extends XObjectViewConfig, T extends XObjectMethod> {

    /**
     * 根据视图配置构建模型接口
     *
     * @param viewConfig 视图配置
     * @return 模型接口
     */
    T build(P viewConfig);

}
