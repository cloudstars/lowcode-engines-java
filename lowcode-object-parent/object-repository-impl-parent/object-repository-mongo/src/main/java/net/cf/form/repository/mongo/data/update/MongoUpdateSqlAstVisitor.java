package net.cf.form.repository.mongo.data.update;

import net.cf.form.repository.mongo.data.ExprTypeEnum;
import net.cf.form.repository.mongo.data.MongoUpdateItem;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public class MongoUpdateSqlAstVisitor implements SqlAstVisitor {

    private MongoUpdateCommandBuilder builder;

    public MongoUpdateSqlAstVisitor(MongoUpdateCommandBuilder builder) {
        this.builder = builder;
    }


    @Override
    public boolean visit(SqlExprTableSource x) {
        this.builder.setCollectionName(x.getTableName());
        return false;
    }

    @Override
    public boolean visit(SqlUpdateStatement x) {
        if (x.getWhere() != null) {
            builder.addWhere(x.getWhere());
        }
        return true;
    }


    @Override
    public boolean visit(SqlUpdateSetItem setItem) {
        MongoUpdateItem mongoExpr = new MongoUpdateItem();
        mongoExpr.setValueExpr(setItem.getValue());
        mongoExpr.setFieldExpr(setItem.getColumn());
        mongoExpr.setFieldExprEnum(ExprTypeEnum.match(mongoExpr.getFieldExpr()));
        mongoExpr.setValueExprEnum(ExprTypeEnum.match(mongoExpr.getValueExpr()));
        builder.addUpdateItems(mongoExpr);
        return true;
    }




}
