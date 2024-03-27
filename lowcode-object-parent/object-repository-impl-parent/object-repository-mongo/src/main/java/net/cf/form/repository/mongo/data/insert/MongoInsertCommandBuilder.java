package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.mongo.data.*;
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

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void addColName(String colName) {
        this.colNames.add(colName);
    }

    public void addInsertItems(List<MongoInsertItem> mongoInsertItems) {
        this.mongoInsertItems.add(mongoInsertItems);
    }


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
        GlobalContext globalContext = new GlobalContext(paramMap, PositionEnum.VALUE);
        globalContext.setMongoMode(MongoMode.INSERT);
        for (List<MongoInsertItem> items : this.mongoInsertItems) {
            this.documents.add(buildSingleInsertDoc(items, globalContext));
        }
    }


    private Document buildSingleInsertDoc(List<MongoInsertItem> insertItems, GlobalContext globalContext) {
        Document document = new Document();
        for (MongoInsertItem insertItem : insertItems) {

            Object value = MongoExprVisitor.visit(insertItem.getValueExpr(), globalContext);
            document.put(insertItem.getColName(), value);
        }
        return document;
    }

}
