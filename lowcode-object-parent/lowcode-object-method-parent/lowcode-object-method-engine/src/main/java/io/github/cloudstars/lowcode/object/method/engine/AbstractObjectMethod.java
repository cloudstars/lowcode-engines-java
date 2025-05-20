package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;

/**
 * 抽象的模型方法
 *
 * @param <R> 返回值类型
 * @param <P> 参数类型
 */
public abstract class AbstractObjectMethod<C extends XObjectMethodConfig, R extends Object, P extends Object> implements XObjectMethod<R, P> {

    protected C methodConfig;

    public AbstractObjectMethod(/*XObjectConfigResolver objectConfigResolver, */C methodConfig) {
        this.methodConfig = methodConfig;
    }

    @Override
    public XObjectMethodConfig getConfig() {
        return this.methodConfig;
    }

}
