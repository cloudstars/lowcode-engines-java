package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.visitor.InsertStatementCheckOqlAstVisitor;
import net.cf.object.engine.sqlbuilder.insert.OqlInsertAstVisitor;
import net.cf.object.engine.sqlbuilder.insert.SqlInsertStatementBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * OQL记录引擎实现
 *
 * @author clouds
 */
public class OqlEngineImpl implements OqlEngine {

    private static Logger logger = LoggerFactory.getLogger(OqlEngineImpl.class);

    private final ObjectRepository repository;

    public OqlEngineImpl(ObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement statement) {
        return null;
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement statement, Map<String, Object> dataMap) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement statement, Map<String, Object> dataMap) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int create(OqlInsertStatement statement) {
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        OqlInsertAstVisitor visitor = new OqlInsertAstVisitor(builder);
        statement.accept(visitor);
        SqlInsertStatement sqlStatement = builder.build();

        int effectedRows = this.repository.insert(sqlStatement);
        return effectedRows;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String create(OqlInsertStatement statement, Map<String, Object> dataMap) {
        // 语法检查
        InsertStatementCheckOqlAstVisitor checkVisitor = new InsertStatementCheckOqlAstVisitor();
        statement.accept(checkVisitor);


        // 根据object的定义校验数据

        return null;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<String> createList(OqlInsertStatement statement, List<Map<String, Object>> dataMaps) {
        return null;
    }

    @Override
    public void modify(OqlUpdateStatement statement) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modify(OqlUpdateStatement statement, Map<String, Object> dataMap) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyList(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps) {

    }

    @Override
    public void remove(OqlDeleteStatement statement) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void remove(OqlDeleteStatement statement, Map<String, Object> dataMap) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeList(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps) {

    }

}
