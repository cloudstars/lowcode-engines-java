package net.cf.form.engine.repository.mongo;

import net.cf.form.engine.repository.FormEngineRepository;
import net.cf.form.engine.repository.mongo.statement.insert.InsertDocumentsBuilder;
import net.cf.form.engine.repository.mongo.statement.insert.InsertSqlAstVisitor;
import net.cf.form.engine.repository.sql.ast.statement.*;
import net.cf.form.engine.repository.sql.ast.statement.*;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class MongoFormEngineRepositoryImpl implements FormEngineRepository {

    @Resource
    private MongoTemplate template;

    @Override
    public int insert(SqlInsertStatement statement) {
        InsertDocumentsBuilder builder = new InsertDocumentsBuilder();
        InsertSqlAstVisitor visitor = new InsertSqlAstVisitor(builder);
        statement.accept(visitor);
        List<Document> documents = builder.build();
        String collectionName = this.parseCollectionName(statement.getTableSource());
        this.template.insert(documents, collectionName);
        return documents.size();
    }

    private String parseCollectionName(SqlExprTableSource tableSource) {
        return tableSource.getTableName();
    }

    @Override
    public int insert(SqlInsertStatement statement, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] batchInsert(SqlInsertStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public int update(SqlUpdateStatement statement) {
        return 0;
    }

    @Override
    public int update(SqlUpdateStatement statement, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public int delete(SqlDeleteStatement statement) {
        return 0;
    }

    @Override
    public int delete(SqlDeleteStatement statement, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement) {
        return null;
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement, Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement, Map<String, Object> paramMap) {
        return null;
    }
}
