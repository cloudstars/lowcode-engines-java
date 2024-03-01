package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelect;
import net.cf.object.engine.oql.visitor.OqlAstVisitorAdaptor;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * OQL Select 语句 AST 访问器
 *
 * @author clouds
 */
public final class SelectOqlAstVisitor extends OqlAstVisitorAdaptor {

    private final SelectSqlStatementBuilder builder;

    public SelectOqlAstVisitor(SelectSqlStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlSelect x) {
        OqlObjectSource objectSource = x.getFrom();
        if (objectSource instanceof OqlExprObjectSource) {
            XObject object = ((OqlExprObjectSource) objectSource).getResolvedObject();
            List<SqlSelectItem> oqlSelectItems = x.getSelectItems();
            for (SqlSelectItem oqlSelectItem : oqlSelectItems) {
                SqlSelectItem sqlSelectItem = oqlSelectItem.cloneMe();
                SqlExpr expr = oqlSelectItem.getExpr();
                if (expr instanceof SqlIdentifierExpr) {
                    SqlIdentifierExpr identExpr = (SqlIdentifierExpr) expr;
                    String fieldCode = identExpr.getName();
                    XField field = object.getField(fieldCode);
                    sqlSelectItem.setExpr(new SqlIdentifierExpr(field.getColumnName()));
                    String alias = oqlSelectItem.getAlias();
                    if (!StringUtils.hasLength(alias)) {
                        // 如果没有显式指定alias，那么查询的返回结果字段名为字段名
                        alias = object.getField(((SqlIdentifierExpr) expr).getName()).getCode();
                        sqlSelectItem.setAlias(alias);
                    }
                    this.builder.appendSelectItem(sqlSelectItem);
                }
            }

            SqlExprTableSource tableSource = new SqlExprTableSource();
            tableSource.setExpr(new SqlIdentifierExpr(object.getTableName()));
            this.builder.setTableSource(tableSource);
        }

        return false;
    }
}


