package net.cf.object.engine.object;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelectStatement;

public class XObjectTestUtils {

    private XObjectTestUtils() {
    }

    /**
     * 根据语句解析模型
     *
     * @param stmt
     * @return
     */
    public static void resolveObject(OqlSelectStatement stmt) {
        OqlObjectSource objectSource = stmt.getSelect().getFrom();
        if (objectSource instanceof OqlExprObjectSource) {
            OqlExprObjectSource exprObjectSource = (OqlExprObjectSource) objectSource;
            SqlExpr objectExpr = exprObjectSource.getExpr();
            if (objectExpr instanceof SqlIdentifierExpr) {
                String objectCode = ((SqlIdentifierExpr) objectExpr).getName();
                exprObjectSource.setResolvedObject(XObjectTestResolver.resolveObject(objectCode));
            } else if (objectExpr instanceof SqlPropertyExpr) {
                throw new RuntimeException("暂不支持SqlPropertyExpr类型的模型！");
            }
        } else {
            throw new RuntimeException("暂不支持OqlExprObjectSource之外的类型！");
        }
    }
}
