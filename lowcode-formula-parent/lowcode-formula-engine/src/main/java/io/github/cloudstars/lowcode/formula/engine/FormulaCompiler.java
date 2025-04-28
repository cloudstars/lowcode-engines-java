package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.FormulaParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParser;

/**
 * 公式编译器
 *
 * @author clouds
 */
public final class FormulaCompiler {

    /**
     * 公式解析器
     */
    private static FormulaParser PARSER = new FormulaParser();

    private FormulaCompiler() {
    }

    /**
     * 编译公式
     *
     * @param fx 公式文本
     * @return 公式
     */
    public static Formula compile(String fx) {
        FxExprParser.ProgramContext context = FormulaParser.parse(fx);
        FormulaImpl formula = new FormulaImpl(context);
        return formula;
    }

}
