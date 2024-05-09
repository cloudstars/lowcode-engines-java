package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * Where子句解析器
 * <p>
 * 约束：
 * 1、不允许出现和本模型无关的模型
 * 2、分组最多嵌套一层，如允许：a = 1 or b = c 或者 (a = 1 and b = 2) or (c = 1 and d = 3)，不允许：(a = 1 and b = 2 and (c = d and d = 3)) or (c = 1 and d = 3)
 * 3、二元操作符的左值和右值对应的模型必须是同一个，如这样不允许：refField.b = a，refField.b是引用表的字段，a是本表的字段
 * 优化：
 * 1、同一个最底层分组里的标识符，属于同一个模型的表达式组合在一起, a = 1 and b = 2 and refField.a = 1 and refField.b = 2, a = 1 and b = 2 and (refField.a = 1 or refField.b = 2)
 * 2、对于非本表的二元操作表达式，转换为exists子查询
 *
 * @author clouds
 */
public class WhereAstVisitor implements OqlAstVisitor {

    // 本模型
   private XObject selfObject;

    /**
     * 主键字段的值
     */
    private SqlExpr primaryFieldValueExpr;

    public WhereAstVisitor(XObject selfObject) {
        this.selfObject = selfObject;
    }

    public SqlExpr getPrimaryFieldValueExpr() {
        return primaryFieldValueExpr;
    }

    @Override
    public boolean visit(SqlInListExpr x) {
        this.parsePrimaryFieldValueExpr(x);

        return this.primaryFieldValueExpr == null;
    }

    @Override
    public boolean visit(SqlBinaryOpExpr x) {
        this.parsePrimaryFieldValueExpr(x);

        return this.primaryFieldValueExpr == null;
    }

    /**
     * 解析主表记录ID的值表达式
     *
     * @param x
     */
    private void parsePrimaryFieldValueExpr(SqlBinaryOpExpr x) {
        SqlExpr left = x.getLeft();
        if (left instanceof OqlFieldExpr) {
            XField resolvedField = ((OqlFieldExpr) left).getResolvedField();
            if (resolvedField == this.selfObject.getPrimaryField()) {
                SqlBinaryOperator operator = x.getOperator();
                if (operator == SqlBinaryOperator.EQUALITY || operator == SqlBinaryOperator.IN) {
                    this.primaryFieldValueExpr = x.getRight();
                }
            }
        }
    }

}
