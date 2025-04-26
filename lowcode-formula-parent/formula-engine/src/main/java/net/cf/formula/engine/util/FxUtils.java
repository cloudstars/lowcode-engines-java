package net.cf.formula.engine.util;

import net.cf.formula.engine.ast.FxObject;
import net.cf.formula.engine.visitor.FxAstOutputVisitor;

public class FxUtils {

    public static FxAstOutputVisitor createOutputVisitor(Appendable out) {
        return new FxAstOutputVisitor(out);
    }

    public static String toFxString(FxObject object) {
        StringBuilder out = new StringBuilder();
        object.accept(new FxAstOutputVisitor(out));
        return out.toString();
    }

}
