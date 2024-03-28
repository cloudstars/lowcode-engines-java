package net.cf.form.repository.mongo.data.update;

import net.cf.form.repository.mongo.data.*;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoUpdateCommandBuilder extends AbstractMongoCommandBuilder<SqlUpdateStatement, MongoUpdateCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoUpdateCommandBuilder.class);

    private Map<String, Object> paramMap = null;

    private boolean enableVariable = false;


    private String collectionName;

    private SqlExpr whereExpr;

    private List<MongoUpdateItem> updateItems = new ArrayList<>();

    public MongoUpdateCommandBuilder() {
    }

    public MongoUpdateCommandBuilder(Map<String, Object> paramMap) {
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.paramMap = paramMap;
            this.enableVariable = true;
        }
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void addWhere(SqlExpr whereExpr) {
        this.whereExpr = whereExpr;
    }


    public void addUpdateItems(MongoUpdateItem mongoUpdateItem) {
        this.updateItems.add(mongoUpdateItem);
    }


    @Override
    public MongoUpdateCommand build() {
        Document setDoc = buildSetDoc();
        Document whereDoc = buildWhere();
        MongoUpdateCommand command = new MongoUpdateCommand();
        command.setWhereDoc(whereDoc);
        command.setCollectionName(this.collectionName);
        command.setSetDoc(setDoc);
        log.info("update sql : {} ", command.getSqlExpr());
        return command;
    }


    private Document buildSetDoc() {
        GlobalContext fieldContext = new GlobalContext(paramMap, PositionEnum.PARAM);
        fieldContext.setMongoMode(MongoMode.UPDATE);

        GlobalContext valueContext = new GlobalContext(paramMap, PositionEnum.VALUE);
        valueContext.setMongoMode(MongoMode.UPDATE);

        Document document = new Document();
        for (MongoUpdateItem updateItem : this.updateItems) {
            String field = String.valueOf(MongoExprVisitor.visit(updateItem.getFieldExpr(), fieldContext));
            Object value = MongoExprVisitor.visit(updateItem.getValueExpr(), valueContext);
            document.put(field, value);
        }

        return new Document("$set", document);
    }


    private Document buildWhere() {
        Document document = new Document();
        if (this.whereExpr != null) {
            WhereBuilder whereBuilder = new WhereBuilder(this.whereExpr, paramMap);
            document = whereBuilder.build();
        }
        return document;
    }
}
