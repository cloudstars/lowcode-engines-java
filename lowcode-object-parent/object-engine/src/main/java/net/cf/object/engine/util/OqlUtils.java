package net.cf.object.engine.util;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.parser.OqlStatementParser;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.oql.visitor.OqlAstOutputVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OQL工具类
 *
 * @author clouds
 */
public class OqlUtils {

    /**
     * 创建一个输出访问器
     *
     * @param out
     * @return
     */
    public static OqlAstOutputVisitor createAstOutputVisitor(Appendable out) {
        return new OqlAstOutputVisitor(out);
    }

    /**
     * 将AST表达式转为字符串
     *
     * @param expr
     * @return
     */
    public static String expr2String(SqlExpr expr) {
        StringBuilder builder = new StringBuilder();
        OqlAstOutputVisitor visitor = new OqlAstOutputVisitor(builder);
        expr.accept(visitor);
        return builder.toString();
    }

    /**
     * 解析并返回唯一的一条插入 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlInsertStatement parseSingleInsertStatement(XObjectResolver resolver, String oql) {
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlInsertStatement);
        return (OqlInsertStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条更新 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlUpdateStatement parseSingleUpdateStatement(XObjectResolver resolver, String oql) {
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlUpdateStatement);
        return (OqlUpdateStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条删除 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlDeleteStatement parseSingleDeleteStatement(XObjectResolver resolver, String oql) {
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlDeleteStatement);
        return (OqlDeleteStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条查询 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlSelectStatement parseSingleSelectStatement(XObjectResolver resolver, String oql) {
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlSelectStatement);
        return (OqlSelectStatement) statements.get(0);
    }


    /**
     * 默认展开模型的全部字段
     *
     * @param object 待展开的模型
     * @return 展开后的字段表达式列表
     */
    public static List<OqlExpr> defaultExpandObjectFields(XObject object) {
        List<OqlExpr> fieldExprs = new ArrayList<>();
        List<XField> fields = object.getFields();
        for (XField field : fields) {
            // 非本表字段不处理
            if (field instanceof XObjectRefField) {
                XObjectRefField objectRefField = (XObjectRefField) field;
                if (objectRefField.getRefType() == ObjectRefType.DETAIL) {
                    continue;
                }
            }

            String fieldName = field.getName();
            List<XProperty> properties = field.getProperties();
            if (properties != null && properties.size() > 0) {
                // 默认把所有的字段展开
                OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(fieldName);
                fieldExpandExpr.setDefaultExpanded(true);
                fieldExpandExpr.setResolvedField(field);
                List<OqlPropertyExpr> propExprs = OqlUtils.defaultExpandFieldProperties(field);
                fieldExpandExpr.addProperties(propExprs);
                fieldExprs.add(fieldExpandExpr);
            } else {
                OqlFieldExpr fieldExpr = new OqlFieldExpr();
                fieldExpr.setName(fieldName);
                fieldExpr.setResolvedField(field);
                fieldExprs.add(fieldExpr);
            }
        }

        return fieldExprs;
    }

    /**
     * 默认的主键字段
     *
     * @param object
     * @return
     */
    public static OqlExpr defaultPrimaryField(XObject object) {
        XField primaryField = object.getPrimaryField();
        return OqlUtils.defaultFieldExpr(primaryField);
    }

    /**
     * 默认展开字段的全部属性
     *
     * @param field 待展开的字段
     * @return 展开后的属性表达式列表
     */
    public static List<OqlPropertyExpr> defaultExpandFieldProperties(XField field) {
        List<OqlPropertyExpr> propExprs = new ArrayList<>();
        List<XProperty> properties = field.getProperties();
        for (XProperty property : properties) {
            String propName = property.getName();
            OqlPropertyExpr propExpr = new OqlPropertyExpr(null, propName);
            propExpr.setResolvedProperty(property);
            propExprs.add(propExpr);
        }

        return propExprs;
    }

    /**
     * 根据字段构建OqlExpr
     *
     * @param field
     * @return
     */
    public static OqlExpr defaultFieldExpr(XField field) {
        String fieldName = field.getName();
        if (field.getProperties() != null && field.getProperties().size() > 0) {
            OqlFieldExpandExpr fieldExpandExpr = new OqlFieldExpandExpr(fieldName);
            fieldExpandExpr.setDefaultExpanded(true);
            fieldExpandExpr.setResolvedField(field);
            return fieldExpandExpr;
        } else {
            OqlFieldExpr fieldExpr = new OqlFieldExpr(fieldName);
            fieldExpr.setResolvedField(field);
            return fieldExpr;
        }
    }

    /**
     * 构建字段等于它自身变量的比较表达式，即：field = #{field}
     *
     * @param field
     */
    public static SqlBinaryOpExpr buildFieldEqualsVarRefExpr(XField field) {
        OqlExpr fieldExpr = OqlUtils.defaultFieldExpr(field);
        String fieldName = field.getName();
        SqlExpr fieldVarRefExpr = new SqlVariantRefExpr("#{" + fieldName + "}");
        SqlBinaryOpExpr fieldEqualsVarRefExpr = new SqlBinaryOpExpr();
        fieldEqualsVarRefExpr.setLeft(fieldExpr);
        fieldEqualsVarRefExpr.setOperator(SqlBinaryOperator.EQUALITY);
        fieldEqualsVarRefExpr.setRight(fieldVarRefExpr);
        return fieldEqualsVarRefExpr;
    }


    /**
     * 构建字段属于它自身变量的复数形式的比较表达式，即：field = #{fields}
     *
     * @param field
     */
    public static SqlInListExpr buildFieldInListVarRefExpr(XField field) {
        OqlExpr fieldExpr = OqlUtils.defaultFieldExpr(field);
        String fieldName = field.getName();
        SqlExpr fieldVarRefExpr = new SqlVariantRefExpr("#{" + fieldName + "s}");
        SqlInListExpr fieldInListVarRefExpr = new SqlInListExpr();
        fieldInListVarRefExpr.setLeft(fieldExpr);
        fieldInListVarRefExpr.setTargetList(Arrays.asList(fieldVarRefExpr));
        return fieldInListVarRefExpr;
    }

    /**
     * 表达式列表中是否包含子表
     *
     * @param stmt
     * @return
     */
    public static boolean hasDetailSetItems(OqlUpdateStatement stmt) {
        List<OqlUpdateSetItem> setItems = stmt.getSetItems();
        for (OqlUpdateSetItem setItem : setItems) {
            OqlExpr field = setItem.getField();
            if (field instanceof OqlObjectExpandExpr) {
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
                if (objectExpandExpr.getResolvedObjectRefField().getRefType() == ObjectRefType.DETAIL) {
                    return true;
                }
            }
        }

        return false;
    }

}
