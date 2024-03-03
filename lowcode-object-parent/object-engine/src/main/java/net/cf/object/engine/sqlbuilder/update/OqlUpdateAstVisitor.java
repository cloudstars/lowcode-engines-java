package net.cf.object.engine.sqlbuilder.update;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitorAdaptor;

import java.util.List;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class OqlUpdateAstVisitor extends OqlAstVisitorAdaptor {

    private final SqlUpdateStatementBuilder builder;

    public OqlUpdateAstVisitor(SqlUpdateStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlUpdateStatement x) {
        this.resolvedObject = x.getObjectSource().getResolvedObject();
        this.buildTableSource(x.getObjectSource());
        this.buildSetItems(x.getSetItems());

        // 输出where条件
        SqlExpr where = x.getWhere();
        if (where != null) {
            this.builder.where(this.buildSqlExpr(where));
        }

        return false;
    }

    /**
     * 访问数据源
     *
     * @param objectSource
     */
    private void buildTableSource(final OqlExprObjectSource objectSource) {
        SqlExprTableSource tableSource = new SqlExprTableSource();
        tableSource.setExpr(new SqlIdentifierExpr(objectSource.getResolvedObject().getTableName()));
        this.builder.tableSource(tableSource);
    }

    /**
     * 访问setItem列表
     *
     * @param setItems
     */
    private void buildSetItems(final List<SqlUpdateSetItem> setItems) {
        for (SqlUpdateSetItem setItem : setItems) {
            SqlUpdateSetItem sqlSetItem = setItem.cloneMe();
            sqlSetItem.setColumn(this.buildSqlExpr(setItem.getColumn()));
            this.builder.appendSetItem(sqlSetItem);
        }
    }
}
