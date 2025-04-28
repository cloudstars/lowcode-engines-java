package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParser;

/**
 * 公式实现
 *
 * @author clouds
 */
public class FormulaImpl implements Formula {

    private FxExprParser.ProgramContext context;

    public FormulaImpl(FxExprParser.ProgramContext context) {
        this.context = context;
    }

    @Override
    public Object execute() {
        FormulaCalculateAstVisitor visitor = new FormulaCalculateAstVisitor();
        Object result = visitor.visitProgram(this.context);
        return result;
    }

}
