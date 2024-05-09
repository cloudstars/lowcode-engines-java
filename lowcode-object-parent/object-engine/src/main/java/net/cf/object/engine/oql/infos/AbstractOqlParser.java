package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XObject;

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
     * 抽取where中的主表记录ID表达式
     *
     * @param object
     * @param where
     * @return
     */
    public SqlExpr extractMasterIdInWhere(XObject object, SqlExpr where) {
        WhereAstVisitor visitor = new WhereAstVisitor(object);
        where.accept(visitor);
        return visitor.getPrimaryFieldValueExpr();
    }

}
