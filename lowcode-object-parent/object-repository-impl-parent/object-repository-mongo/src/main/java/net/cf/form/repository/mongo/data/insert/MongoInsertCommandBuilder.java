package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.mongo.data.AbstractMongoCommandBuilder;
import net.cf.form.repository.mongo.data.MongoUtils;
import net.cf.form.repository.mongo.data.visitor.*;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class MongoInsertCommandBuilder extends AbstractMongoCommandBuilder<SqlInsertStatement, MongoInsertCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoInsertCommandBuilder.class);

    private final List<List<Document>> documentRes = new ArrayList<>();

    private String autoGenColumn;

    private boolean enableVariable = false;
    private List<Map<String, Object>> paramMapList = null;

    private String collectionName;

    private List<List<MongoInsertItem>> mongoInsertItems = new ArrayList<>();

    private List<String> colNames = new ArrayList<>();

    public void addAutoGenColumn(String autoGenColumn) {
        this.autoGenColumn = autoGenColumn;
    }

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
        Set<String> autoGenFields = new HashSet<>();
        for (MongoInsertItem insertItem : insertItems) {
            VisitContext visitContext = new VisitContext();
            if (insertItem.getColumn().isAutoGen()) {
                visitContext.setAutoGen(true);
            }
            Object value = MongoExprVisitor.visit(insertItem.getValueExpr(), globalContext, visitContext);
            document.put(insertItem.getColName(), value);
        }

        if (!StringUtils.isEmpty(this.autoGenColumn) && !"_id".equals(this.autoGenColumn)) {
            autoGenFields.add(this.autoGenColumn);
        }

        // 统一处理自增
        if (!CollectionUtils.isEmpty(autoGenFields)) {
            for (String autoGenField : autoGenFields) {
                document.put(autoGenField, new ObjectId());
            }
        }

        return document;
    }

}
