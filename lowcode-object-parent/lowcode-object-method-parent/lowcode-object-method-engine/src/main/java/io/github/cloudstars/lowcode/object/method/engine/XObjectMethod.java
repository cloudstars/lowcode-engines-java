package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;

/**
 * 模型接口
 *
 * @param <R> 返回值类型
 * @param <P> 参数类型
 */
public interface XObjectMethod<P extends Object, R extends Object> {

    /**
     * 获取模型接口的配置
     *
     * @return
     */
    XObjectMethodConfig getConfig();

    /**
     * 执行模型接口
     *
     * @param methodParams 接口参数
     * @return 接口执行结果
     */
    R execute(P methodParams);

}
