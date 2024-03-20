package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.mongo.data.*;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoSelectCommandBuilder extends AbstractMongoCommandBuilder<SqlSelectStatement, MongoSelectCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoSelectCommandBuilder.class);

    private boolean enableVariable = false;

    private Map<String, Object> paramMap = null;


    private String collectionName;

    private List<MongoSelectItem> selectItems = new ArrayList<>();

    private SqlExpr whereExpr;


    private List<AggregationOperation> aggregationOperations = new ArrayList<>();


    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void addSelectItems(MongoSelectItem mongoExpr) {
        selectItems.add(mongoExpr);
    }

    public void addWhere(SqlExpr whereExpr) {
        this.whereExpr = whereExpr;
    }

    public MongoSelectCommandBuilder() {
    }

    public MongoSelectCommandBuilder(Map<String, Object> paramMap) {
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.paramMap = paramMap;
            this.enableVariable = true;
        }
    }


    @Override
    public MongoSelectCommand build() {
        buildAggregationOperation();
        Aggregation aggregation = Aggregation.newAggregation(this.aggregationOperations);
        MongoSelectCommand mongoSelectCommand = new MongoSelectCommand();
        mongoSelectCommand.setAggregation(aggregation);
        mongoSelectCommand.setCollectionName(this.collectionName);
        log.info("select sql : {} ", mongoSelectCommand.getSqlExpr());
        return mongoSelectCommand;
    }


    private void buildAggregationOperation() {

        // 初始化分析【后续看是否可以移到visitor中】
        analyse();

        // 构建过滤条件
        buildWhere();

        // 构建聚合信息
        buildGroupBy();

        // 构建返回映射
        buildReturn();

    }




    private void analyse() {

    }

    private void buildWhere() {
        Document document = new Document();
        if (this.whereExpr != null) {
            WhereBuilder whereBuilder = new WhereBuilder(this.whereExpr, paramMap);
            document = whereBuilder.build();
        }
        AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$match", document));
        this.aggregationOperations.add(aggregationOperation);
    }



    private void buildGroupBy() {

    }


    private void buildReturn() {
        Document fieldProject = new Document();



    }


    private void resolveProjectField(Document fieldProject) {
        for (MongoSelectItem mongoSelectItem : selectItems) {
            addProjectField(mongoSelectItem, fieldProject);
        }
    }

    private void addProjectField(MongoSelectItem mongoSelectItem, Document fieldProject) {
        SqlExpr sqlExpr = mongoSelectItem.getSqlExpr();
        ExprTypeEnum exprEnum = ExprTypeEnum.match(sqlExpr);
        MongoExprAstVisitor mongoExprAstVisitor = new MongoExprAstVisitor(mongoSelectItem.getSqlExpr(), paramMap);

        if (exprEnum == ExprTypeEnum.PARAM) {
            doAddFieldProject(fieldProject, null, null, String.valueOf(mongoExprAstVisitor.visitForValue()));
        } else if (exprEnum == ExprTypeEnum.COMMON) {
            doAddFieldProject(fieldProject, null, mongoSelectItem.getAlias(), mongoSelectItem.getAlias());
        }
        throw new RuntimeException("not support");
    }


    private void doAddFieldProject(Document fieldProject, String prefix, String alias, String value) {
        if (!StringUtils.isEmpty(alias) && !alias.equals(value)) {
            fieldProject.put(prefix + alias, "$" + prefix + value);
        } else {
            fieldProject.put(prefix + value, 1);
        }



    }

}
