package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.literal;

import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

/**
 * 整数表达式
 *
 * @author clouds
 */
public class SqlIntegerExpr extends AbstractSqlNumericLiteralExpr implements SqlValuableExpr {

    public SqlIntegerExpr(String value) {
        super(Integer.parseInt(value));
    }

    public SqlIntegerExpr(Number value) {
        super(value);
    }

    public SqlIntegerExpr() {
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Integer getValue() {
        return this.number.intValue();
    }

    @Override
    public SqlIntegerExpr cloneMe() {
        return new SqlIntegerExpr(this.number.intValue());
    }
}
