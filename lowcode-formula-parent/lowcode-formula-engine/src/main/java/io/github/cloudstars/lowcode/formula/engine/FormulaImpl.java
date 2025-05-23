package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;

import java.util.Map;

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
        CalculateFxVisitor visitor = new CalculateFxVisitor();
        Object result = visitor.visitFx(this.context);
        return result;
    }

    @Override
    public Object execute(Map<String, Object> dataMap) {
        CalculateFxVisitor visitor = new CalculateFxVisitor(dataMap);
        Object result = visitor.visitFx(this.context);
        return result;
    }
}
