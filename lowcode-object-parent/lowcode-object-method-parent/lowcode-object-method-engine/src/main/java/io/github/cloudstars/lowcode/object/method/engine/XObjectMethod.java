package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;
import io.github.cloudstars.object.core.engine.XObject;

/**
 * 模型方法
 *
 * @param <R> 返回值类型
 * @param <P> 参数类型
 */
public interface XObjectMethod<R extends Object, P extends Object> {

    /**
     * 获取模型方法的配置
     *
     * @return
     */
    XObjectMethodConfig getObjectMethodConfig();

    /**
     * 执行
     *
     * @param object 模型
     * @return 影响行数
     */
    R execute(XObject object);

}
