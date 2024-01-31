package net.cf.form.engine;

import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.sqlbuilder.insert.InsertOqlAstVisitor;
import net.cf.form.engine.sqlbuilder.insert.InsertSqlStatementBuilder;
import net.cf.form.engine.repository.FormEngineRepository;
import net.cf.form.engine.repository.sql.ast.statement.SqlInsertStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * OQL记录引擎实现
 *
 * @author clouds
 */
@Service
public class OqlRecordEngineImpl implements OqlRecordEngine {

    private static Logger logger = LoggerFactory.getLogger(OqlRecordEngineImpl.class);

    @Resource
    private XObjectResolver objectResolver;

    @Resource
    private FormEngineRepositoryResolver driverResolver;

    @Override
    public int create(OqlInsertStatement statement) {
        InsertSqlStatementBuilder builder = new InsertSqlStatementBuilder();
        InsertOqlAstVisitor visitor = new InsertOqlAstVisitor(builder);
        statement.accept(visitor);
        SqlInsertStatement sqlStatement = builder.build();

        FormEngineRepository repository = this.resolveRepository(statement);
        int effectedRows = repository.insert(sqlStatement);
        return effectedRows;
    }

    private FormEngineRepository resolveRepository(OqlInsertStatement statement) {
        String objectName = ((OqlIdentifierExpr) statement.getInsertInto().getObjectSource().getFlashback()).getName();
        XObject object = this.objectResolver.resolveObject(objectName);
        FormEngineRepository repository = this.driverResolver.resolveRepository(object);
        return repository;
    }

    @Override
    public String create(OqlInsertStatement statement, Map<String, Object> dataMap) {
        return null;
    }

    @Override
    public List<String> batchCreate(OqlInsertStatement statement, List<Map<String, Object>> dataMaps) {
        return null;
    }

    @Override
    public void modify(OqlUpdateStatement statement, Map<String, Object> dataMap) {

    }

    @Override
    public void batchModify(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps) {

    }

    @Override
    public void remove(OqlDeleteStatement statement, Map<String, Object> dataMap) {

    }

