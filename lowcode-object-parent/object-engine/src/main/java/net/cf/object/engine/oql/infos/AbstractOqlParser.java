package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.sql.SqlDataTypeConvert;

import java.util.Arrays;
import java.util.List;

/**
 * 抽象的OQL解析器，用于提供一些公共的能力
 *
 * @author clouds
 */
public abstract class AbstractOqlParser {

    /**
     * 支持的方法名称
     */
    protected static final List<String> METHOD_NAMES = Arrays.asList("NOW", "LTRIM", "RTRIM", "TRIM", "CONCAT", "SUBSTRING", "LENGTH", "YEAR", "MONTH", "DAY");

    public AbstractOqlParser() {}

    /**
     * 将字段转换成驱动层的表达式
     *
     * @param selfObject
     * @param field
     * @return
     */
    protected SqlExpr toRepoExpr(XObject selfObject, XField field) {
        XObject thisObject = field.getOwner();
        if (thisObject == selfObject) {
            return this.toRepoSelfExpr(field);
        } else {
            return this.toRepoRefExpr(field);
        }
    }

    /**
     * 将字段属性转换成驱动层的表达式
     *
     * @param selfObject
     * @param property
     * @return
     */
    protected SqlExpr toRepoExpr(XObject selfObject, XProperty property) {
        XObject thisObject = property.getOwner().getOwner();
        if (thisObject == selfObject) {
            return this.toRepoSelfExpr(property);
        } else {
            return this.toRepoRefExpr(property);
        }
    }

    /**
     * 将本表字段转换成驱动层的标识符表达式
     *
     * @param field
     * @return
     */
    protected SqlIdentifierExpr toRepoSelfExpr(XField field) {
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
    protected SqlPropertyExpr toRepoRefExpr(XField field) {
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
    protected SqlIdentifierExpr toRepoSelfExpr(XProperty property) {
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
    protected SqlPropertyExpr toRepoRefExpr(XProperty property) {
        String tableName = property.getOwner().getOwner().getTableName();
        SqlPropertyExpr propExpr = new SqlPropertyExpr(tableName);
        propExpr.setName(property.getColumnName());
        return propExpr;
    }

    /**
     * 构建字段的变量引用表达式，即：#{field}
     *
     * @param field
     */
    protected SqlVariantRefExpr buildFieldVarRefExpr(XField field) {
        String fieldName = field.getName();
        SqlVariantRefExpr fieldVarRefExpr = new SqlVariantRefExpr("#{" + fieldName + "}");
        return fieldVarRefExpr;
    }

    /**
     * 构建字段等于它自身变量的比较表达式，即：field = #{field}
     *
     * @param field
     */
    protected SqlBinaryOpExpr buildFieldEqualsVarRefExpr(XField field) {
        SqlExpr fieldExpr = this.toRepoSelfExpr(field);
        SqlExpr fieldVarRefExpr = SqlVariantRefExpr.fromVarName(field.getName());
        SqlBinaryOpExpr fieldEqualsVarRefExpr = new SqlBinaryOpExpr();
        fieldEqualsVarRefExpr.setLeft(fieldExpr);
        fieldEqualsVarRefExpr.setOperator(SqlBinaryOperator.EQUALITY);
        fieldEqualsVarRefExpr.setRight(fieldVarRefExpr);
        return fieldEqualsVarRefExpr;
    }

    /**
     * 构建字段属于它自身变量的复数形式的比较表达式，即：field in (#{fields})
     *
     * @param field
     */
    protected SqlInListExpr buildFieldInListVarRefExpr(XField field) {
        SqlExpr fieldExpr = this.toRepoSelfExpr(field);
        String fieldName = field.getName();
        SqlExpr fieldVarRefExpr = new SqlVariantRefExpr("#{" + fieldName + "s}");
        SqlInListExpr fieldInListVarRefExpr = new SqlInListExpr();
        fieldInListVarRefExpr.setLeft(fieldExpr);
        fieldInListVarRefExpr.setTargetList(Arrays.asList(fieldVarRefExpr));
        return fieldInListVarRefExpr;
    }

}
