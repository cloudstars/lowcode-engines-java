package net.cf.form.engine.repository.mysql.statement.insert;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.sql_bak.InsertSqlInfo;
import net.cf.form.engine.repository.util.CommaSeperatedListOutput;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlBuilder;
import net.cf.form.engine.repository.mysql.statement.OqlExprAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertValues;

import java.util.List;

/**
 * 插入语句构建器
 *
 * @author clouds
 */
public class InsertSqlBuilder extends AbstractSqlBuilder<InsertSqlInfo> {

    private OqlExprAstVisitor exprAstVisitor;

    public InsertSqlBuilder(InsertSqlInfo sqlInfo) {
        super(sqlInfo);
    }

    @Override
    protected String buildSql() {
        StringBuilder builder = new StringBuilder();
        this.buildTable(builder);
        this.buildColumns(builder);
        this.buildValues(builder);
        return builder.toString();
    }

    /**
     * 构建表
     *
     * @param builder
     */
    private void buildTable(StringBuilder builder) {
        DataObject object = sqlInfo.getObject();
        builder.append("insert into ").append(object.getTableName());
    }

    /**
     * 构建插入的列
     *
     * @param builder
     */
    private void buildColumns(StringBuilder builder) {
        DataObject object = sqlInfo.getObject();
        OqlInsertStatement statement = sqlInfo.getStatement();
        List<QqlExpr> fields = statement.getInsertInto().getFields();
        builder.append("(");
        CommaSeperatedListOutput.output(builder, fields, (field) -> {
            OqlIdentifierExpr identField = (OqlIdentifierExpr) field;
            DataField resolvedField = object.getField(identField.getName());
            builder.append(resolvedField.getColumnName());
        });
        builder.append(")");
    }

    /**
     * 构建插入的值
     *
     * @param builder
     */
    private void buildValues(StringBuilder builder) {
        OqlInsertStatement statement = sqlInfo.getStatement();
        List<OqlInsertValues> valuesList = statement.getInsertInto().getValuesList();
        builder.append(" values ");
        CommaSeperatedListOutput.output(builder, valuesList, (values) -> {
            List<QqlExpr> columnValues = values.getValues();
            builder.append("(");
            CommaSeperatedListOutput.output(builder, columnValues, (columnValue) -> {
                exprAstVisitor.append(columnValue, builder);
            });
            builder.append(")");
        });
    }
}
