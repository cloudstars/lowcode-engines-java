package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlListExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * 抽象的 SQL 指令构建器，用于将 OQL 语句（带参数）构建为 SQL 指令
 *
 *
 *
 * @author clouds
 * @param <OQL> OQL语句
 * @param <SQLCMD> SQL指令
 */
public abstract class AbstractSqlCmdBuilder<OQL extends OqlStatement, SQLCMD extends AbstractSqlCmd> extends AbstractSqlBuilder {

    /**
     * 待解析的OQL语句
     */
    protected final OQL stmt;

    /**
     * 是否批量OQL语句
     */
    protected final boolean isBatch;

    /**
     * 解析后的本模型
     */
    protected XObject resolvedObject;

    /**
     * 子表变量名展开的匹配模式
     */
    protected final Pattern VAR_EXPAND_PATTERN = Pattern.compile("(\\w+)\\((\\.+)\\)");


    public AbstractSqlCmdBuilder(OQL stmt, boolean isBatch) {
        this.stmt = stmt;
        this.isBatch = isBatch;
    }

    /**
     * 构建SQL指令
     *
     * @return
     */
    public abstract SQLCMD build();

    /**
     * 从JSON值中解析某个字段属性的值
     *
     * @param jsonValueExpr
     * @param property
     * @return
     */
    protected SqlExpr extractPropertyValue(SqlExpr jsonValueExpr, XProperty property) {
        if (!property.isArray()) {
            return this.extractPropertyValue((SqlJsonObjectExpr) jsonValueExpr, property);
        } else {
            return this.extractPropertyValue((SqlJsonArrayExpr) jsonValueExpr, property);
        }
    }

    /**
     * 从JSON对象表达式中解析某个字段属性的值
     *
     * @param objectExpr
     * @param property
     * @return
     */
    protected SqlValuableExpr extractPropertyValue(SqlJsonObjectExpr objectExpr, XProperty property) {
        return objectExpr.getItems().get(property.getName());
    }

    /**
     * 从JSON数组表达式中解析某个属性的值
     *
     * @param arrayExpr
     * @param property
     * @return
     */
    protected SqlJsonArrayExpr extractPropertyValue(SqlJsonArrayExpr arrayExpr, XProperty property) {
        List<SqlExpr> arrayExprItems = arrayExpr.getItems();
        int valuesSize = arrayExprItems.size();
        List<SqlExpr> values = new ArrayList<>();
        for (int i = 0; i < valuesSize; i++) {
            SqlExpr arrayExprItem = arrayExprItems.get(i);
            if (!(arrayExprItem instanceof SqlListExpr)) {
                throw new FastOqlException("JSON数据中的某个元数不是JSON对象");
            }
            values.add(this.extractPropertyValue((SqlJsonObjectExpr) arrayExprItem, property));
        }
        return new SqlJsonArrayExpr(values);
    }

}
