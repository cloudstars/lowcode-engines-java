package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.mongo.data.*;
import net.cf.form.repository.mongo.data.delete.MongoDeleteCommandBuilder;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoInsertCommandBuilder extends AbstractMongoCommandBuilder<SqlInsertStatement, MongoInsertCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoInsertCommandBuilder.class);

    private Map<String, Object> paramMap = null;

    private boolean enableVariable = false;

    private final List<Document> documents = new ArrayList<>();

    private String collectionName;

    private List<List<MongoInsertItem>> mongoInsertItems = new ArrayList<>();

    private List<String> colNames = new ArrayList<>();


    public MongoInsertCommandBuilder() {

    }

    public MongoInsertCommandBuilder(Map<String, Object> paramMap) {
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.paramMap = paramMap;
            enableVariable = true;
        }
    }




    public MongoInsertCommandBuilder newDocument() {
//        this.appendDocument(new Document());

        return this;
    }


    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }


    public void addColName(String colName) {
        this.colNames.add(colName);
    }

    public void addInsertItems(List<MongoInsertItem> mongoInsertItems) {
        this.mongoInsertItems.add(mongoInsertItems);
    }


//    private void appendDocument(Document document) {
//        this.documents.add(document);
//        this.lastDocument = document;
//    }
//
//    public MongoInsertCommandBuilder appendDocumentKeyValue(String k, Object v) {
//        if (this.lastDocument == null) {
//            this.appendDocument(new Document());
//        }
//
//        this.lastDocument.put(k, v);
//
//        return this;
//    }
//




    @Override
    public MongoInsertCommand build() {
        buildInsertDoc();

        MongoInsertCommand mongoInsertCommand = new MongoInsertCommand();
        mongoInsertCommand.setDocuments(this.documents);
        mongoInsertCommand.setCollectionName(collectionName);
        log.info("insert sql : {}", mongoInsertCommand.getSqlExpr());
        return mongoInsertCommand;
    }


    private void buildInsertDoc() {
        for (List<MongoInsertItem> items : this.mongoInsertItems) {
            this.documents.add(buildSingleInsertDoc(items));
        }
    }


    private Document buildSingleInsertDoc(List<MongoInsertItem> insertItems) {
        Document document = new Document();
        for (MongoInsertItem insertItem : insertItems) {
            MongoExprAstVisitor mongoExprAstVisitor;
            if (enableVariable) {
                mongoExprAstVisitor = new MongoExprAstVisitor(insertItem.getValueExpr(), paramMap);
            } else {
                mongoExprAstVisitor = new MongoExprAstVisitor(insertItem.getValueExpr());
            }
            Object value = mongoExprAstVisitor.visit();
            document.put(insertItem.getColName(), value);
        }
        return document;
    }

}
