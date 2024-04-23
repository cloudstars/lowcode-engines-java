package net.cf.object.engine.oql.stmt;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.parser.XObjectResolver;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractOqlParser {

    /**
     * 支持的方法名称
     */
    protected List<String> METHOD_NAMES = Arrays.asList("NOW", "LTRIM", "RTRIM", "TRIM", "CONCAT", "SUBSTRING", "LENGTH", "YEAR", "MONTH", "DAY");

    /**
     * 模型解析器
     */
    protected final XObjectResolver resolver;

    // 最后一次解析到的模型
    protected XObject lastResolvedObject;

    public AbstractOqlParser(XObjectResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * 根据模型名称解析模型
     *
     * @param objectName
     * @return
     */
    protected XObject resolveObject(String objectName) {
        XObject resolvedObject = this.resolver.resolve(objectName);
        if (resolvedObject == null) {
            throw new FastOqlException("模型" + objectName + "不存在");
        }

        this.lastResolvedObject = resolvedObject;

        return resolvedObject;
    }

    /**
     * 根据模型的字段名称解析普通字段
     *
     * @param object
     * @param fieldName
     * @return
     */
    protected XField resolveField(XObject object, String fieldName) {
        XField field = object.getField(fieldName);
        if (field == null) {
            String objectName = object.getName();
            throw new FastOqlException("模型" + objectName + "下的字段" + fieldName + "不存在");
        }

        return field;
    }

    /**
     * 根据模型的字段名称解析模型引用字段
     *
     * @param object
     * @param fieldName
     * @return
     */
    protected XObjectRefField resolveObjectRefField(XObject object, String fieldName) {
        XField field = this.resolveField(object, fieldName);
        if (!(field instanceof XObjectRefField)) {
            String objectName = object.getName();
            throw new FastOqlException("模型" + objectName + "下的字段" + fieldName + "不是一个模型引用的字段");
        }

        return (XObjectRefField) field;
    }

    /**
     * 根据模型的字段名称解析普通字段
     *
     * @param field
     * @param propName
     * @return
     */
    protected XProperty resolveProperty(XField field, String propName) {
        XProperty property = field.getProperty(propName);
        if (property == null) {
            XObject object = field.getOwner();
            String objectName = object.getName();
            String fieldName = field.getName();
            throw new FastOqlException("模型" + objectName + "下的字段" + fieldName + "下的属性" + propName + "不存在");
        }

        return property;
    }

    /**
     * 将字段转换成驱动层的表达式
     *
     * @param selfObject
     * @param field
     * @return
     */
    protected SqlExpr toRepoExpr(XObject selfObject, XField field) {
        if (selfObject == field.getOwner()) {
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
        if (selfObject == property.getOwner().getOwner()) {
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
     * 构建字段等于它自身变量的比较表达式，即：field = #{field}
     *
     * @param field
     */
    protected SqlBinaryOpExpr buildFieldEqualsVarRefExpr(XField field) {
        SqlExpr fieldExpr = this.toRepoSelfExpr(field);
        String fieldName = field.getName();
        SqlExpr fieldVarRefExpr = new SqlVariantRefExpr("#{" + fieldName + "}");
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
