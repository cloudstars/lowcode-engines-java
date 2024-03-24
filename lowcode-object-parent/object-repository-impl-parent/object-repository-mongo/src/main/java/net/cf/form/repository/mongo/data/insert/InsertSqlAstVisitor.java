package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入SQL语句AST访问器
 *
 * @author clouds
 */
@Deprecated
public class InsertSqlAstVisitor implements SqlAstVisitor {

    /**
     * 输出源
     */
    protected final InsertDocumentsBuilder builder;

    /**
     * 是否在访问 values 部分
     */
    private boolean isInValues;

    private int columnIndex = -1;

    private List<String> columnNames = new ArrayList<>();

    public InsertSqlAstVisitor(InsertDocumentsBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(SqlIdentifierExpr x) {
        if (!isInValues) {
            columnNames.add(x.getName());
        }

        return false;
    }


    @Override
    public boolean visit(SqlInsertStatement.ValuesClause insertValues) {
        this.isInValues = true;

        // 生成一个Document并将列序号重置为0
        this.builder.newDocument();
        this.columnIndex = 0;

        return true;
    }

    @Override
    public boolean visit(SqlCharExpr x) {
        if (this.isInValues) {
            String columnName = columnNames.get(this.columnIndex);
            this.builder.appendDocumentKeyValue(columnName, x.getValue());
            this.columnIndex++;
        }

        return false;
    }
}
