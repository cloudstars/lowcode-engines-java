package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.engine.function.AbstractFunctionHandler;
import io.github.cloudstars.lowcode.formula.parser.function.FunctionConfig;

/**
 * 测试用的函数
 *
 * @author clouds
 */
public class TestFunction extends AbstractFunctionHandler {

    public TestFunction(FunctionConfig descriptor) {
        super(descriptor);
    }

    @Override
    protected Object exec(Object... params) {
        return null;
    }

}
