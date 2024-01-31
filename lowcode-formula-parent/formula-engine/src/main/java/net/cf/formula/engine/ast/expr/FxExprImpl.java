package net.cf.formula.engine.ast.expr;

import net.cf.formula.engine.ast.FxObjectImpl;

import java.util.Collections;
import java.util.List;

/**
 * 表达式实现类
 *
 * @author clouds
 */
public abstract class FxExprImpl extends FxObjectImpl implements FxExpr {

    /**
     * 克隆
     *
     * @return
     */
    public abstract FxExpr clone();

    /**
     * 获取儿子节点
     *
     * @return
     */
    public List<FxExpr> getChildren() {
        return Collections.EMPTY_LIST;
    }

}
