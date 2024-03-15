package net.cf.object.engine.oql.visitor;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExprGroup;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlLikeOpExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.visitor.VisitorFeature;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XFieldProperty;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;

import java.util.List;

/**
 * AST访问适配器
 *
 * @author clouds
 */
public class OqlAstVisitorAdaptor implements OqlAstVisitor {

    protected int features;

    protected XObject resolvedObject;

    public OqlAstVisitorAdaptor() {
    }

    public final boolean isEnabled(VisitorFeature feature) {
        return VisitorFeature.isEnabled(this.features, feature);
    }

    public void config(VisitorFeature feature, boolean state) {
        this.features = VisitorFeature.config(this.features, feature, state);
    }

    public int getFeatures() {
        return this.features;
    }

    public void setFeatures(int features) {
        this.features = features;
    }


    /**
     * 构建表源
     *
     * @param x
     */
    protected SqlTableSource buildSqlTableSource(OqlObjectSource x) {
        if (x instanceof OqlExprObjectSource) {
            return this.buildSqlTableSource((OqlExprObjectSource) x);
        } else {
            throw new RuntimeException("暂不支持");
        }
    }

    /**
     * 根据OQL标识符模型源构建SQL表源
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
     * @param x
     * @return
     */
    protected SqlExpr buildSqlExpr(final SqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == SqlIdentifierExpr.class) {
            return this.buildSqlExpr((SqlIdentifierExpr) x);
        } else if (clazz == SqlPropertyExpr.class) {
            return this.buildSqlExpr((SqlPropertyExpr) x);
        } else if (clazz == SqlVariantRefExpr.class) {
            return this.buildSqlExpr((SqlVariantRefExpr) x);
        } else if (clazz == SqlLikeOpExpr.class) {
            return this.buildSqlExpr((SqlLikeOpExpr) x);
        } else if (clazz == SqlInListExpr.class) {
            return this.buildSqlExpr((SqlInListExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            return this.buildSqlExpr((SqlBinaryOpExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            return this.buildSqlExpr((SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            return this.buildSqlExpr((SqlMethodInvokeExpr) x);
        } else {
            return x.cloneMe();
        }
    }

    /**
     * 构建SQL层的标识符，将字段名转为列名
     *
     * @param x
     * @return
     */
    private SqlIdentifierExpr buildSqlExpr(final SqlIdentifierExpr x) {
        String fieldName = x.getName();
        XField field = this.resolvedObject.getField(fieldName);
        // 将当前解析出来的列表、表名结果记录下来
        x.setResolvedColumn(field.getColumnName());
        x.setResolvedOwnerTable(field.getOwner().getTableName());
        SqlIdentifierExpr sqlX = x.cloneMe();
        sqlX.setName(field.getColumnName());
        return sqlX;
    }

    /**
     * 子属性转换
     *
     * @param x
     * @return
     */
    private SqlIdentifierExpr buildSqlExpr(final SqlPropertyExpr x) {
        SqlName owner = x.getOwner();
        String fieldName = owner.getName();
        XField field = this.resolvedObject.getField(fieldName);
        String propertyName = x.getName();
        XFieldProperty fieldProperty = field.getProperty(propertyName);
        SqlIdentifierExpr identifierExpr = new SqlIdentifierExpr();
        identifierExpr.setName(fieldProperty.getColumnName());
        return identifierExpr;
    }

    /**
     * 变量引用的转换
     *
     * @param x
     * @return
     */
    private SqlVariantRefExpr buildSqlExpr(final SqlVariantRefExpr x) {
        SqlVariantRefExpr sqlX = x.cloneMe();
        String fieldName = x.getVarName();
        XField field = this.resolvedObject.getField(fieldName);
        if (field != null) {
            // 可能存在in (#{ids}) 这种标识符不存在于字段中
            String columnName = field.getColumnName();
            sqlX.setVarName(columnName);
        }
        return sqlX;
    }



    /**
     * 构建like表达式
     *
     * @param x
     * @return
     */
    private SqlLikeOpExpr buildSqlExpr(final SqlLikeOpExpr x) {
        SqlLikeOpExpr sqlX = x.cloneMe();
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
    private SqlInListExpr buildSqlExpr(final SqlInListExpr x) {
        SqlInListExpr sqlX = new SqlInListExpr();
        sqlX.setLeft(this.buildSqlExpr(x.getLeft()));
        List<SqlExpr> targetList = x.getTargetList();
        for (SqlExpr targetItem : targetList) {
            sqlX.addTarget(this.buildSqlExpr(targetItem));
        }
        return sqlX;
    }

    private SqlBinaryOpExpr buildSqlExpr(final SqlBinaryOpExpr x) {
        SqlBinaryOpExpr sqlX = x.cloneMe();
        sqlX.setLeft(this.buildSqlExpr(x.getLeft()));
        sqlX.setRight(this.buildSqlExpr(x.getRight()));
        return sqlX;
    }

    private SqlBinaryOpExprGroup buildSqlExpr(final SqlBinaryOpExprGroup x) {
        return null;
    }

    private SqlMethodInvokeExpr buildSqlExpr(final SqlMethodInvokeExpr x) {
        return null;
    }


}

