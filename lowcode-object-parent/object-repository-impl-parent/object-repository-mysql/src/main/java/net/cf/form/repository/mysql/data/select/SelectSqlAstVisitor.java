package net.cf.form.repository.mysql.data.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.visitor.SqlAstVisitorAdaptor;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 查询SQL语句AST访问器
 *
 * @author clouds
 */
public class SelectSqlAstVisitor extends SqlAstVisitorAdaptor {

    private final StringBuilder builder;

    public SelectSqlAstVisitor(StringBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(SqlSelect x) {
        SqlTableSource tableSource = x.getFrom();
        this.builder.append("select ");

        if (tableSource instanceof SqlExprTableSource) {
            String tableName = ((SqlExprTableSource) tableSource).getTableName();
            List<SqlSelectItem> selectItems = x.getSelectItems();
            int i = 0;
            for (SqlSelectItem selectItem : selectItems) {
                if (i++ > 0) {
                    this.builder.append(", ");
                }
                SqlExpr expr = selectItem.getExpr();
                if (expr instanceof SqlIdentifierExpr) {
                    String columnName = ((SqlIdentifierExpr) expr).getName();
                    this.builder.append(columnName);
                    String alias = selectItem.getAlias();
                    if (!StringUtils.hasLength(alias)) {
                        this.builder.append(" as ").append(alias);
                    }
                }
            }

            this.builder.append(" from ");
            this.builder.append(tableName);
        }

        return false;
    }

}
