package net.cf.object.fx.parser.ast.expr;

import net.cf.object.fx.parser.ast.AbstractFxObjectImpl;

import java.util.Collections;
import java.util.List;

/**
 * 表达式实现类
 *
 * @author clouds
 */
public abstract class AbstractFxExprImpl extends AbstractFxObjectImpl implements FxExpr {

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
