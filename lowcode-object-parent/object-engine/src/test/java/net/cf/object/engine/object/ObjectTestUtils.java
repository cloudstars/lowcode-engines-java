package net.cf.object.engine.object;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;

public class ObjectTestUtils {

    private ObjectTestUtils() {
    }

    /**
     * 根据模型源解析模型
     *
     * @param objectSource
     * @return
     */
    public static void resolveObject(OqlObjectSource objectSource) {
        if (objectSource instanceof OqlExprObjectSource) {
            OqlExprObjectSource exprObjectSource = (OqlExprObjectSource) objectSource;
            if (exprObjectSource.getResolvedObject() != null) {
                return;
            }

            SqlExpr objectExpr = exprObjectSource.getExpr();
            if (objectExpr instanceof SqlIdentifierExpr) {
                String objectCode = ((SqlIdentifierExpr) objectExpr).getName();
                exprObjectSource.setResolvedObject(ObjectTestResolver.resolveObject(objectCode));
            } else if (objectExpr instanceof SqlPropertyExpr) {
                throw new RuntimeException("暂不支持SqlPropertyExpr类型的模型！");
            }
        } else {
            throw new RuntimeException("暂不支持OqlExprObjectSource之外的类型！");
        }
    }
}
