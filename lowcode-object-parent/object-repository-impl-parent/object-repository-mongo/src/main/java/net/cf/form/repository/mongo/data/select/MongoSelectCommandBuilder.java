package net.cf.form.repository.mongo.data.select;

import com.alibaba.fastjson.JSON;
import net.cf.form.repository.mongo.data.AbstractMongoCommandBuilder;
import net.cf.form.repository.mongo.data.DocumentAggregationOperation;
import net.cf.form.repository.mongo.data.ExprTypeEnum;
import net.cf.form.repository.mongo.data.MongoUtils;
import net.cf.form.repository.mongo.data.visitor.*;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlCaseExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlExistsExpr;
import net.cf.form.repository.sql.ast.statement.*;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class MongoSelectCommandBuilder extends AbstractMongoCommandBuilder<SqlSelectStatement, MongoSelectCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoSelectCommandBuilder.class);

    private boolean enableVariable = false;

    private SequenceNameGenerator sequenceNameGenerator;

    private Map<String, Object> paramMap = null;

    private String collectionName;

    private List<MongoSelectItem> selectItems = new ArrayList<>();

    private SqlExpr whereExpr;

    private SqlSelectGroupByClause groupBy;

    private SqlOrderBy orderBy;

    private SqlLimit limit;

    private Map<String, String> replaceFields = new HashMap<>();

    // 关联查询结果别名
    private Map<String, String> subQueryAliasMap = new HashMap<>();

    // 关联查询
    private List<SqlExistsExpr> subQueryExprs = new ArrayList<>();

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

    public void addLimit(SqlLimit sqlLimit) {
        this.limit = sqlLimit;
    }

    public void addOrderBy(SqlOrderBy sqlOrderBy) {
        this.orderBy = sqlOrderBy;
    }

    public void addGroupBy(SqlSelectGroupByClause groupBy) {
        this.groupBy = groupBy;
    }

    public MongoSelectCommandBuilder() {
    }

    public MongoSelectCommandBuilder(Map<String, Object> paramMap) {
        if (MongoUtils.isVariableEnable(paramMap)) {
            this.paramMap = paramMap;
            this.enableVariable = true;
        }
    }

    private LinkedList<AggregationOpEnum> operations;


    private boolean shouldGroupBy = false;

    private List<MongoSelectItem> groupByFields = new ArrayList<>();

    private Map<String, Object> aliasMap = new HashMap<>();

    @Override
    public MongoSelectCommand build() {
        sequenceNameGenerator = new SequenceNameGenerator();
        buildAggregationOperation();
        Aggregation aggregation = Aggregation.newAggregation(this.aggregationOperations);
        MongoSelectCommand mongoSelectCommand = new MongoSelectCommand();
        mongoSelectCommand.setAggregation(aggregation);
        mongoSelectCommand.setCollectionName(this.collectionName);
        mongoSelectCommand.setReplaceFields(this.replaceFields);
        log.info("select sql : {} ", mongoSelectCommand.getSqlExpr());
        if (!this.replaceFields.isEmpty()) {
            log.info("select field replace : {}", JSON.toJSON(this.replaceFields));
        }
        return mongoSelectCommand;
    }

    private void buildAggregationOperation() {
        this.operations = AggregationOpEnum.getDefaultOperations();

        // 初始化分析
        analyse();

        // 构建mongo操作命令
        for (AggregationOpEnum opEnum : this.operations) {
            buildOperation(opEnum);
        }
    }


    private void buildOperation(AggregationOpEnum opEnum) {
        switch (opEnum) {
            case MATCH:
                // 构建过滤条件
                buildWhere();
                break;
            case LOOKUP:
                //
                buildLookup();
                break;
            case ADD_FIELD:
                //
                buildAddField();
                break;
            case ORDER:
                buildOrderBy();
                break;
            case DISTINCT:
                buildDistinct();
                break;
            case LIMIT:
                buildLimit();
                break;
            case GROUP_BY:
                buildGroupBy();
                break;
            case PROJECT:
                // 构建返回映射
                buildReturn();
                break;
            default:
                throw new RuntimeException("error aggregate operation");
        }
    }


    private void analyse() {
        // 处理查询字段
        for (MongoSelectItem selectItem : this.selectItems) {
            if (selectItem.getExprEnum() == ExprTypeEnum.PARAM) {
                selectItem.setOriginFieldName(MongoUtils.getOriginExpr(selectItem.getSqlExpr()));
                // 字段类型只用原始字段名作为构建命令时的字段名
                selectItem.setFieldName(selectItem.getOriginFieldName());
            } else {
                if (StringUtils.isEmpty(selectItem.getAlias())) {
                    String originExpr = MongoUtils.getOriginExprAlias(selectItem.getSqlExpr());
                    selectItem.setAlias(originExpr);
                }
                if (StringUtils.isEmpty(selectItem.getOriginFieldName())) {
                    selectItem.setOriginFieldName(selectItem.getAlias());
                }
                // 其他类型一律使用别名作为构建命令时的字段名
                selectItem.setFieldName(selectItem.getAlias());
            }
            // 设置替换字段名
            setReplaceName(selectItem);
            //
            if (selectItem.getExprEnum() == ExprTypeEnum.AGGR) {
                selectItem.setAggr(true);
            }
            // 缓存别名
            if (selectItem.getAlias() != null) {
                aliasMap.put(selectItem.getAlias(), selectItem);
            }
        }

        // 处理关联表字段作为查询条件
        MongoExistsExprVisitor existsExprVisitor = new MongoExistsExprVisitor(whereExpr);
        this.subQueryExprs = existsExprVisitor.visit();
        for (SqlExistsExpr sqlExistsExpr : this.subQueryExprs) {
            SqlSelect subQuery = sqlExistsExpr.getSubQuery();
            SqlExprTableSource from = (SqlExprTableSource) subQuery.getFrom();
            this.subQueryAliasMap.put(String.valueOf(sqlExistsExpr.hashCode()), sequenceNameGenerator.getNextName(from.getTableName()));
        }
        if (!CollectionUtils.isEmpty(this.subQueryExprs)) {
            // match 后置到 LOOKUP后
            AggregationOpEnum.moveFirstAfter(this.operations, AggregationOpEnum.LOOKUP, AggregationOpEnum.MATCH);
        }
    }

    /**
     * 设置替换字段名
     *
     * @param selectItem
     */
    private void setReplaceName(MongoSelectItem selectItem) {

        if (!StringUtils.isEmpty(selectItem.getAlias())) {
            // 别名不带点，不需要替换
            if (!selectItem.getAlias().contains(".")) {
                return;
            }
        } else {
            // 字段名不带点，不需要替换
            if (!StringUtils.isEmpty(selectItem.getOriginFieldName()) && !selectItem.getOriginFieldName().contains(".")) {
                return;
            }
        }

        String replaceName = sequenceNameGenerator.getNextName("rfield");
        selectItem.setReplaceName(replaceName);
        // 写入替换名
        if (StringUtils.isEmpty(selectItem.getAlias())) {
            // 有别名，原返回键肯定是别名，替换别名作为返回键
            replaceFields.put(replaceName, selectItem.getAlias());
        } else {
            // 没有别名，原返回键是解析字段名，替换origin field name
            replaceFields.put(replaceName, selectItem.getOriginFieldName());
        }

        // 特殊处理，非字段类型，别名必定不为空，一旦包含".", 需要改解析时字段名为 替换字段名
        if (selectItem.getExprEnum() != ExprTypeEnum.PARAM) {
            selectItem.setFieldName(replaceName);
        }
    }


    private void buildWhere() {
        Document document = new Document();
        if (this.whereExpr != null) {
            WhereBuilder whereBuilder = new WhereBuilder(this.whereExpr, paramMap, this.subQueryAliasMap);
            document = whereBuilder.build();
        }
        AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$match", document));
        this.aggregationOperations.add(aggregationOperation);
    }


    private void buildGroupBy() {
        List<MongoSelectItem> groupByItems = new ArrayList<>();
        List<MongoSelectItem> aggrItems = new ArrayList<>();
        for (MongoSelectItem selectItem : this.selectItems) {
            if (selectItem.isAggr()) {
                shouldGroupBy = true;
                aggrItems.add(selectItem);
            } else {
                groupByItems.add(selectItem);
            }
        }

        if (!shouldGroupBy) {
            return;
        }

        this.groupByFields = groupByItems;

        Document groupDocInfo = new Document();
        if (this.groupBy == null) {
            groupDocInfo.put("_id", null);
        } else {
            // todo
            Document idDoc = new Document();
            for (SqlExpr sqlExpr : this.groupBy.getItems()) {
                String originExpr = MongoUtils.getOriginExprAlias(sqlExpr);
                idDoc.put(originExpr, "$" + originExpr);
            }
            groupDocInfo.put("_id", idDoc);
        }

        for (MongoSelectItem selectItem : aggrItems) {
            addGroupInfo(selectItem, groupDocInfo);
        }
        AggregationOperation groupOperation = new DocumentAggregationOperation(new Document("$group", groupDocInfo));
        this.aggregationOperations.add(groupOperation);
    }


    private void addGroupInfo(MongoSelectItem selectItem, Document groupDocInfo) {
        SqlExpr sqlExpr = selectItem.getSqlExpr();

        SqlMethodInvokeExpr sqlAggregateExpr = (SqlMethodInvokeExpr) sqlExpr;
        String methodName = sqlAggregateExpr.getMethodName();
        Object expression = getAggrExpression(sqlAggregateExpr, methodName);

        Document opDoc = new Document();
        String mongoMethod = getMongoMethod(methodName);
        opDoc.put(mongoMethod, expression);
        groupDocInfo.put(selectItem.getFieldName(), opDoc);

    }


    private Object getAggrExpression(SqlMethodInvokeExpr sqlAggregateExpr, String methodName) {
        if ("COUNT".equalsIgnoreCase(methodName)) {
            return 1;
        }
        List<SqlExpr> arguments = sqlAggregateExpr.getArguments();
        SqlExpr sqlExpr = arguments.get(0);
        if (sqlExpr instanceof SqlIdentifierExpr) {
            return "$" + ((SqlIdentifierExpr) sqlExpr).getName();
        }
        throw new RuntimeException("not support");
    }


    private String getMongoMethod(String methodName) {
        String mongoMethod = null;
        switch (methodName.toUpperCase()) {
            case "COUNT":
            case "SUM":
                mongoMethod = "$sum";
                break;
            case "MAX":
                mongoMethod = "$max";
                break;
            case "MIN":
                mongoMethod = "$min";
                break;
            case "AVG":
                mongoMethod = "$avg";
                break;
            default:
        }
        return mongoMethod;
    }


    private void buildAddField() {
        Document addFields = new Document();

        for (MongoSelectItem selectItem : this.selectItems) {
            SqlExpr sqlExpr = selectItem.getSqlExpr();
            if (sqlExpr instanceof SqlValuableExpr
                    || sqlExpr instanceof SqlCaseExpr
                    || selectItem.getExprEnum() == ExprTypeEnum.METHOD
                    || selectItem.getExprEnum() == ExprTypeEnum.EXPRESSION) {
                Object value = MongoExprVisitor.visit(sqlExpr, new GlobalContext(paramMap, PositionEnum.PARAM));
                // 添加常量数据,因为在语句中，所以必须使用mongo格式
                addFields.put(selectItem.getFieldName(), value);
            }
        }
        if (addFields.size() > 0) {
            AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$addFields", addFields));
            this.aggregationOperations.add(aggregationOperation);
        }
    }


    private void buildDistinct() {

    }


    private void buildLookup() {
        if (this.subQueryExprs.isEmpty()) {
            return;
        }

        for (SqlExistsExpr sqlExistsExpr : subQueryExprs) {
            SqlSelect select = sqlExistsExpr.getSubQuery();
            SqlExpr sqlExpr = select.getWhere();
            String slaveTable = ((SqlExprTableSource) select.getFrom()).getTableName();
            String slaveTableAs = this.subQueryAliasMap.get(String.valueOf(sqlExistsExpr.hashCode()));
            String mainTable = this.collectionName;
            List<Document> documents = buildSingleLeftJoinLookup(mainTable, slaveTable, slaveTableAs, sqlExpr);
            for (Document document : documents) {
                AggregationOperation aggregationOperation = new DocumentAggregationOperation(document);
                this.aggregationOperations.add(aggregationOperation);
            }
        }

    }

    private List<Document> buildSingleLeftJoinLookup(String mainTable, String slaveTable, String slaveTableAs, SqlExpr sqlExpr) {
        Map<String, List<String>> table2JoinParam = JoinConditionBuilder.getJoinParam((SqlBinaryOpExpr) sqlExpr);

        JoinInfo joinInfo = new JoinInfo();

        // let
        Document letDoc = new Document();
        for (String param : table2JoinParam.get(mainTable)) {
            // let处添加连接主表的连接字段信息
            String innerMainMasterKey = "jkey_" + param.replace(mainTable + ".", mainTable + "_").toLowerCase();
            String innerMainParam = param.replace(mainTable + ".", "");
            letDoc.put(innerMainMasterKey, "$" + innerMainParam);
            // 用于on变量替换
            joinInfo.addMainReplace(param, "$$" + innerMainMasterKey);
        }

        for (String param : table2JoinParam.get(slaveTable)) {
            String newParam = param.replace(slaveTable + ".", "");
            // 用于on变量替换
            joinInfo.addSlaveReplace(param, newParam);
        }
        joinInfo.addTableNames(mainTable);
        joinInfo.addTableNames(slaveTable);

        // lookup pipeline
        List<Document> refPipeline = new ArrayList<>();
        Document pipelineMatch = new Document();
        Document onExpr = JoinConditionBuilder.buildExpr((SqlBinaryOpExpr) sqlExpr, joinInfo, this.paramMap);
        pipelineMatch.append("$match", onExpr);
        refPipeline.add(pipelineMatch);

        Document refLookUp = new Document();
        refLookUp.append("from", slaveTable)
                .append("as", slaveTableAs)
                .append("let", letDoc)
                .append("pipeline", refPipeline);

        Document lookupDoc = new Document(refLookUp);
        List<Document> singleRef = new ArrayList<>();
        singleRef.add(new Document("$lookup", lookupDoc));

        Document unwindDoc = new Document();
        unwindDoc.put("path", "$" + slaveTableAs);
        unwindDoc.put("preserveNullAndEmptyArrays", true);
        singleRef.add(new Document("$unwind", unwindDoc));
        return singleRef;
    }


    private void buildOrderBy() {
        if (this.orderBy == null) {
            return;
        }
        SqlOrderBy sqlOrderBy = this.orderBy;
        List<Sort.Order> orders = new ArrayList<>();
        for (SqlSelectOrderByItem item : sqlOrderBy.getItems()) {
            Sort.Order order = null;
            Sort.Direction direction = item.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            if (item.getExpr() instanceof SqlIdentifierExpr) {
                String param = ((SqlIdentifierExpr) item.getExpr()).getName();
                order = new Sort.Order(direction, param);
            } else {
                throw new RuntimeException("not support");
            }
            orders.add(order);
        }
        AggregationOperation aggregationOperation = Aggregation.sort(Sort.by(orders));
        this.aggregationOperations.add(aggregationOperation);
    }

    private void buildLimit() {
        if (this.limit == null) {
            return;
        }

        SqlLimit sqlLimit = this.limit;
        SqlExpr offsetExpr = sqlLimit.getOffset();
        if (offsetExpr != null) {
            int offset = parseInt(offsetExpr);
            if (offset >= 0) {
                AggregationOperation skipOperation = Aggregation.skip(offset);
                this.aggregationOperations.add(skipOperation);
            }
        }

        int rowCount = parseInt(sqlLimit.getRowCount());
        AggregationOperation limitOperation = Aggregation.limit(rowCount);
        this.aggregationOperations.add(limitOperation);
    }


    private int parseInt(SqlExpr sqlExpr) {
        Object value = MongoExprVisitor.visit(sqlExpr, new GlobalContext(paramMap, PositionEnum.DEFAULT));
        return Integer.valueOf(String.valueOf(value));
    }


    private void buildReturn() {
        Document fieldProject = new Document();
        if (shouldGroupBy) {
            for (MongoSelectItem selectItem : groupByFields) {
                addProjectFieldForAggregateId(selectItem, fieldProject);
            }
            for (MongoSelectItem selectItem : selectItems) {
                if (selectItem.isAggr()) {
                    addProjectField(selectItem, fieldProject);
                }
            }
        } else {
            for (MongoSelectItem mongoSelectItem : selectItems) {
                addProjectField(mongoSelectItem, fieldProject);
            }
        }

        if (fieldProject.size() > 0) {
            AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$project", fieldProject));
            this.aggregationOperations.add(aggregationOperation);
        }

    }


    private void addProjectFieldForAggregateId(MongoSelectItem selectItem, Document fieldProject) {

        // todo
        if (selectItem.getExprEnum() == ExprTypeEnum.PARAM) {
            SqlIdentifierExpr sqlIdentifierExpr = (SqlIdentifierExpr) selectItem.getSqlExpr();
            fieldProject.put(sqlIdentifierExpr.getName(), "$_id." + sqlIdentifierExpr.getName());
        }

    }


    /**
     * @param mongoSelectItem
     * @param fieldProject
     */
    private void addProjectField(MongoSelectItem mongoSelectItem, Document fieldProject) {
        doAddFieldProject(fieldProject, "", mongoSelectItem.getAlias(), mongoSelectItem.getOriginFieldName(), mongoSelectItem.getReplaceName());
    }


    /**
     * @param fieldProject
     * @param prefix
     * @param alias
     * @param fieldName
     * @param replaceFieldName
     */
    private void doAddFieldProject(Document fieldProject, String prefix, String alias, String fieldName, String replaceFieldName) {
        // 如果有字段名替换，直接使用替换字段名
        if (!StringUtils.isEmpty(replaceFieldName)) {
            fieldProject.put(prefix + replaceFieldName, 1);
            return;
        }

        if (!StringUtils.isEmpty(alias) && !alias.equals(fieldName)) {
            fieldProject.put(prefix + alias, "$" + prefix + fieldName);
        } else {
            fieldProject.put(prefix + fieldName, 1);
        }
    }


}
