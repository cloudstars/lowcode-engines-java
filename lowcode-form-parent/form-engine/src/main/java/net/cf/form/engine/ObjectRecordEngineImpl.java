package net.cf.form.engine;

import net.cf.commons.lang.BusinessException;
import net.cf.commons.util.ObjectReference;
import net.cf.commons.util.ObjectUtils;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.sqlbuilder.insert.InsertSqlStatementBuilder;
import net.cf.form.repository.FormRepository;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.util.SqlExprUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class ObjectRecordEngineImpl implements ObjectRecordEngine {

    private final Logger logger = LoggerFactory.getLogger(ObjectRecordEngineImpl.class);

    @Resource
    private FormRepository repository;

    @Override
    public String createOne(XObject object, Object data) {
        assert (object != null && data != null);

        ObjectReference<Method> primaryFieldGetMethodRef = ObjectUtils.createRef(null);
        SqlInsertStatement insertSql = this.buildInsertSql(object, data, primaryFieldGetMethodRef);
        this.repository.insert(insertSql);

        try {
            Object recordId = primaryFieldGetMethodRef.getRef().invoke(data);
            logger.info("记录创建成功，对象编号：{}，记录ID：{}", object.getName(), recordId);

            return recordId != null ? recordId.toString() : null;
        } catch (Exception e) {
            throw new RuntimeException("记录创建出错，返回主键失败！", e);
        }
    }

    private void checkObject(XObject object) {
        if (object.getPrimaryField() == null) {
            throw new BusinessException("对象不合法，不存在主键字段！");
        }
    }

    /**
     * 根据对象来构建 SQL 语句
     *
     * @param object
     * @param data
     * @param primaryFieldGetMethodRef
     * @return
     */
    private SqlInsertStatement buildInsertSql(XObject object, Object data, ObjectReference primaryFieldGetMethodRef) {
        InsertSqlStatementBuilder builder = new InsertSqlStatementBuilder();
        builder.tableSource(new SqlExprTableSource(object.getTableName()));
        Map<String, Method> getMethods = ObjectUtils.getDeclaredGetMethodMap(data);
        for (Map.Entry<String, Method> entry : getMethods.entrySet()) {
            String fieldName = entry.getKey();
            Method method = entry.getValue();
            Object value;
            try {
                value = method.invoke(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (value != null) {
                builder.appendColumn(new SqlIdentifierExpr(fieldName));
                builder.appendInsertValuesItem(SqlExprUtils.toSqlExpr(value.toString()));
            }

            if (fieldName.equals(object.getPrimaryField().getName())) {
                primaryFieldGetMethodRef.setRef(method);
            }
        }

        return builder.build();
    }

    @Override
    public String createOne(XObject object, Map<String, Object> dataMap) {
        assert (object != null && dataMap != null);

        SqlInsertStatement insertSql = this.buildInsertSql(object, dataMap);
        this.repository.insert(insertSql);

        Object recordId = dataMap.get(object.getPrimaryField().getName());
        logger.info("记录创建成功，对象编号：{}，记录ID：{}", object.getName(), recordId);

        return recordId.toString();
    }


    /**
     * 根据 Map 来构建 SQL 语句
     *
     * @param object
     * @param dataMap
     * @return
     */
    private SqlInsertStatement buildInsertSql(XObject object, Map<String, Object> dataMap) {
        InsertSqlStatementBuilder builder = new InsertSqlStatementBuilder();
        builder.tableSource(new SqlExprTableSource(object.getTableName()));
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                builder.appendColumn(new SqlIdentifierExpr(fieldName));
                builder.appendInsertValuesItem(SqlExprUtils.toSqlExpr(value.toString()));
            }
        }

        return builder.build();
    }

    @Override
    public List<String> createList(XObject object, List<Map<String, Object>> dataMaps) {
        assert (object != null && dataMaps != null && dataMaps.size() > 0);

        SqlInsertStatement insertSql = this.buildBatchInsertSql(object, dataMaps);
        this.repository.batchInsert(insertSql, dataMaps);


        return null;
    }

    /**
     * 根据 Map 来构建 SQL 语句
     *
     * @param object
     * @param dataMaps
     * @return
     */
    private SqlInsertStatement buildBatchInsertSql(XObject object, List<Map<String, Object>> dataMaps) {
        InsertSqlStatementBuilder builder = new InsertSqlStatementBuilder();
        builder.tableSource(new SqlExprTableSource(object.getTableName()));
        for (Map.Entry<String, Object> entry : dataMaps.get(0).entrySet()) {
            String fieldName = entry.getKey();
            builder.appendColumn(new SqlIdentifierExpr(fieldName));
            builder.appendInsertValuesItem(new SqlVariantRefExpr("#{" + fieldName + "}"));
        }

        return builder.build();
    }

    @Override
    public void modifyOne(XObject object, String recordId, Map<String, Object> dataMap) {

    }

    @Override
    public void modifyList(XObject object, List<String> recordIds, List<Map<String, Object>> dataMaps) {

    }

    @Override
    public void removeOne(XObject object, String recordId) {

    }

    @Override
    public void removeOne(XObject object, List<String> recordIds) {

    }

    @Override
    public Map<String, Object> queryOne(XObject object, String recordId) {
        return null;
    }

    @Override
    public Map<String, Object> queryOne(XObject object, String recordId, List<String> fields) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryList(XObject object, List<String> recordIds) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryList(XObject object, List<String> recordIds, List<String> fields) {
        return null;
    }
}
