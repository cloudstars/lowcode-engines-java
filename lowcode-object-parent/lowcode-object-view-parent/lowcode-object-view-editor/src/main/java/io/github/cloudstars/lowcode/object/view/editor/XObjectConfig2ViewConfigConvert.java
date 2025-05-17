package io.github.cloudstars.lowcode.object.view.editor;

import io.github.cloudstars.lowcode.object.commons.XObjectConfig;

/**
 * 将模型配置转换为视图配置的转换器
 *
 * @param <O> 模型配置类型
 * @param <T> 视图配置类型
 */
public interface XObjectConfig2ViewConfigConvert<O extends XObjectConfig, T extends XObjectViewConfig> {

    /**
     * 将模型配置转换为视图配置
     *
     * @param objectConfig 模型配置
     * @return 视图配置
     */
    T convert(O objectConfig);

}
