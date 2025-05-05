package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

/**
 * 模型视图接口
 *
 * @author clouds
 * @param <C> 视图的配置类型
 */
public interface ObjectView<C extends AbstractObjectViewConfig> {

    /**
     * 执行视图API
     *
     * @param apiType API类型
     * @param apiParams API参数
     * @return
     */
    Object executeApi(String apiType, Object apiParams);
}