    @Override
    public void batchRemove(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps) {

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
    public PageResult queryPageList(OqlSelectStatement statement, Map<String, Object> dataMap, Page page) {
        return null;
    }

    //    @Override
//    public String create(OqlInsertStatement statement, Map<String, Object> dataMap) {
//        OqlObjectSource objectSource = statement.getInsertInto().getObjectSource();
//        assert (objectSource instanceof OqlExprObjectSource);
//
//        XObject object = this.resolveObject(objectSource);
//        assert (object != null);
//        //1. statement语法格式校验
//        validateInsertStatement(statement, object);
//        //2. 数据处理
//        this.processData(object, dataMap);
//        //3. 数据校验
//        object.validate(dataMap);
////        //4. 动态值计算
////        if(this.recordResolver != null) {
////            this.recordResolver.calculate(object, dataMap);
////        }
//        //5. 调整Statement，补充增加的字段
//        alterInsertStatement(statement, object, dataMap);
//
//        SqlInsertStatement insertSql = new InsertSqlStatementBuilder(statement).build();
//        RepositoryDriver driver = this.driverResolver.resolveRepositoryDriver(object);
//        int effectRows = driver.insert(insertSql, dataMap);
//        String primaryFieldName = object.getPrimaryFieldName();
//        Object recordId = dataMap.get(primaryFieldName);
//
//        return recordId.toString();
//    }
//
//    @Override
//    public List<String> batchCreate(OqlInsertStatement statement, List<Map<String, Object>> dataMaps) {
//        List<String> ids = new ArrayList<>();
//        dataMaps.stream().forEach(dataMap -> {
//            ids.add(create(statement, dataMap));
//        } );
//        return ids;
//    }
//
//    @Override
//    public void modify(OqlUpdateStatement statement, Map<String, Object> dataMap) {
//        OqlObjectSource objectSource = statement.getObjectSource();
//        XObject object = this.resolveObject(objectSource);
//        //1.校验statement
//        validateUpdateStatement(statement, object);
//        //2. 数据处理
//        this.processData(object, dataMap);
//        //2. 数据校验
//        object.validate(dataMap);
////        //3. 动态值计算
////        if(this.recordResolver != null) {
////            this.recordResolver.calculate(object, dataMap);
////        }
//        //4. 调整updateStatement
//        alterUpdateStatement(statement, object, dataMap);
//
//        SqlUpdateStatement updateSql = new UpdateSqlStatementBuilder(statement).build();
//        RepositoryDriver driver = this.driverResolver.resolveRepositoryDriver(object);
//        driver.update(updateSql, dataMap);
//    }
//
//    @Override
//    public void batchModify(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps) {
//
//    }
//
//    /**
//     * 删除单条记录
//     * @param statement
//     * @param dataMap
//     */
//
//
//    @Override
//    public void remove(OqlDeleteStatement statement, Map<String, Object> dataMap) {
//        XObject object = this.resolveObject(statement.getObjectSource());
//        //1.statement校验
//        validateDeleteStatement(statement,object);
//
//        SqlDeleteStatement deleteSql = new DeleteSqlStatementBuilder(statement).build();
//        RepositoryDriver driver = this.driverResolver.resolveRepositoryDriver(object);
//        driver.delete(deleteSql, dataMap);
//    }
//
//    @Override
//    public void batchRemove(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps) {
//
//    }
//
//    @Override
//    public Map<String, Object> queryOne(OqlSelectStatement statement, Map<String, Object> dataMap) {
//        OqlObjectSource objectSource = statement.getSelect().getFrom();
//        XObject object = this.resolveObject(objectSource);
//        //1、查询语句校验
//        validateSelectStatement(statement, object);
//        //2、执行查询sql
//        SqlSelectStatement selectSql = new SelectSqlStatementBuilder(statement).build();
//        RepositoryDriver driver = this.driverResolver.resolveRepositoryDriver(object);
//        List<Map<String, Object>> result = driver.selectList(selectSql, dataMap);
//        return Objects.isNull(result) ? null : result.get(0);
//    }
//
//
//    @Override
//    public List<Map<String, Object>> queryList(OqlSelectStatement statement, Map<String, Object> dataMap) {
//        OqlObjectSource objectSource = statement.getSelect().getFrom();
//        XObject object = this.resolveObject(objectSource);
//        //1、查询语句校验
//        validateSelectStatement(statement, object);
//        //2、执行查询sql
//        SqlSelectStatement selectSql = new SelectSqlStatementBuilder(statement).build();
//        RepositoryDriver driver = this.driverResolver.resolveRepositoryDriver(object);
//        return driver.selectList(selectSql, dataMap);
//    }
//
//
//    /**
//     * 从OQL中解析对象
//     *
//     * @param objectSource
//     * @return
//     */
//    private XObject resolveObject(OqlObjectSource objectSource) {
//        OqlExprObjectSource exprObjectSource = (OqlExprObjectSource) objectSource;
//        String objectName = exprObjectSource.getExpr().getName();
//        //XObject object = this.objectResolver.resolveObject(objectName);
//        //return object;
//        return null;
//    }
//
//
//
//    /**
//     * 对数据进行处理
//     *
//     * @param object
//     * @param dataMap
//     */
//    private void processData(XObject object, Map<String, Object> dataMap) {
//        //this.processor.process(object, dataMap);
//        for (XField field : object.getFields()) {
//            if (field instanceof XDetailField) {
//                Object detailObjectValues = dataMap.get(field.getName());
//                if (detailObjectValues != null) {
//                    if (!(detailObjectValues instanceof Collection)) {
//                        throw new IllegalArgumentException("子表的数据必须是一个集合");
//                    }
//
//                    //XObject detailObject = this.objectResolver.resolveObject(((XDetailField) field).getDetailObjectName());
//                    Collection<Map<String, Object>> values = (Collection) detailObjectValues;
//                    for (Map<String, Object> value : values) {
//                        //this.processor.process(detailObject, value);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 校验Insert statement语句中有没有非持久化字段，有则报错
//     *
//     * @param statement
//     * @param object
//     */
//    private void validateInsertStatement(OqlInsertStatement statement, XObject object) {
//        //校验对象是否是持久化
//        validateObject(object);
//        //校验insert的字段中是否包含非持久化字段
//        OqlInsertInto oqlInsertInto = statement.getInsertInto();
//        //Set<String> persistenceFieldName = object.getFields().stream().filter(field -> field.isPersistence() || field instanceof XDetailField).map(XFieldImpl::getName).collect(Collectors.toSet());
//        List<OqlExpr> fields = oqlInsertInto.getFields();
//        fields.stream().forEach(field -> {
//            /*String fieldName = getOqlFieldName(field);
//            if (!persistenceFieldName.contains(fieldName)) {
//                logger.error("该字段为非持久化字段, code:{}", fieldName);
//                throw new RuntimeException("对象OQL中存在非持久化类型字段，字段名为：" + fieldName);
//            }*/
//        });
//        //TODO 此处没有对子表下的字段做校验
//    }
//
//    /**
//     * 校验statement语句中有没有非持久化字段，有则报错
//     *
//     * @param statement
//     * @param object
//     */
//    private void validateUpdateStatement(OqlUpdateStatement statement, XObject object) {
//        validateObject(object);
//        //Set<String> persistenceFieldName = object.getFields().stream().filter(field -> field.isPersistence() || field instanceof XDetailField).map(XFieldImpl::getName).collect(Collectors.toSet());
//        //校验insert的字段中是否包含非持久化字段
//        List<OqlUpdateSetItem> updateSetItems = statement.getSetItems();
//        updateSetItems.stream().forEach(field -> {
//            /*String fieldName = getOqlFieldName(field.getField());
//            if (!persistenceFieldName.contains(fieldName)) {
//                logger.error("该字段为非持久化字段, code:{}", fieldName);
//                throw new RuntimeException("对象OQL中存在非持久化类型字段，字段名为：" + fieldName);
//            }*/
//        });
//        //TODO 此处没有对子表下的字段做校验
//    }
//
//    /**
//     * 校验删除语法
//     *
//     * @param statement
//     * @param object
//     */
//    private void validateDeleteStatement(OqlDeleteStatement statement, XObject object) {
//        validateObject(object);
//        //TODO
//    }
//
//    /**
//     * 校验OqlSelectStatement语法
//     *
//     * @param statement
//     * @param object
//     */
//    private void validateSelectStatement(OqlSelectStatement statement, XObject object) {
//        validateObject(object);
//        //TODO
//    }
//
//    /**
//     * 对象校验
//     *
//     * @param object
//     */
//    private void validateObject(XObject object) {
//        if (!object.isPersistence()) {
//            logger.error("对象为非持久化对象，code:{}", object.getName());
//            throw new RuntimeException("对象OQL必须是内部对象");
//        }
//    }
//
//    /**
//     * 获取OqlExpr中的名称
//     *
//     * @param field
//     * @return
//     */
//    private String getOqlFieldName(SqlExpr field) {
//        if (field instanceof OqlIdentifierExpr) {
//            return ((OqlIdentifierExpr) field).getName();
//        } else if (field instanceof OqlObjectExpandExpr) {
//            return ((OqlObjectExpandExpr) field).getMethodName();
//        }
//        throw new RuntimeException("暂不支持该类型获取字段名称!");
//    }
//
//
//    /**
//     * 调整 Insert 语句
//     *
//     * @param statement
//     * @param dataMap
//     */
//    private void alterInsertStatement(OqlInsertStatement statement, XObject object, Map<String, Object> dataMap) {
//        logger.info("开始调整insertStatement， 原始的statement：{}", statement.toString());
//        OqlInsertInto oqlInsertInto = statement.getInsertInto();
//        List<OqlExpr> fields = oqlInsertInto.getFields();
//        OqlInsertValues values = oqlInsertInto.getValuesList().get(0);
//        //Set<String> fieldNameSet = fields.stream().map(field -> getOqlFieldName(field)).collect(Collectors.toSet());
//        Map<String, XField> fieldMap = ObjectRecordUtil.getObjectFieldMap(object);
//        dataMap.forEach((code, value) -> {
//            /*if (!fieldNameSet.contains(code) && fieldMap.containsKey(code)) {
//                SqlExpr field = ObjectRecordUtil.buildOQLField(code, value, fieldMap);
//                oqlInsertInto.addField(field);
//                values.addValue(ObjectRecordUtil.getOQLVariantRefExpr(code));
//            }*/
//        });
//        logger.info("调整完成insertstatement, 调整后的statement:{}", statement.toString());
//    }
//
//
//    /**
//     * 调整更新语句
//     *
//     * @param statement
//     * @param dataMap
//     */
//    public void alterUpdateStatement(OqlUpdateStatement statement, XObject object, Map<String, Object> dataMap) {
//        logger.info("开始调整updateStatement， 原始的statement：{}", statement.toString());
//        List<OqlUpdateSetItem> oqlUpdateSetItems = statement.getSetItems();
//        //Set<String> fieldNameSet = oqlUpdateSetItems.stream().map(item -> getOqlFieldName(item.getField())).collect(Collectors.toSet());
//        Map<String, XField> fieldMap = ObjectRecordUtil.getObjectFieldMap(object);
//        dataMap.forEach((code, value) -> {
//            /*if (!fieldNameSet.contains(code) && fieldMap.containsKey(code)) {
//                SqlExpr field = ObjectRecordUtil.buildOQLField(code, value, fieldMap);
//                OqlUpdateSetItem item = new OqlUpdateSetItem();
//                item.setField(field);
//                item.setValue(ObjectRecordUtil.getOQLVariantRefExpr(code));
//                oqlUpdateSetItems.add(item);
//            }*/
//        });
//        logger.info("调整完成insertstatement, 调整后的statement:{}", statement);
//    }
//
//    @Override
//    public PageResult queryPageList(OqlSelectStatement statement, Map<String, Object> dataMap, Page page) {
//        return null;
//    }
}
