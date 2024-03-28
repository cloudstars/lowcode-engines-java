package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.mongo.data.*;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MongoInsertCommandBuilder extends AbstractMongoCommandBuilder<SqlInsertStatement, MongoInsertCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoInsertCommandBuilder.class);

    private final List<List<Document>> documentRes = new ArrayList<>();

    private boolean enableVariable = false;
    private List<Map<String, Object>> paramMapList = null;

    private String collectionName;

    private List<List<MongoInsertItem>> mongoInsertItems = new ArrayList<>();

    private List<String> colNames = new ArrayList<>();


    public MongoInsertCommandBuilder() {

    }

    public MongoInsertCommandBuilder(Map<String, Object> paramMap) {
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.paramMapList = Arrays.asList(paramMap);
            enableVariable = true;
        }
    }

    public MongoInsertCommandBuilder(List<Map<String, Object>> paramMapList) {
        if (!CollectionUtils.isEmpty(paramMapList)) {
            this.paramMapList = paramMapList;
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
        mongoInsertCommand.setDocuments(this.documentRes);
        mongoInsertCommand.setCollectionName(collectionName);
        log.info("insert sql : {}", mongoInsertCommand.getSqlExpr());
        return mongoInsertCommand;
    }


    private void buildInsertDoc() {
        if (enableVariable) {
            for (Map<String, Object> paramMap : paramMapList) {
                List<Document> documents = new ArrayList<>();
                GlobalContext globalContext = new GlobalContext(paramMap, PositionEnum.VALUE);
                globalContext.setMongoMode(MongoMode.INSERT);
                for (List<MongoInsertItem> items : this.mongoInsertItems) {
                    documents.add(buildSingleInsertDoc(items, globalContext));
                }
                this.documentRes.add(documents);
            }
        } else {
            List<Document> documents = new ArrayList<>();
            GlobalContext globalContext = new GlobalContext(PositionEnum.VALUE);
            globalContext.setMongoMode(MongoMode.INSERT);
            for (List<MongoInsertItem> insertItems : this.mongoInsertItems) {
                documents.add(buildSingleInsertDoc(insertItems, globalContext));
            }
            this.documentRes.add(documents);
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
