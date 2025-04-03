package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL 查询的字段
 *
 * 正常情况下，SqlSelectItem不需要实现SqlExpr，只需要实现SqlObject。
 * 继承了SqlExpr是因为OQL语句的解析时，用了SQL解析器，先把文本转为SQL，将SQL语句转换为了OQL语句，
 * 同时OQL语句的展开子句中存在如下子句：object(1 as f1, f2 as fx)、field('1' as prop1, prop2 as propX)
 * 为了能用到SqlExprParser的exprList的解析过程，所以续承了SqlExpr，后续可以改进，提供OqlExprParser来处理这一种情况
 *
 * @author clouds
 */
public class SqlSelectItem extends AbstractSqlObjectImpl implements SqlReplaceable, SqlExpr {

    /**
     * 字段表达式
     */
    protected SqlExpr expr;

    /**
     * 别名
     */
    protected String alias;

    public SqlSelectItem() {
    }

    public SqlSelectItem(SqlExpr expr) {
        this(expr, null);
    }

    public SqlSelectItem(SqlExpr expr, String alias) {
        this.expr = expr;
        this.alias = alias;
        this.setExpr(expr);
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void accept(SqlAstVisitor v) {
        if (v.visit(this)) {
            if (this.expr != null) {
                this.expr.accept(v);
            }
        }

        v.endVisit(this);
    }


    @Override
    public SqlDataType computeSqlDataType() {
        return null;
    }

    @Override
    public SqlSelectItem cloneMe() {
        SqlSelectItem x = new SqlSelectItem();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr.cloneMe());
        }

        return x;
    }


    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.expr == expr) {
            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}

