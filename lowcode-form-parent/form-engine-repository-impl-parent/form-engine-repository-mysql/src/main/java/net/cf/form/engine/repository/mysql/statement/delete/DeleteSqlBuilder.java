package net.cf.form.engine.repository.mysql.statement.delete;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.sql_bak.DeleteSqlInfo;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlBuilder;
import net.cf.form.engine.repository.oql.ast.statement.OqlWhereClause;

/**
 * 删除语句构建器
 *
 * @author clouds
 */
public class DeleteSqlBuilder extends AbstractSqlBuilder<DeleteSqlInfo> {

    public DeleteSqlBuilder(DeleteSqlInfo sqlInfo) {
        super(sqlInfo);
    }

    @Override
    public String buildSql() {
        DataObject object = this.sqlInfo.getObject();
        String tableName = object.getTableName();

        StringBuilder builder = new StringBuilder();
        builder.append("delete from ").append(tableName);
        OqlWhereClause whereClause = this.sqlInfo.getWhereClause();
        if (whereClause != null && whereClause.getExpr() != null) {
            builder.append(" where ");
            builder.append(exprAstVisitor.getSql(whereClause.getExpr()));
        }

        return builder.toString();
    }
}
