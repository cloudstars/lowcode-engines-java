package net.cf.form.engine.repository.mongo.statement.select;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.mongo.aggregation.DocumentAggregationOperation;
import net.cf.form.engine.repository.mongo.statement.*;
import net.cf.form.engine.repository.mongo.statement.*;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoSelectAggregationBuilder {


    private MongoSelectInfo mongoSelectInfo;

    private List<AggregationOperation> aggregationOperations = new ArrayList<>();


    public MongoSelectAggregationBuilder(MongoSelectInfo mongoSelectInfo) {
        this.mongoSelectInfo = mongoSelectInfo;
    }


    public MongoSelectCommand buildAggregation() {
        ObjectContextHolder.setCurrentObjectContext(mongoSelectInfo.getDataObject().getName());
        build();
        Aggregation aggregation = Aggregation.newAggregation(aggregationOperations);

        return new MongoSelectCommand(aggregation, mongoSelectInfo.getDataObject().getTableName());
    }


    private void build() {
        buildLookUp();
        buildWhere();
        buildReturn();
    }


    private void buildLookUp() {
        // todo 带优化
        if (mongoSelectInfo.getDetailSqlInfos() != null) {
            DataObject mainDataObject = mongoSelectInfo.getDataObject();
            for (MongoSelectInfo selectInfo : mongoSelectInfo.getDetailSqlInfos()) {
                DataObject dataObject = selectInfo.getDataObject();


                Document pipeline = new Document();
                Document pipelineIdExpr = new Document("$eq", Arrays.asList("$$MASTER", "$" + dataObject.getMasterField().getColumnName()));
                List<Document> subPipeline = new ArrayList<>();
                pipeline.append("$match", new Document("$expr", pipelineIdExpr));
                subPipeline.add(pipeline);
                Document lookup = new Document();
                lookup.append("from", dataObject.getTableName()).append("as", dataObject.getName())
                        .append("let", new Document("MASTER", "$" + mainDataObject.getPrimaryField().getColumnName())).append("pipeline", subPipeline);
                AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$lookup", lookup));
                this.aggregationOperations.add(aggregationOperation);
            }
        }
        if (mongoSelectInfo.getMasterSqlInfo() != null) {
            DataObject mainDataObject = mongoSelectInfo.getDataObject();
            DataObject maserDataObject = mongoSelectInfo.getMasterObject();
            Document pipeline = new Document();
            Document pipelineIdExpr = new Document("$eq", Arrays.asList("$$MASTER", "$" + maserDataObject.getPrimaryField().getColumnName()));
            List<Document> subPipeline = new ArrayList<>();
            pipeline.append("$match", new Document("$expr", pipelineIdExpr));
            subPipeline.add(pipeline);
            Document lookup = new Document();
            lookup.append("from", maserDataObject.getTableName()).append("as", maserDataObject.getName())
                    .append("let", new Document("MASTER", "$" + mainDataObject.getPrimaryField().getColumnName())).append("pipeline", subPipeline);
            AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$lookup", lookup));
            this.aggregationOperations.add(aggregationOperation);


        }

    }



    private void buildWhere() {
        if (this.mongoSelectInfo.getWhereDoc() != null && this.mongoSelectInfo.getWhereDoc().size() > 0) {
            AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$match", this.mongoSelectInfo.getWhereDoc()));
            this.aggregationOperations.add(aggregationOperation);
        }
    }

    private void buildReturn() {
        Document addFields = new Document();
        Document fieldProject = new Document();
        ObjectContextPool pool = ObjectContextHolder.getPool();
        pool.getCurrentObject();
        // 当前对象
        ObjectContext objectContext = ObjectContextHolder.getObjectContext(mongoSelectInfo.getDataObject().getName());
        DataObject dataObject = objectContext.getMainDataObject();
        for (MongoSelectInfo.SelectItem selectItem : mongoSelectInfo.getParams()) {
            addField(selectItem, fieldProject, addFields, dataObject, "");
        }

        if (mongoSelectInfo.getDetailSqlInfos() != null) {
            for (MongoSelectInfo selectInfo : mongoSelectInfo.getDetailSqlInfos()) {
                for (MongoSelectInfo.SelectItem selectItem : selectInfo.getParams()) {
                    addField(selectItem, fieldProject, addFields, selectInfo.getDataObject(), selectInfo.getDataObject().getName() + ".");
                }
            }
        }
        if (addFields.size() > 0) {
            AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$addFields", addFields));
            this.aggregationOperations.add(aggregationOperation);
        }

        AggregationOperation aggregationOperation = new DocumentAggregationOperation(new Document("$project", fieldProject));
        this.aggregationOperations.add(aggregationOperation);
    }


    private void addField(MongoSelectInfo.SelectItem selectItem, Document fieldProject, Document addFields, DataObject dataObject, String prefix) {
        VisitValue param = selectItem.getParam();
        if (param instanceof TextVisitValue) {
            TextVisitValue textVisitValue = (TextVisitValue) param;

            // 字符串常量处理
            if (dataObject.getField(textVisitValue.getValue()) == null) {
                // todo 常量补充
                addFields.append(prefix + textVisitValue.getValue(), prefix + textVisitValue.getValue());
            }
            // 映射处理
            if (selectItem.getAlias() != null && selectItem.getAlias().length() > 0 && !selectItem.getAlias().equals(textVisitValue.getValue())) {
                fieldProject.put(prefix + selectItem.getAlias(), "$" + prefix + textVisitValue.getValue());
            } else {
                fieldProject.put(prefix + textVisitValue.getValue(), 1);
            }
        } else if (param instanceof CommonVisitValue) {
            CommonVisitValue commonVisitValue = (CommonVisitValue) param;
            addFields.append(prefix + selectItem.getAlias(), prefix + commonVisitValue.getValue());
            fieldProject.put(prefix + selectItem.getAlias(), "$" + prefix + selectItem.getAlias());
        }
    }

}
