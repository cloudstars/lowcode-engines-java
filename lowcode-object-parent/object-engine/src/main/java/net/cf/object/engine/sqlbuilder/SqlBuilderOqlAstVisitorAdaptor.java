package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExprGroup;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlLikeOpExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.ast.OqlPropertyExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.List;

/**
 * OQL转SQL访问适配器
 *
 * @author clouds
 */
public class SqlBuilderOqlAstVisitorAdaptor implements OqlAstVisitor {

    /**
     * 当前解析的OQL语句的（本）模型
     */
    protected XObject selfObject;

    public SqlBuilderOqlAstVisitorAdaptor() {
    }

    /**
     * 构建表源
     *
     * @param x
     */
    protected SqlExprTableSource buildSqlTableSource(OqlExprObjectSource x) {
        SqlExprTableSource tableSource = new SqlExprTableSource();
        tableSource.setExpr(x.getResolvedObject().getTableName());
        return tableSource;
    }

    /**
     * 将一个OQL层的表达式转为SQL层的表达式
     *
     * @param object
     * @param x
     * @return
     */
    protected SqlExpr buildSqlExpr(final XObject object, final SqlExpr x) {
        // 经过OQL转换后，这两种类型已经不存在了
        assert (!(x instanceof SqlIdentifierExpr) && !(x instanceof SqlPropertyExpr));

        Class<?> clazz = x.getClass();
        if (clazz == OqlFieldExpr.class) {
            return this.buildFieldOqlExpr(object, (OqlFieldExpr) x);
        } else if (clazz == OqlPropertyExpr.class) {
            return this.buildPropertyOqlExpr(object, (OqlPropertyExpr) x);
        } else if (clazz == SqlLikeOpExpr.class) {
            return this.buildLikeSqlExpr(object, (SqlLikeOpExpr) x);
        } else if (clazz == SqlInListExpr.class) {
            return this.buildInListSqlExpr(object, (SqlInListExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            return this.buildSqlExpr(object, (SqlBinaryOpExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            return this.buildSqlExpr(object, (SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlAggregateExpr.class) {
            return this.buildSqlExpr(object, (SqlAggregateExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            return this.buildSqlExpr(object, (SqlMethodInvokeExpr) x);
        } else {
            return x;
        }
    }

    /**
     * 构建SQL层的标识符，将字段名转为列名
     *
     * @param object
     * @param x
     * @return
     */
    private SqlExpr buildFieldOqlExpr(final XObject object, final OqlFieldExpr x) {
        XField resolvedField = x.getResolvedField();
        if (object == selfObject) {
            // 省略表名前缀
            SqlIdentifierExpr sqlX = new SqlIdentifierExpr();
            sqlX.setName(resolvedField.getColumnName());
            return sqlX;
        } else {
            SqlPropertyExpr sqlX = new SqlPropertyExpr(object.getTableName());
            sqlX.setName(resolvedField.getColumnName());
            return sqlX;
        }
    }

    /**
     * 字段属性转换，子属性3种形式："object.field.x" 或 "field.x" 或 "field(x)中的x"
     *
     * @param object
     * @param x
     * @return
     */
    private SqlExpr buildPropertyOqlExpr(final XObject object, final OqlPropertyExpr x) {
        XProperty property = x.getResolvedProperty();
        String columnName = property.getColumnName();
        if (object == this.selfObject) {
            SqlIdentifierExpr sqlX = new SqlIdentifierExpr();
            sqlX.setName(columnName);
            return sqlX;
        } else {
            SqlPropertyExpr sqlX = new SqlPropertyExpr(object.getTableName());
            sqlX.setName(columnName);
            return sqlX;
        }
    }

    /**
     * 构建like表达式
     *
     * @param x
     * @return
     */
    private SqlExpr buildLikeSqlExpr(final XObject object, final SqlLikeOpExpr x) {
        SqlLikeOpExpr sqlX = new SqlLikeOpExpr();
        sqlX.setLeft(this.buildSqlExpr(object, x.getLeft()));
        sqlX.setRight(this.buildSqlExpr(object, x.getRight()));
        return sqlX;
    }

    /**
     * 构建in表达式
     *
     * @param x
     * @return
     */
    private SqlExpr buildInListSqlExpr(final XObject object, final SqlInListExpr x) {
        SqlInListExpr sqlX = new SqlInListExpr();
        sqlX.setLeft(this.buildSqlExpr(object, x.getLeft()));
        List<SqlExpr> targetList = x.getTargetList();
        for (SqlExpr targetItem : targetList) {
            sqlX.addTarget(this.buildSqlExpr(object, targetItem));
        }
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final XObject object, final SqlBinaryOpExpr x) {
        SqlBinaryOpExpr sqlX = new SqlBinaryOpExpr();
        sqlX.setLeft(this.buildSqlExpr(object, x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.buildSqlExpr(object, x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final XObject object, final SqlBinaryOpExprGroup x) {
        SqlBinaryOpExprGroup sqlX = new SqlBinaryOpExprGroup(x.getOperator());
        List<SqlExpr> items = x.getItems();
        for (SqlExpr item : items) {
            sqlX.addItem(this.buildSqlExpr(object, item));
        }

        return sqlX;
    }

    private SqlExpr buildSqlExpr(final XObject object, final SqlAggregateExpr x) {
        SqlAggregateExpr sqlX = new SqlAggregateExpr();
        this.copyMethodSqlExpr(object, x, sqlX);
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final XObject object, final SqlMethodInvokeExpr x) {
        SqlMethodInvokeExpr sqlX = new SqlMethodInvokeExpr();
        this.copyMethodSqlExpr(object, x, sqlX);
        return sqlX;
    }

    /**
     * 构建方法调用表达式
     *
     * @param source
     * @param target
     */
    private void copyMethodSqlExpr(final XObject object, final SqlMethodInvokeExpr source, final SqlMethodInvokeExpr target) {
        target.setMethodName(source.getMethodName());
        List<SqlExpr> args = source.getArguments();
        for (SqlExpr arg : args) {
            target.addArgument(this.buildSqlExpr(object, arg));
        }
    }

}

