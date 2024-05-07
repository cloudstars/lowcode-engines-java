package net.cf.object.engine.oqlnew.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlListExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlStatement;
import net.cf.object.engine.oqlnew.cmd.AbstractOqlInfos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * 抽象的OQL指令构建器，用于将OQL语句解析为OQL执行指令信息
 *
 * @author clouds
 * @param <O> OQL语句
 * @param <I> OQL信息
 */
public abstract class AbstractOqInfoParser<O extends OqlStatement, I extends AbstractOqlInfos> extends AbstractOqlParser {

    /**
     * 待解析的OQL语句
     */
    protected final O stmt;

    /**
     * 是否批量OQL语句
     */
    protected final boolean isBatch;

    /**
     * 解析后的主模型
     */
    protected XObject masterObject;

    /**
     * 子表变量名展开的匹配模式
     */
    protected final Pattern VAR_EXPAND_PATTERN = Pattern.compile("(\\w+)\\((\\[\\.+)\\)");


    public AbstractOqInfoParser(O stmt, boolean isBatch) {
        this.stmt = stmt;
        this.isBatch = isBatch;
    }

    /**
     * 解析OQL语句为OQL信息
     *
     * @return
     */
    public abstract I parse();

    /**
     * 从JSON对象表达式中解析某个属性的值
     *
     * @param objectExpr
     * @param propName
     * @return
     */
    protected SqlValuableExpr parsePropertyValue(SqlJsonObjectExpr objectExpr, String propName) {
        return objectExpr.getItems().get(propName);
    }

    /**
     * 从JSON数组表达式中解析某个属性的值
     *
     * @param arrayExpr
     * @param propName
     * @return
     */
    protected SqlJsonArrayExpr parsePropertyValue(SqlJsonArrayExpr arrayExpr, String propName) {
        List<SqlExpr> arrayExprItems = arrayExpr.getItems();
        int valuesSize = arrayExprItems.size();
        List<SqlExpr> values = new ArrayList<>();
        for (int i = 0; i < valuesSize; i++) {
            SqlExpr arrayExprItem = arrayExprItems.get(i);
            if (!(arrayExprItem instanceof SqlListExpr)) {
                throw new FastOqlException("JSON数据中的某个元数不是JSON对象");
            }
            values.add(this.parsePropertyValue((SqlJsonObjectExpr) arrayExprItem, propName));
        }
        return new SqlJsonArrayExpr(values);
    }

}
