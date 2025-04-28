package io.github.cloudstars.lowcode.formula.engine;

/**
 * 函数句柄
 *
 * @author clouds
 */
public interface FunctionHandler {

    /**
     * 函数调用
     *
     * @param params
     * @return
     */
    Object call(Object... params);

}
