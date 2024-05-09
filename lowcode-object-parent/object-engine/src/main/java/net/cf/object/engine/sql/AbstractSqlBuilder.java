package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.op.*;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;
import net.cf.object.engine.oql.ast.OqlPropertyExpr;

import java.util.Arrays;
import java.util.List;

/**
 * 抽象的 SQL 构建器，用于为于 SQL 指令构建时的公共能力
 *
 * @author clouds
 */
public abstract class AbstractSqlBuilder {

    /**
     * 支持的方法名称
     */
    protected static final List<String> METHOD_NAMES = Arrays.asList("NOW", "LTRIM", "RTRIM", "TRIM", "CONCAT", "SUBSTRING", "LENGTH", "YEAR", "MONTH", "DAY");

    public AbstractSqlBuilder() {
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
     * 将本表字段转换成驱动层的标识符表达式
     *
     * @param field
     * @return
     */
    protected SqlIdentifierExpr buildSqlExpr(XField field) {
        SqlIdentifierExpr identExpr = new SqlIdentifierExpr(field.getColumnName());
        identExpr.setAutoGen(field.isAutoGen());
        identExpr.setSqlDataType(SqlDataTypeConvert.toSqlDataType(field));
        return identExpr;
    }


    /**
     * 将它表字段转换成驱动层的属性表达式
     *
     * @param field
     * @return
     */
    protected SqlPropertyExpr buildSqlRefExpr(XField field) {
        String tableName = field.getOwner().getTableName();
        SqlPropertyExpr propExpr = new SqlPropertyExpr(tableName);
        propExpr.setName(field.getColumnName());
        propExpr.setSqlDataType(SqlDataTypeConvert.toSqlDataType(field));
        return propExpr;
    }

    /**
     * 将本表字段属性转换成驱动层的标识符表达式
     *
     * @param property
     * @return
     */
    protected SqlIdentifierExpr buildSqlExpr(XProperty property) {
        SqlIdentifierExpr identExpr = new SqlIdentifierExpr(property.getColumnName());
        identExpr.setSqlDataType(SqlDataTypeConvert.toSqlDataType(property.getOwner()));
        return identExpr;
    }

    /**
     * 将它表字段属性转换成驱动层的属性表达式
     *
     * @param property
     * @return
     */
    protected SqlPropertyExpr buildSqlRefExpr(XProperty property) {
        String tableName = property.getOwner().getOwner().getTableName();
        SqlPropertyExpr propExpr = new SqlPropertyExpr(tableName);
        propExpr.setName(property.getColumnName());
        propExpr.setSqlDataType(SqlDataTypeConvert.toSqlDataType(property));
        return propExpr;
    }


    /**
     * 构建字段属性的变量引用表达式，即：#{property}
     *
     * @param property
     */
    protected SqlVariantRefExpr buildPropertyVarRefExpr(XProperty property, String prefix) {
        String propName = property.getName();
        SqlVariantRefExpr propVarRefExpr = new SqlVariantRefExpr("#{" + prefix + "." + propName + "}");
        return propVarRefExpr;
    }

    /**
     * 将一个OQL层的表达式转为SQL层的表达式
     *
     * @param x
     * @return
     */
    protected SqlExpr buildSqlExpr(final SqlExpr x) {
        // 经过OQL转换后，这两种类型已经不存在了
        assert (!(x instanceof SqlIdentifierExpr));
        assert (!(x instanceof SqlPropertyExpr));

        Class<?> clazz = x.getClass();
        if (clazz == OqlFieldExpr.class) {
            return this.buildFieldOqlExpr((OqlFieldExpr) x);
        } else if (clazz == OqlPropertyExpr.class) {
            return this.buildPropertyOqlExpr((OqlPropertyExpr) x);
        } else if (clazz == OqlObjectExpandExpr.class) {
            throw new RuntimeException("模型展开字段不能构建为驱动层的表达式");
        } else if (clazz == SqlLikeOpExpr.class) {
            return this.buildLikeSqlExpr((SqlLikeOpExpr) x);
        } else if (clazz == SqlInListExpr.class) {
            return this.buildInListSqlExpr((SqlInListExpr) x);
        } else if (clazz == SqlContainsOpExpr.class) {
            return this.buildSqlExpr((SqlContainsOpExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            return this.buildSqlExpr((SqlBinaryOpExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            return this.buildSqlExpr((SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlAggregateExpr.class) {
            return this.buildSqlExpr((SqlAggregateExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            return this.buildSqlExpr((SqlMethodInvokeExpr) x);
        } else {
            return x;
        }
    }

    /**
     * 构建SQL层的标识符，将字段名转为列名
     *
     * @param x
     * @return
     */
    private SqlExpr buildFieldOqlExpr(final OqlFieldExpr x) {
        XField resolvedField = x.getResolvedField();
        SqlDataType sqlDataType = SqlDataTypeConvert.toSqlDataType(resolvedField);
        SqlIdentifierExpr sqlX = new SqlIdentifierExpr();
        sqlX.setName(resolvedField.getColumnName());
        sqlX.setSqlDataType(sqlDataType);
        sqlX.setAutoGen(x.getResolvedField().isAutoGen());
        return sqlX;
    }

    /**
     * 字段属性转换，子属性3种形式："object.field.x" 或 "field.x"
     *
     * @param x
     * @return
     */
    private SqlExpr buildPropertyOqlExpr(final OqlPropertyExpr x) {
        XProperty property = x.getResolvedProperty();
        String columnName = property.getColumnName();
        SqlDataType sqlDataType = SqlDataTypeConvert.toSqlDataType(property.getOwner());
        SqlIdentifierExpr sqlX = new SqlIdentifierExpr();
        sqlX.setName(columnName);
        sqlX.setSqlDataType(sqlDataType);
        return sqlX;
    }

    /**
     * 构建like表达式
     *
     * @param x
     * @return
     */
    private SqlExpr buildLikeSqlExpr(final SqlLikeOpExpr x) {
        SqlLikeOpExpr sqlX = new SqlLikeOpExpr();
        sqlX.setLeft(this.buildSqlExpr(x.getLeft()));
        sqlX.setRight(this.buildSqlExpr(x.getRight()));
        return sqlX;
    }

    /**
     * 构建in表达式
     *
     * @param x
     * @return
     */
    private SqlExpr buildInListSqlExpr(final SqlInListExpr x) {
        SqlInListExpr sqlX = new SqlInListExpr();
        sqlX.setNot(x.isNot());
        sqlX.setLeft(this.buildSqlExpr(x.getLeft()));
        List<SqlExpr> targetList = x.getTargetList();
        for (SqlExpr targetItem : targetList) {
            sqlX.addTarget(this.buildSqlExpr(targetItem));
        }
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final SqlContainsOpExpr x) {
        SqlContainsOpExpr sqlX = new SqlContainsOpExpr();
        sqlX.setLeft(this.buildSqlExpr(x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.buildSqlExpr(x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final SqlBinaryOpExpr x) {
        SqlBinaryOpExpr sqlX = new SqlBinaryOpExpr();
        sqlX.setLeft(this.buildSqlExpr(x.getLeft()));
        sqlX.setOperator(x.getOperator());
        sqlX.setRight(this.buildSqlExpr(x.getRight()));
        sqlX.setParenthesized(x.isParenthesized());
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final SqlBinaryOpExprGroup x) {
        SqlBinaryOpExprGroup sqlX = new SqlBinaryOpExprGroup(x.getOperator());
        List<SqlExpr> items = x.getItems();
        for (SqlExpr item : items) {
            sqlX.addItem(this.buildSqlExpr(item));
        }

        return sqlX;
    }

    private SqlExpr buildSqlExpr(final SqlAggregateExpr x) {
        SqlAggregateExpr sqlX = new SqlAggregateExpr();
        this.copyMethodSqlExpr(x, sqlX);
        return sqlX;
    }

    private SqlExpr buildSqlExpr(final SqlMethodInvokeExpr x) {
        SqlMethodInvokeExpr sqlX = new SqlMethodInvokeExpr();
        this.copyMethodSqlExpr(x, sqlX);
        return sqlX;
    }

    /**
     * 构建方法调用表达式
     *
     * @param source
     * @param target
     */
    private void copyMethodSqlExpr(final SqlMethodInvokeExpr source, final SqlMethodInvokeExpr target) {
        target.setMethodName(source.getMethodName());
        List<SqlExpr> args = source.getArguments();
        for (SqlExpr arg : args) {
            target.addArgument(this.buildSqlExpr(arg));
        }
    }

}
