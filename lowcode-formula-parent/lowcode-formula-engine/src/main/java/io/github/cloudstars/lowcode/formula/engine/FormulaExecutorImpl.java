package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.FormulaParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;

import java.util.Map;

/**
 * 公式引擎
 *
 * @author clouds
 */
public class FormulaExecutorImpl implements FormulaExecutor {

    /**
     * 编译
     *
     * @param fx
     * @return
     */
    @Override
    public Formula compile(String fx) {
        FxParser.FxContext context = FormulaParser.parse(fx);
        return new FormulaImpl(context);
    }

    /**
     * 执行一个公式
     *
     * @param fx
     * @return
     */
    @Override
    public Object execute(String fx) {
        Formula formula = compile(fx);
        return formula.execute();
    }

    @Override
    public Object execute(String fx, Map<String, Object> context) {
        return null;
    }

}
