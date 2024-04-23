package net.cf.form.repository.mongo;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.mongo.data.DataConvertContext;
import net.cf.form.repository.mongo.data.DocumentAggregationOperation;
import net.cf.form.repository.mongo.data.MongoDbDataConverter;
import net.cf.form.repository.mongo.data.MongoUtils;
import net.cf.form.repository.mongo.data.delete.MongoDeleteCommand;
import net.cf.form.repository.mongo.data.delete.MongoDeleteCommandBuilder;
import net.cf.form.repository.mongo.data.delete.MongoDeleteSqlAstVisitor;
import net.cf.form.repository.mongo.data.insert.MongoInsertCommand;
import net.cf.form.repository.mongo.data.insert.MongoInsertCommandBuilder;
import net.cf.form.repository.mongo.data.insert.MongoInsertSqlAstVisitor;
import net.cf.form.repository.mongo.data.select.MongoSelectCommand;
import net.cf.form.repository.mongo.data.select.MongoSelectCommandBuilder;
import net.cf.form.repository.mongo.data.select.MongoSelectSqlAstVisitor;
import net.cf.form.repository.mongo.data.update.MongoUpdateCommand;
import net.cf.form.repository.mongo.data.update.MongoUpdateCommandBuilder;
import net.cf.form.repository.mongo.data.update.MongoUpdateSqlAstVisitor;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.AggregationUpdate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class MongoObjectRepositoryImpl implements ObjectRepository {

    private final MongoTemplate template;

    public MongoObjectRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public int insert(SqlInsertStatement statement) {
        return insert(statement, null);
    }

    @Override
    public int insert(SqlInsertStatement statement, Map<String, Object> paramMap) {
        List<Map<String, Object>> paramMapList = null;

        if (MongoUtils.isVariableEnable(paramMap)) {
            paramMapList = Arrays.asList(paramMap);
        }

        int[] res = batchInsert(statement, paramMapList);
        if (res != null && res.length > 0) {
            return res[0];
        }
        return 0;
    }

    @Override
    public int[] batchInsert(SqlInsertStatement statement, List<Map<String, Object>> paramMapList) {
        MongoInsertCommandBuilder mongoInsertCommandBuilder = new MongoInsertCommandBuilder(paramMapList);
        MongoInsertSqlAstVisitor visitor = new MongoInsertSqlAstVisitor(mongoInsertCommandBuilder);
        statement.accept(visitor);
        MongoInsertCommand mongoInsertCommand = mongoInsertCommandBuilder.build();

        List<Document> documents = mongoInsertCommand.getMergedDocuments();
        this.template.insert(documents, mongoInsertCommand.getCollectionName());

        addAutoPrimaryKeyValue(statement, paramMapList, mongoInsertCommand.getDocuments());
        int[] nums = new int[mongoInsertCommand.getDocuments().size()];
        int index = 0;
        for (List<Document> doc : mongoInsertCommand.getDocuments()) {
            nums[index] = doc.size();
            index++;
        }
        return nums;
    }

    private void addAutoPrimaryKeyValue(SqlInsertStatement statement, List<Map<String, Object>> paramMapList, List<List<Document>> documents) {
        if (paramMapList == null || paramMapList.size() == 0) {
            return;
        }
        String autoPrimaryKey = statement.getAutoGenColumn();
        if (StringUtils.isEmpty(autoPrimaryKey)) {
            return;
        }

        int index = 0;
        for (Map<String, Object> paramMap : paramMapList) {
            // todo
            Document document = documents.get(index).get(0);
            paramMap.put(autoPrimaryKey, MongoDbDataConverter.convertDbData(document.get("_id")));
            index++;
        }
    }


    @Override
    public int update(SqlUpdateStatement statement) {
        return update(statement, null);
    }

    @Override
    public int update(SqlUpdateStatement statement, Map<String, Object> paramMap) {
        MongoUpdateCommandBuilder builder = new MongoUpdateCommandBuilder(paramMap);
        MongoUpdateSqlAstVisitor visitor = new MongoUpdateSqlAstVisitor(builder);
        statement.accept(visitor);
        MongoUpdateCommand command = builder.build();
        Query where = null;
        if (command.getWhereDoc() != null) {
            where = new BasicQuery(command.getWhereDoc());
        } else {
            where = new BasicQuery(new Document());
        }

        AggregationOperation aggregationOperation = new DocumentAggregationOperation(command.getSetDoc());
        AggregationUpdate aggregationUpdate = AggregationUpdate.from(Arrays.asList(aggregationOperation));
        UpdateResult updateResult = template.updateMulti(where, aggregationUpdate, command.getCollectionName());

        return (int) updateResult.getModifiedCount();
    }

    @Override
    public int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
        if (CollectionUtils.isEmpty(paramMapList)) {
            return new int[0];
        }
        int[] effectedCounts = new int[paramMapList.size()];
        int index = 0;
        for (Map<String, Object> paramMap : paramMapList) {
            int effectedCount = update(statement, paramMap);
            effectedCounts[index] = effectedCount;
            index++;
        }
        return effectedCounts;
    }

    @Override
    public int delete(SqlDeleteStatement statement) {
        return delete(statement, null);
    }

    @Override
    public int delete(SqlDeleteStatement statement, Map<String, Object> paramMap) {
        MongoDeleteCommandBuilder builder = new MongoDeleteCommandBuilder(paramMap);
        MongoDeleteSqlAstVisitor visitor = new MongoDeleteSqlAstVisitor(builder);
        statement.accept(visitor);
        MongoDeleteCommand command = builder.build();
        Query where = new BasicQuery(command.getWhereDoc());
        DeleteResult deleteResult = template.remove(where, command.getCollectionName());
        return (int) deleteResult.getDeletedCount();
    }

    @Override
    public int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMapList) {
        if (CollectionUtils.isEmpty(paramMapList)) {
            return new int[0];
        }
        int[] effectedCounts = new int[paramMapList.size()];
        int index = 0;
        for (Map<String, Object> paramMap : paramMapList) {
            int effectedCount = delete(statement, paramMap);
            effectedCounts[index] = effectedCount;
            index++;
        }
        return effectedCounts;
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement) {
        List<Map<String, Object>> res = selectList(statement);
        if (CollectionUtils.isEmpty(res)) {
            return new HashMap<>();
        }
        return res.get(0);
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement, Map<String, Object> paramMap) {
        List<Map<String, Object>> res = selectList(statement, paramMap);
        if (CollectionUtils.isEmpty(res)) {
            return new HashMap<>();
        }
        return res.get(0);
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement) {
        return selectList(statement, null);
    }


    private List<Map<String, Object>> convert(List<Document> documents, DataConvertContext convertContext) {
        List<Map<String, Object>> res = new ArrayList<>();
        for (Document document : documents) {
            res.add(convert(document, convertContext));
        }
        return res;
    }

    private Map<String, Object> convert(Document document, DataConvertContext convertContext) {
        return MongoDbDataConverter.convertDoc(document, convertContext);
    }


    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement, Map<String, Object> paramMap) {
        MongoSelectCommandBuilder builder = new MongoSelectCommandBuilder(paramMap);
        MongoSelectSqlAstVisitor visitor = new MongoSelectSqlAstVisitor(builder);
        statement.accept(visitor);
        MongoSelectCommand command = builder.build();

        AggregationResults<Document> results = this.template.aggregate(command.getAggregation(), command.getCollectionName(), Document.class);

        DataConvertContext convertContext = new DataConvertContext(command.getReplaceFields());
        List<Map<String, Object>> res = convert(results.getMappedResults(), convertContext);
        return res;
    }
}
