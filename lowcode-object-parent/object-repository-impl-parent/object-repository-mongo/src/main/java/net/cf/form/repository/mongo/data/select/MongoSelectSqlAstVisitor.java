package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.mongo.data.ExprTypeEnum;
import net.cf.form.repository.sql.ast.expr.op.SqlExistsExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public class MongoSelectSqlAstVisitor implements SqlAstVisitor {

    private MongoSelectCommandBuilder builder;


    public MongoSelectSqlAstVisitor(MongoSelectCommandBuilder builder) {
        this.builder = builder;
    }


    @Override
    public boolean visit(SqlExprTableSource x) {
        this.builder.setCollectionName(x.getTableName());
        return false;
    }

    @Override
    public boolean visit(SqlSelect x) {
        if (x.getDistinctOption() != null) {
            builder.setDistinct();
        }

        if (x.getWhere() != null) {
            builder.addWhere(x.getWhere());
        }
        if (x.getLimit() != null) {
            builder.addLimit(x.getLimit());
        }
        if (x.getOrderBy() != null) {
            builder.addOrderBy(x.getOrderBy());
        }
        if (x.getGroupBy() != null) {
            builder.addGroupBy(x.getGroupBy());
        }
        return true;
    }

    @Override
    public boolean visit(SqlExistsExpr sqlExistsExpr) {
        return false;
    }

    @Override
    public boolean visit(SqlSelectItem x) {
        MongoSelectItem mongoExpr = new MongoSelectItem();
        mongoExpr.setAlias(x.getAlias());
        mongoExpr.setSqlExpr(x.getExpr());
        mongoExpr.setExprEnum(ExprTypeEnum.match(mongoExpr.getSqlExpr()));
        builder.addSelectItems(mongoExpr);
        return true;
    }


}
