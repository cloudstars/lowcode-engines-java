package io.github.cloudstars.lowcode.object.method.editor;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;

/**
 * 模型方法配置
 *
 * @author clouds
 */
public interface XObjectMethodConfig extends XTypedConfig {

    /**
     * 获取模型的编号
     *
     * @return
     */
    String getObjectKey();

}
