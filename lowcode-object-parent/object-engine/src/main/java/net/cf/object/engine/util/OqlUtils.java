package net.cf.object.engine.util;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.util.SqlUtils;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlExpr;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.visitor.OqlAstOutputVisitor;

import java.util.Arrays;
import java.util.List;

/**
 * OQL工具类
 *
 * @author clouds
 */
public class OqlUtils {

   private static List<String> SQL_METHOD_NAMES = Arrays.asList("NOW", "LTRIM", "CONCAT", "SUBSTRING", "TRIM", "RTRIM", "YEAR", "MONTH", "DAY", "LENGTH", "DATE", "TIMESTAMP", "DATE_FORMAT");

    /**
     * 判断一个方法的名称是否合法的
     *
     * @param methodName
     * @return
     */
   public static boolean isValidMethodName(String methodName) {
       return SQL_METHOD_NAMES.contains(methodName.toUpperCase());
   }

    /**
     * 获取查询字段的别名
     *
     * @param selectItemIndex
     * @return
     */
    public static String getSelectItemIndexAlias(int selectItemIndex) {
        return "_" + selectItemIndex;
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
     * 根据模型生成模型源表达式
     *
     * @param object
     * @return
     */
    public static OqlExprObjectSource buildObjectSource(XObject object) {
        OqlExprObjectSource objectSource = new OqlExprObjectSource();
        objectSource.setExpr(new SqlIdentifierExpr(object.getName()));
        objectSource.setResolvedObject(object);
        return objectSource;
    }

    /**
     * 根据字段构建OqlExpr
     *
     * @param field
     * @return
     */
    public static OqlFieldExpr buildFieldExpr(XField field) {
        String fieldName = field.getName();
        OqlFieldExpr fieldExpr = new OqlFieldExpr(fieldName);
        fieldExpr.setResolvedField(field);
        return fieldExpr;
    }

    /**
     * 根据字段构建OqlExpr
     *
     * @param field
     * @return
     */
    public static SqlVariantRefExpr buildFieldVarExpr(XField field) {
        return SqlUtils.buildSqlVariantRefExpr(field.getName());
    }

    /**
     * 构建字段等于它自身变量的比较表达式，即：field = #{field}
     *
     * @param field
     */
    public static SqlBinaryOpExpr buildFieldEqualsVarRefExpr(XField field) {
        OqlExpr fieldExpr = OqlUtils.buildFieldExpr(field);
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
    public static SqlInListExpr buildFieldInListVarRefExpr(XField field) {
        return OqlUtils.buildFieldInListVarRefExpr(field, false);
    }

    /**
     * 构建字段属于它自身变量的复数形式的比较表达式，即：field in (#{fields})
     *
     * @param field
     */
    public static SqlInListExpr buildFieldInListVarRefExpr(XField field, boolean not) {
        OqlExpr fieldExpr = OqlUtils.buildFieldExpr(field);
        String fieldName = field.getName();
        SqlExpr fieldVarRefExpr = new SqlVariantRefExpr("#{" + fieldName + "s}");
        SqlInListExpr fieldInListVarRefExpr = new SqlInListExpr();
        fieldInListVarRefExpr.setLeft(fieldExpr);
        fieldInListVarRefExpr.setNot(not);
        fieldInListVarRefExpr.setTargetList(Arrays.asList(fieldVarRefExpr));
        return fieldInListVarRefExpr;
    }

}
