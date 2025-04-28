package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;

/**
 * 公式实现
 *
 * @author clouds
 */
public class FormulaImpl implements Formula {

    private FxParser.FxContext context;

    public FormulaImpl(FxParser.FxContext context) {
        this.context = context;
    }

    @Override
    public Object execute() {
        FormulaCalculateFxVisitor visitor = new FormulaCalculateFxVisitor();
        Object result = visitor.visitFx(this.context);
        return result;
    }

}
