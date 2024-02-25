package net.cf.form.engine;


import net.cf.form.engine.object.XField;
import net.cf.form.engine.object.XFieldProperty;
import net.cf.form.engine.object.XObject;
import net.cf.form.engine.sqlbuilder.insert.InsertSqlStatementBuilder;
import net.cf.form.engine.util.ObjectUtils;
import net.cf.form.repository.FormRepository;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.parser.SqlParseException;
import net.cf.form.repository.sql.util.SqlExprUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class RecordEngineImpl implements RecordEngine {

    private final Logger logger = LoggerFactory.getLogger(RecordEngineImpl.class);

    @Resource
    private FormRepositoryProvider repositoryProvider;

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

    @Override
    public int count(XObject object, Map<String, Object> queryMap) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPageList(XObject object, int pageIndex, int pageSize, Map<String, Object> queryMap) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createOne(XObject object, Object data) {
        assert (object != null && data != null);

        ObjectUtils.Ref<Method> primaryFieldGetMethodRef = ObjectUtils.createRef(null);
        SqlInsertStatement insertSql = this.buildInsertSql(object, data, primaryFieldGetMethodRef);
        FormRepository repository = this.repositoryProvider.getByObject(object);
        repository.insert(insertSql);

        try {
            Object recordId = primaryFieldGetMethodRef.getRef().invoke(data);
            logger.info("记录创建成功，模型编号：{}，记录ID：{}", object.getCode(), recordId);

            return recordId != null ? recordId.toString() : null;
        } catch (Exception e) {
            throw new RuntimeException("记录创建出错，返回主键失败！", e);
        }
    }

    /**
     * 根据模型来构建 SQL 语句
     *
     * @param object
     * @param data
     * @param primaryFieldGetMethodRef
     * @return
     */
    private SqlInsertStatement buildInsertSql(XObject object, Object data, ObjectUtils.Ref<Method> primaryFieldGetMethodRef) {
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

            if (fieldName.equals(object.getPrimaryField().getCode())) {
                primaryFieldGetMethodRef.setRef(method);
            }
        }

        return builder.build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createOne(XObject object, Map<String, Object> dataMap) {
        assert (object != null && dataMap != null);

        SqlInsertStatement insertSql = this.buildInsertSql(object, dataMap);
        FormRepository repository = this.repositoryProvider.getByObject(object);
        repository.insert(insertSql);

        Object recordId = dataMap.get(object.getPrimaryField().getCode());
        logger.info("记录创建成功，模型编号：{}，记录ID：{}", object.getCode(), recordId);

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
            String fieldCode = entry.getKey();
            XField field = object.getField(fieldCode);
            if (field == null) {
                logger.warn("字段{}不存在，忽略！", fieldCode);
                continue;
            }

            Object fieldValue = entry.getValue();
            List<XFieldProperty> properties = field.getProperties();
            if (properties != null && properties.size() > 0) {
                for (XFieldProperty property : properties) {
                    builder.appendColumn(new SqlIdentifierExpr(property.getColumnName()));
                    Object propertyValue = ObjectUtils.getPropertyValue(fieldValue, property.getCode());
                    builder.appendInsertValuesItem(this.toSqlExpr(propertyValue));
                }
            } else {
                builder.appendColumn(new SqlIdentifierExpr(field.getColumnName()));
                builder.appendInsertValuesItem(this.toSqlExpr(fieldValue));
            }
        }

        return builder.build();
    }

    private SqlExpr toSqlExpr(Object value) {
        SqlExpr valueExpr;
        try {
            valueExpr = SqlExprUtils.fromJavaObject(value);
        } catch (SqlParseException e) {
            valueExpr = new SqlCharExpr('\'' + value.toString() + '\'');
        }

        return valueExpr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<String> createList(XObject object, List<Map<String, Object>> dataMaps) {
        assert (object != null && dataMaps != null && dataMaps.size() > 0);

        SqlInsertStatement insertSql = this.buildBatchInsertSql(object, dataMaps);
        FormRepository repository = this.repositoryProvider.getByObject(object);
        repository.batchInsert(insertSql, dataMaps);

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyOne(XObject object, String recordId, Map<String, Object> dataMap) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyList(XObject object, List<String> recordIds, List<Map<String, Object>> dataMaps) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeOne(XObject object, String recordId) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeOne(XObject object, List<String> recordIds) {

    }

}
