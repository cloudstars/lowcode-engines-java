package net.cf.object.engine.sqlbuilder.update;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlFieldExpandExpr;
import net.cf.object.engine.oql.ast.OqlPropertyExpr;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitorAdaptor;

import java.util.List;
import java.util.Map;

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
            SqlExpr updateField = setItem.getColumn();
            SqlExpr updateValue = setItem.getValue();
            if (updateField instanceof OqlFieldExpandExpr) {
                this.expandSetExpandedField((OqlFieldExpandExpr) updateField, updateValue);
            } else {
                SqlUpdateSetItem sqlSetItem = setItem.cloneMe();
                sqlSetItem.setColumn(this.buildSqlExpr(updateField));
                sqlSetItem.setValue(this.buildSqlExpr(setItem.getValue()));
                this.builder.appendSetItem(sqlSetItem);
            }
        }
    }

    /**
     * 展开set带子属性的字段
     * @param fieldExpandExpr
     * @param updateValue
     */
    private void expandSetExpandedField(OqlFieldExpandExpr fieldExpandExpr, SqlExpr updateValue) {
        XField field = fieldExpandExpr.getResolvedField();
        List<SqlIdentifierExpr> properties = fieldExpandExpr.getProperties();
        if (updateValue instanceof SqlJsonObjectExpr) {
            SqlJsonObjectExpr jsonObjectExpr = (SqlJsonObjectExpr) updateValue;
            Map<String, SqlExpr> items = jsonObjectExpr.getItems();
            for (SqlIdentifierExpr property : properties) {
                OqlPropertyExpr propertyExpr = new OqlPropertyExpr(field, property.getName());
                SqlExpr updateItem = this.buildSqlExpr(propertyExpr);
                SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                sqlSetItem.setColumn(updateItem);
                SqlExpr itemValue = items.get(property.getName());
                sqlSetItem.setValue(itemValue);
                this.builder.appendSetItem(sqlSetItem);
            }
        } else if (updateValue instanceof SqlVariantRefExpr) {
            SqlVariantRefExpr variantRefExpr = (SqlVariantRefExpr) updateValue;
            String varName = variantRefExpr.getVarName();
            for (SqlIdentifierExpr property : properties) {
                SqlUpdateSetItem sqlSetItem = new SqlUpdateSetItem();
                String propVarName = varName + "." + property.getName();
                sqlSetItem.setColumn(new SqlIdentifierExpr(propVarName));
                SqlVariantRefExpr propVariableRefExpr = new SqlVariantRefExpr();
                propVariableRefExpr.setVarName(propVarName);
                sqlSetItem.setValue(propVariableRefExpr);
                this.builder.appendSetItem(sqlSetItem);
            }
        } else {
            throw new FastOqlException("set子句中展开字段的设值必须是JSON或者变量引用，当前值：" + updateValue);
        }
    }
}
