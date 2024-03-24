package net.cf.object.engine.oql.util;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.parser.OqlStatementParser;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.oql.visitor.OqlAstOutputVisitor;

import java.util.ArrayList;
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
    public static List<SqlExpr> defaultExpandObjectFields(XObject object) {
        List<SqlExpr> fieldExprs = new ArrayList<>();
        List<XField> fields = object.getFields();
        for (XField field : fields) {
            // 一对多的关联表不处理
            if (field instanceof XObjectRefField && ((XObjectRefField) field).isMultiRef()) {
                continue;
            }

            String fieldName = field.getName();
            List<XProperty> properties = field.getProperties();
            if (properties != null && properties.size() > 0) {
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
}
