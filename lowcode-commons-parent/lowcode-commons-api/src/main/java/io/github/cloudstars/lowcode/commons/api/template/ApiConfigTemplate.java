package io.github.cloudstars.lowcode.commons.api.template;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;

/**
 * API配置模板
 *
 * @param <P> 模板参数类型
 */
public interface ApiConfigTemplate<P extends Object> {

    /**
     * 获取模板代码
     *
     * @return
     */
    String getCode();

    /**
     * 获取模板名称
     *
     * @return
     */
    String getName();

    /**
     * 根据参数实例化一个API
     *
     * @param params
     * @return
     */
    ApiConfig newInstance(P params);

}
