package io.github.cloudstars.lowcode.formula.commons.util;

import io.github.cloudstars.lowcode.formula.commons.ast.FxObject;
import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstOutputVisitor;

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
