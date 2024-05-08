package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAggregateExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.op.*;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;
import net.cf.object.engine.oql.ast.OqlPropertyExpr;
import net.cf.object.engine.sql.SqlDataTypeConvert;

import java.util.List;

/**
 * 将OQL层的表达式转换为SQL层的表达式
 *
 * @author clouds
 */
public final class RepoExprBuilder extends AbstractOqlParser {

    public RepoExprBuilder() {
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
     * @param selfObject
     * @param x
     * @return
     */
    protected SqlExpr buildSqlExpr(final XObject selfObject, final SqlExpr x) {
        // 经过OQL转换后，这两种类型已经不存在了
        assert (!(x instanceof SqlIdentifierExpr) && !(x instanceof SqlPropertyExpr));

        Class<?> clazz = x.getClass();
        if (clazz == OqlFieldExpr.class) {
            return this.buildFieldOqlExpr(selfObject, (OqlFieldExpr) x);
        } else if (clazz == OqlPropertyExpr.class) {
            return this.buildPropertyOqlExpr(selfObject, (OqlPropertyExpr) x);
        } else if (clazz == OqlObjectExpandExpr.class) {
            throw new RuntimeException("模型展开字段不能构建为驱动层的表达式");
        } else if (clazz == SqlLikeOpExpr.class) {
            return this.buildLikeSqlExpr(selfObject, (SqlLikeOpExpr) x);
        } else if (clazz == SqlInListExpr.class) {
            return this.buildInListSqlExpr(selfObject, (SqlInListExpr) x);
        } else if (clazz == SqlContainsOpExpr.class) {
            return this.buildSqlExpr(selfObject, (SqlContainsOpExpr) x);
        } else if (clazz == SqlArrayContainsOpExpr.class) {
            return this.buildSqlExpr(selfObject, (SqlArrayContainsOpExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            return this.buildSqlExpr(selfObject, (SqlBinaryOpExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            return this.buildSqlExpr(selfObject, (SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlAggregateExpr.class) {
            return this.buildSqlExpr(selfObject, (SqlAggregateExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            return this.buildSqlExpr(selfObject, (SqlMethodInvokeExpr) x);
        } else {
            return x;
        }
    }

    /**
     * 构建SQL层的标识符，将字段名转为列名
     *
     * @param selfObject
     * @param x
     * @return
     */
    private SqlExpr buildFieldOqlExpr(final XObject selfObject, final OqlFieldExpr x) {
        XField resolvedField = x.getResolvedField();
        XObject thisObject = resolvedField.getOwner();
        SqlDataType sqlDataType = SqlDataTypeConvert.toSqlDataType(resolvedField);
        if (thisObject == selfObject) {
            // 省略表名前缀
            SqlIdentifierExpr sqlX = new SqlIdentifierExpr();
            sqlX.setName(resolvedField.getColumnName());
            sqlX.setSqlDataType(sqlDataType);
            sqlX.setAutoGen(x.getResolvedField().isAutoGen());
            return sqlX;
        } else {
            SqlPropertyExpr sqlX = new SqlPropertyExpr(selfObject.getTableName());
            sqlX.setName(resolvedField.getColumnName());
            sqlX.setSqlDataType(sqlDataType);
            return sqlX;
        }
    }

    /**
     * 字段属性转换，子属性3种形式："object.field.x" 或 "field.x"
     *
     * @param selfObject
     * @param x
     * @return
     */
    private SqlExpr buildPropertyOqlExpr(final XObject selfObject, final OqlPropertyExpr x) {
        XProperty property = x.getResolvedProperty();
        XObject thisObject = property.getOwner().getOwner();
        String columnName = property.getColumnName();
        SqlDataType sqlDataType = SqlDataTypeConvert.toSqlDataType(property.getOwner());
        if (thisObject == selfObject) {
            SqlIdentifierExpr sqlX = new SqlIdentifierExpr();
            sqlX.setName(columnName);
            sqlX.setSqlDataType(sqlDataType);
            return sqlX;
        } else {
            SqlPropertyExpr sqlX = new SqlPropertyExpr(selfObject.getTableName());
            sqlX.setName(columnName);
            sqlX.setSqlDataType(sqlDataType);
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
        sqlX.setNot(x.isNot());
        sqlX.setLeft(this.buildSqlExpr(object, x.getLeft()));
        List<SqlExpr> targetList = x.getTargetList();
        for (SqlExpr targetItem : targetList) {
            sqlX.addTarget(this.buildSqlExpr(object, targetItem));
        }
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final XObject object, final SqlContainsOpExpr x) {
        SqlContainsOpExpr sqlX = new SqlContainsOpExpr();
        sqlX.setLeft(this.buildSqlExpr(object, x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.buildSqlExpr(object, x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final XObject object, final SqlArrayContainsOpExpr x) {
        SqlArrayContainsOpExpr sqlX = new SqlArrayContainsOpExpr();
        sqlX.setLeft(this.buildSqlExpr(object, x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.buildSqlExpr(object, x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        sqlX.setOption(x.getOption());
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