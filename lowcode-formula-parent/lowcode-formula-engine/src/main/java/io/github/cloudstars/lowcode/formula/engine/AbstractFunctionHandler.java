package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.function.FunctionDescriptor;

/**
 * 抽象的函数句柄
 *
 * @author clouds
 */
public abstract class AbstractFunctionHandler implements FunctionHandler {

    private FunctionDescriptor descriptor;

    public AbstractFunctionHandler(FunctionDescriptor descriptor) {
        this.descriptor = descriptor;
    }


    @Override
    public Object call(Object... params) {
        // 结合 descriptor 对 params 作参数校验

        return null;
    }

    /**
     * 执行函数
     *
     * @param params 调用参数
     * @return 执行结果（无结果时返回null）
     */
    protected abstract Object exec(Object... params);

}
