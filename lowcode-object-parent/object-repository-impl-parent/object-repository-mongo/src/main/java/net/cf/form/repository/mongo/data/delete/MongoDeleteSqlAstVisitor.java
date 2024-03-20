package net.cf.form.repository.mongo.data.delete;

import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public class MongoDeleteSqlAstVisitor implements SqlAstVisitor {

    private MongoDeleteCommandBuilder builder;

    public MongoDeleteSqlAstVisitor(MongoDeleteCommandBuilder builder) {
        this.builder = builder;
    }


    @Override
    public boolean visit(SqlExprTableSource x) {
        this.builder.setCollectionName(x.getTableName());
        return false;
    }

    @Override
    public boolean visit(SqlDeleteStatement x) {
        if (x.getWhere() != null) {
            builder.addWhere(x.getWhere());
        }
        return true;
    }
}
