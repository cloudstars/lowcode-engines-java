package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.commons.lang.util.ObjectRef;
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
        ObjectRef<Object> resultRef = new ObjectRef<>();
        FormulaCalculateFxVisitor visitor = new FormulaCalculateFxVisitor(resultRef);
        visitor.visitFx(this.context);
        return resultRef.getRef();
    }

}
