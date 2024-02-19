package net.cf.form.engine;

import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.oql.visitor.InsertStatementCheckOqlAstVisitor;
import net.cf.form.engine.sqlbuilder.insert.InsertOqlAstVisitor;
import net.cf.form.engine.sqlbuilder.insert.InsertSqlStatementBuilder;
import net.cf.form.repository.FormRepository;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * OQL记录引擎实现
 *
 * @author clouds
 */
@Service
public class ObjectQLEngineImpl implements ObjectQLEngine {

    private static Logger logger = LoggerFactory.getLogger(ObjectQLEngineImpl.class);

    //@Resource
    //private XObjectResolver objectResolver;

    @Resource
    private FormRepositoryProvider driverResolver;

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
        InsertSqlStatementBuilder builder = new InsertSqlStatementBuilder();
        InsertOqlAstVisitor visitor = new InsertOqlAstVisitor(builder);
        statement.accept(visitor);
        SqlInsertStatement sqlStatement = builder.build();

        FormRepository repository = this.resolveRepository(statement);
        int effectedRows = repository.insert(sqlStatement);
        return effectedRows;
    }

    /**
     * 根据模型决定使用哪个存储
     *
     * @param statement
     * @return
     */
    private FormRepository resolveRepository(OqlInsertStatement statement) {
        XObject object = statement.getObjectSource().getResolvedObject();
        FormRepository repository = this.driverResolver.getByObject(object);
        return repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String create(OqlInsertStatement statement, Map<String, Object> dataMap) {
        // 语法检查s
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modify(OqlUpdateStatement statement, Map<String, Object> dataMap) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyList(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void remove(OqlDeleteStatement statement, Map<String, Object> dataMap) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void batchRemove(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps) {

    }

}
