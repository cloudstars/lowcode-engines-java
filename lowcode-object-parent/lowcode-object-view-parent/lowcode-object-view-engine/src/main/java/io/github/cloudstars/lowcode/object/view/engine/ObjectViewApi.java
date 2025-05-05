package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;

/**
 * 视图接口
 *
 * @author clouds
 * @param <P> 接口参数类型
 * @param <R> 接口返回值类型
 */
public interface ObjectViewApi<P extends Object, R extends Object> {

    /**
     * 获取视图接口的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取视图接口的配置
     *
     * @return
     */
    ApiConfig getApiConfig();

    /**
     * 执行视图接口
     *
     * @param data
     * @return
     */
    R execute(P data);

}
