package net.cf.object.engine.util;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
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
            // 引用的表不处理
            if (field instanceof XObjectRefField) {
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

    /**
     * 将模型展开语句转换为OQL查询
     *
     * @param objectExpandExpr
     * @return
     */
    public static OqlSelectStatement buildDetailObjectSelectStatement(OqlObjectExpandExpr objectExpandExpr) {
        assert (objectExpandExpr.getResolvedObjectRefField().getRefType() == ObjectRefType.DETAIL);

        OqlSelect select = new OqlSelect();
        List<SqlExpr> fields = objectExpandExpr.getFields();
        for (SqlExpr field : fields) {
            SqlExpr expr = field;
            String alias = null;
            if (field instanceof SqlSelectItem) {
                SqlSelectItem si = (SqlSelectItem) field;
                expr = si.getExpr();
                alias = si.getAlias();
            }
            select.addSelectItem(new OqlSelectItem(expr, alias));
        }

        // 关联的表
        XObject refObject = objectExpandExpr.getResolvedRefObject();

        // 设置数据源
        select.addSelectItem(new OqlSelectItem(new SqlAllColumnExpr()));
        OqlExprObjectSource from = new OqlExprObjectSource(refObject.getName());
        from.setResolvedObject(refObject);
        select.setFrom(from);

        // 设置过滤条件(where detailObject.masterId = #{masterFieldName})
        SqlBinaryOpExpr where = new SqlBinaryOpExpr();
        XField masterField = refObject.getMasterField();
        OqlFieldExpr refPrimaryFieldExpr = new OqlFieldExpr(null, masterField.getName());
        refPrimaryFieldExpr.setResolvedField(masterField);
        where.setLeft(refPrimaryFieldExpr);
        where.setOperator(SqlBinaryOperator.EQUALITY);
        where.setRight(new SqlVariantRefExpr("#{" + masterField.getName() + "}"));
        select.setWhere(where);

        return new OqlSelectStatement(select);
    }

    /**
     * 将模型展开语句转换为OQL插入
     *
     * @param objectExpandExpr
     * @return
     */
    public static OqlInsertStatement buildDetailObjectInsertStatement(OqlObjectExpandExpr objectExpandExpr, List<SqlExpr> insertValues) {
        OqlInsertInto insertInto = new OqlInsertInto();

        // 关联的表
        XObject refObject = objectExpandExpr.getResolvedRefObject();

        // 设置数据源
        OqlExprObjectSource from = new OqlExprObjectSource(refObject.getName());
        from.setResolvedObject(refObject);
        insertInto.setObjectSource(from);

        // 设置插入的列，添加主键列
        List<SqlExpr> insertFields = objectExpandExpr.getFields();
        insertInto.addFields(insertFields);
        XField masterField = refObject.getMasterField();
        OqlFieldExpr refPrimaryFieldExpr = new OqlFieldExpr(null, masterField.getName());
        refPrimaryFieldExpr.setResolvedField(masterField);
        insertInto.addField(refPrimaryFieldExpr);

        // 设置插入的值，添加主键变量引用
        for (SqlExpr insertValue : insertValues) {
            if (insertValue instanceof SqlVariantRefExpr) {
                SqlInsertStatement.ValuesClause valuesClause = new SqlInsertStatement.ValuesClause();
                for (SqlExpr insertField : insertFields) {
                    if (insertField instanceof OqlFieldExpandExpr) {
                        String varName = "#{" + ((OqlFieldExpandExpr) insertField).getOwner().getName() + "}";
                        valuesClause.addValue(new SqlVariantRefExpr(varName));
                    } else {
                        valuesClause.addValue(insertField);
                    }
                }
                valuesClause.addValue(new SqlVariantRefExpr("#{" + masterField.getName() + "}"));
                insertInto.addValues(valuesClause);
            } else {
                throw new FastOqlException("暂不支持非变量展开子表的值");
            }
        }

        return new OqlInsertStatement(insertInto);
    }

    /**
     * 将模型展开语句转换为OQL更新
     *
     * @param objectExpandExpr
     * @return
     */
    public static OqlUpdateStatement toUpdateStatement(OqlObjectExpandExpr objectExpandExpr) {
        OqlUpdateStatement stmt = new OqlUpdateStatement();
        return stmt;
    }

}
