package net.cf.form.engine.repository.mysql.statement.update;

import net.cf.form.engine.repository.sql_bak.UpdateSqlInfo;
import net.cf.form.engine.repository.util.CommaSeperatedListOutput;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlBuilder;
import net.cf.form.engine.repository.mysql.statement.OqlExprAstVisitor;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateSetItem;
import net.cf.form.engine.repository.oql.ast.statement.OqlWhereClause;

import java.util.List;

/**
 * 更新语句构建器
 *
 * @author clouds
 */
public class UpdateSqlBuilder extends AbstractSqlBuilder<UpdateSqlInfo> {

    private OqlExprAstVisitor exprAstVisitor;

    public UpdateSqlBuilder(UpdateSqlInfo sqlInfo) {
        super(sqlInfo);
    }

    @Override
    public String buildSql() {
        StringBuilder builder = new StringBuilder();
        this.buildTable(builder);
        this.buildSetItems(builder);
        this.buildWhere(builder);
        return builder.toString();
    }

    private void buildTable(StringBuilder builder) {
        String tableName = this.sqlInfo.getObject().getTableName();
        builder.append("update ").append(tableName);
    }

    private void buildSetItems(StringBuilder builder) {
        builder.append(" set ");
        List<OqlUpdateSetItem> setItems = this.sqlInfo.getStatement().getSetItems();
        CommaSeperatedListOutput.output(builder, setItems, (setItem) -> {
            String fieldSql = exprAstVisitor.getSql(setItem.getField());
            String valueSql = exprAstVisitor.getSql(setItem.getValue());
            builder.append(fieldSql).append(" = ").append(valueSql);
        });
    }

    private void buildWhere(StringBuilder builder) {
        OqlWhereClause whereClause = this.sqlInfo.getStatement().getWhereClause();
        if (whereClause != null && whereClause.getExpr() != null) {
            builder.append(" where ");
            builder.append(exprAstVisitor.getSql(whereClause.getExpr()));
        }
    }
}
