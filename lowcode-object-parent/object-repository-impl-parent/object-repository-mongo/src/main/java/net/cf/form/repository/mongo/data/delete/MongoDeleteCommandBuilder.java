package net.cf.form.repository.mongo.data.delete;

import net.cf.form.repository.mongo.data.AbstractMongoCommandBuilder;
import net.cf.form.repository.mongo.data.MongoUtils;
import net.cf.form.repository.mongo.data.WhereBuilder;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MongoDeleteCommandBuilder extends AbstractMongoCommandBuilder<SqlDeleteStatement, MongoDeleteCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoDeleteCommandBuilder.class);

    private Map<String, Object> paramMap = null;

    private boolean enableVariable = false;


    private String collectionName;

    private SqlExpr whereExpr;

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void addWhere(SqlExpr whereExpr) {
        this.whereExpr = whereExpr;
    }

    public MongoDeleteCommandBuilder() {
    }

    public MongoDeleteCommandBuilder(Map<String, Object> paramMap) {
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.paramMap = paramMap;
            this.enableVariable = true;
        }
    }

    @Override
    public MongoDeleteCommand build() {

        Document whereDoc = buildWhere();
        MongoDeleteCommand command = new MongoDeleteCommand();
        command.setWhereDoc(whereDoc);
        command.setCollectionName(this.collectionName);
        log.info("delete sql : {}", command.getSqlExpr());
        return command;
    }

    private Document buildWhere() {
        Document document = new Document();
        if (this.whereExpr != null) {
            WhereBuilder whereBuilder = new WhereBuilder(this.whereExpr, this.paramMap);
            document = whereBuilder.build();
        }
        return document;
    }
}
