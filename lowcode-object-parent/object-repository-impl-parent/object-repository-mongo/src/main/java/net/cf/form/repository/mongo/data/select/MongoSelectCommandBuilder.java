package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.mongo.data.*;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.statement.*;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.util.StringUtils;

import java.util.*;

public class MongoSelectCommandBuilder extends AbstractMongoCommandBuilder<SqlSelectStatement, MongoSelectCommand> {

    private static final Logger log = LoggerFactory.getLogger(MongoSelectCommandBuilder.class);

    private boolean enableVariable = false;

    private Map<String, Object> paramMap = null;

    private String collectionName;

    private List<MongoSelectItem> selectItems = new ArrayList<>();

    private SqlJoinTableSource joinTableSource;

    private SqlExpr whereExpr;

    private SqlSelectGroupByClause groupBy;

    private SqlOrderBy orderBy;

    private SqlLimit limit;

    private static final List<String> AGGR_METHODS = Arrays.asList("COUNT", "SUM", "MAX", "MIN", "AVG");

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

    public void addJoin(SqlJoinTableSource joinTableSource) {
        this.joinTableSource = joinTableSource;
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


    private boolean shouldGroupBy = false;

    private List<MongoSelectItem> groupByFields = new ArrayList<>();

    private Map<String, Object> aliasMap = new HashMap<>();


    private void buildAggregationOperation() {

        // 初始化分析【后续看是否可以移到visitor中】
        analyse();

        // 构建过滤条件
        buildWhere();


        //
        buildAddField();

        // 构建聚合信息
        buildGroupBy();


        //
        buildOrderAndLimit();

        // 构建返回映射
        buildReturn();

    }


    private void analyse() {
        for (MongoSelectItem selectItem : this.selectItems) {
            // 设置别名
            if (selectItem.getExprEnum().shouldGetOriginExpression()) {
                if (selectItem.getAlias() == null) {
                    String originExpr = MongoUtils.getOriginExprAlias(selectItem.getSqlExpr());
                    selectItem.setAlias(originExpr);

                }
            }
            //
            if (selectItem.getExprEnum() == ExprTypeEnum.AGGR) {
                selectItem.setAggr(true);
            }

            // 缓存别名
            if (selectItem.getAlias() != null) {
                aliasMap.put(selectItem.getAlias(), selectItem);
            }

        }
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
        groupDocInfo.put(selectItem.getAlias(), opDoc);

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
            if (selectItem.getAlias() != null && sqlExpr instanceof SqlValuableExpr) {
                MongoExprAstVisitor visitor = new MongoExprAstVisitor();
                // 添加常量数据,因为在语句中，所以必须使用mongo格式
                addFields.put(selectItem.getAlias(), visitor.visit(sqlExpr));
            }
        }
        if (addFields.size() > 0) {
            AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$addFields", addFields));
            this.aggregationOperations.add(aggregationOperation);
        }
    }


    private void buildOrderAndLimit() {
        if (this.orderBy != null) {
            buildOrderBy();
        }
        if (this.limit != null) {
            buildLimit();
        }
    }

    private void buildOrderBy() {
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
        MongoExprAstVisitor mongoExprAstVisitor = new MongoExprAstVisitor(paramMap);
        Object value = mongoExprAstVisitor.visit(sqlExpr);
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


    private void addProjectField(MongoSelectItem mongoSelectItem, Document fieldProject) {
        SqlExpr sqlExpr = mongoSelectItem.getSqlExpr();
        ExprTypeEnum exprEnum = ExprTypeEnum.match(sqlExpr);
        MongoExprAstVisitor mongoExprAstVisitor = new MongoExprAstVisitor(paramMap);

        if (exprEnum == ExprTypeEnum.PARAM) {
            doAddFieldProject(fieldProject, "", null, String.valueOf(mongoExprAstVisitor.visit(mongoSelectItem.getSqlExpr())));
        } else if (exprEnum == ExprTypeEnum.COMMON) {
            doAddFieldProject(fieldProject, "", mongoSelectItem.getAlias(), mongoSelectItem.getAlias());
        } else if (exprEnum.isMethod()) {
            doAddFieldProject(fieldProject, "", mongoSelectItem.getAlias(), mongoSelectItem.getAlias());
        } else {
            throw new RuntimeException("not support project field");
        }
    }


    private void doAddFieldProject(Document fieldProject, String prefix, String alias, String value) {
        if (!StringUtils.isEmpty(alias) && !alias.equals(value)) {
            fieldProject.put(prefix + alias, "$" + prefix + value);
        } else {
            fieldProject.put(prefix + value, 1);
        }
    }


}
