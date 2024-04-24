package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlExistsExpr;

import java.util.ArrayList;
import java.util.List;

public class MongoExistsExprVisitor {

    private List<SqlExistsExpr> sqlExistsExprList = new ArrayList<>();

    private SqlExpr sqlExpr;

    public MongoExistsExprVisitor(SqlExpr sqlExpr) {
        this.sqlExpr = sqlExpr;
    }

    public List<SqlExistsExpr> visit() {
        doVisit();
        return sqlExistsExprList;
    }

    private void doVisit() {
        if (sqlExpr == null) {
            return;
        }
        visit(sqlExpr);
    }

    private void visit(SqlExpr sqlExpr) {
        if (sqlExpr instanceof SqlExistsExpr) {
            sqlExistsExprList.add((SqlExistsExpr) sqlExpr);
        } else if (sqlExpr instanceof SqlBinaryOpExpr) {
            SqlBinaryOpExpr sqlBinaryOpExpr = (SqlBinaryOpExpr) sqlExpr;
            visit(sqlBinaryOpExpr.getLeft());
            visit(sqlBinaryOpExpr.getRight());
        } else {
            return;
        }
    }


}
