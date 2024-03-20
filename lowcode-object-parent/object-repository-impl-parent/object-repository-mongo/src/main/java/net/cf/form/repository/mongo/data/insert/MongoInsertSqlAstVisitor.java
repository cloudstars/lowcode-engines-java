package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.mongo.data.MongoInsertItem;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入SQL语句AST访问器
 *
 * @author clouds
 */
public class MongoInsertSqlAstVisitor implements SqlAstVisitor {

    /**
     * 输出源
     */
    protected final MongoInsertCommandBuilder builder;

    /**
     * 是否在访问 values 部分
     */
    private boolean isInValues;

    private int columnIndex = -1;

    private List<String> columnNames = new ArrayList<>();

    public MongoInsertSqlAstVisitor(MongoInsertCommandBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(SqlExprTableSource x) {
        this.builder.setCollectionName(x.getTableName());
        return false;
    }


    @Override
    public boolean visit(SqlIdentifierExpr x) {
        if (!isInValues) {
            columnNames.add(x.getName());
            builder.addColName(x.getName());
        }

        return false;
    }


    @Override
    public boolean visit(SqlInsertStatement.ValuesClause insertValues) {
        this.isInValues = true;
        // 将列序号重置为0
        this.columnIndex = 0;
        List<MongoInsertItem> mongoInsertItems = new ArrayList<>();
        for (SqlExpr sqlExpr : insertValues.getValues()) {
            MongoInsertItem mongoInsertItem = new MongoInsertItem();
            mongoInsertItem.setValueExpr(sqlExpr);
            mongoInsertItem.setColName(this.columnNames.get(columnIndex));
            mongoInsertItems.add(mongoInsertItem);
            columnIndex ++;
        }
        builder.addInsertItems(mongoInsertItems);

        return true;
    }
}
