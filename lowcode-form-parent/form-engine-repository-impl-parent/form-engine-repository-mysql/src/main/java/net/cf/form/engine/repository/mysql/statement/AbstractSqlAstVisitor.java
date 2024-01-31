package net.cf.form.engine.repository.mysql.statement;

import net.cf.form.engine.repository.data.*;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.parser.ParserException;
import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.sql_bak.AbstractSqlInfo;
import net.cf.form.engine.repository.data.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象的 OQL AST 访问器
 *
 * @author clouds
 */
public abstract class AbstractSqlAstVisitor<T extends AbstractSqlInfo> implements OqlAstVisitor {

    /**
     * 数据对象解析器
     */
    protected DataObjectResolver resolver;

    /**
     * 当前访问的对象
     */
    protected DataObject object;

    /**
     * 已经解析过的对象（根据对象的名称解析）
     */
    private Map<String, DataObject> resolvedObjects = new HashMap<>();

    /**
     * 已经解析过的对象（根据本表的关联对象、主子对象字段解析）
     */
    private Map<String, DataObject> resolvedFieldObjects = new HashMap<>();


    /**
     * 子对象映射表，KEY为子对象的字段名称，值为子对象
     */
    //private Map<String, DataObject> detailObjects = new HashMap<>();

    /**
     * 本对象的主对象
     */
    //private DataObject masterObject;

    //private Map<String, DataObject> lookupObjects = new HashMap<>();

    /**
     * 是否参数化的（用于判断生成的SQL中用${}，还是#{}）
     */
    protected boolean parameterized;

    /**
     * 表达式AST访问器
     */
    protected OqlExprAstVisitor exprVisitor;

    /**
     * 当前解析后的SQL信息
     */
    protected T sqlInfo;

    public AbstractSqlAstVisitor(DataObjectResolver resolver) {
        this(resolver, false);
    }

    public AbstractSqlAstVisitor(DataObjectResolver resolver, boolean parameterized) {
        this.resolver = resolver;
        this.parameterized = parameterized;
    }

    /**
     * 获取解析到的数据对象
     *
     * @return
     */
    public DataObject getObject() {
        return object;
    }

    protected void resolveObjectSource(OqlObjectSource objectSource) {
        if (objectSource instanceof OqlExprObjectSource) {
            String objectName = ((OqlExprObjectSource) objectSource).getExpr().getName();
            this.object = this.resolveObject(objectName);
        }
    }

    /**
     * 根据对象名称解析型
     *
     * @param objectName
     * @return
     */
    protected DataObject resolveObject(String objectName) {
        DataObject resolvedObject = this.resolvedObjects.get(objectName);
        if (resolvedObject == null) {
            resolvedObject = this.resolver.resolveObject(objectName);
            assert (resolvedObject != null);
            this.resolvedObjects.put(objectName, resolvedObject);
        }

        return resolvedObject;
    }

    /**
     * 根据主对象的子对象字段解析子对象
     *
     * @param objectFieldName
     * @return
     */
    public DataObject resolveFieldObject(String objectFieldName) {
        DataObject fieldObject = resolvedFieldObjects.get(objectFieldName);
        if (fieldObject == null) {
            DataField field = this.object.getField(objectFieldName);
            assert (field != null);

            String objectName;
            if (field instanceof DetailDataField) {
                objectName = ((DetailDataField) field).getDetailObjectName();
            } else if (field instanceof MasterDataField) {
                objectName = ((MasterDataField) field).getMasterObjectName();
            } else if (field instanceof LookupDataField) {
                objectName = ((LookupDataField) field).getLookupObjectName();
            } else {
                throw new ParserException(objectFieldName + "is not an object field!");
            }

            fieldObject = this.resolveObject(objectName);
            assert (fieldObject != null);
            resolvedFieldObjects.put(objectFieldName, fieldObject);
            resolvedObjects.put(objectName, fieldObject);
        }

        return fieldObject;
    }

    /**
     * 根据主对象的子对象字段解析子对象
     *
     * @param detailFieldName
     * @return
     */
    /*public DataObject resolveDetailObject(String detailFieldName) {
        DataObject detailObject = detailObjects.get(detailFieldName);
        if (detailObject == null) {
            DataField detailField = this.resolvedObject.getField(detailFieldName);
            assert (detailField != null && detailField instanceof DetailDataField);
            String detailObjectName = ((DetailDataField) detailField).getDetailObjectName();
            detailObject = resolver.resolveObject(detailObjectName);
            assert (detailObject != null);
            detailObjects.put(detailFieldName, detailObject);
        }

        return detailObject;
    }*/

    /**
     * 根据本对象的主对象字段解析主对象
     *
     * @param masterFieldName
     * @return
     */
    /*public DataObject resolveMasterObject(String masterFieldName) {
        if (this.masterObject != null) {
            DataField masterField = this.resolvedObject.getField(masterFieldName);
            assert (masterField != null && masterField instanceof MasterDataField);
            this.masterObject = this.resolveObject(masterField.getName());
        }

        return this.masterObject;
    }*/

    /**
     * 根据本对象的相关对象字段解析相关对象
     *
     * @param lookupFieldName
     * @return
     */
    /*public DataObject resolveLookupObject(String lookupFieldName) {
        DataObject lookupObject = detailObjects.get(lookupFieldName);
        if (lookupFieldName == null) {
            DataField lookupField = this.resolvedObject.getField(lookupFieldName);
            assert (lookupField != null && lookupField instanceof LookupDataField);
            String lookupObjectName = ((LookupDataField) lookupField).getLookupObjectName();
            lookupObject = resolver.resolveObject(lookupObjectName);
            assert (lookupObject != null);
            lookupObjects.put(lookupFieldName, lookupObject);
        }

        return lookupObject;
    }*/

    /**
     * 设置解析到的数据对象
     *
     * @param resolvedObject
     */
    /*public void setResolvedObject(DataObject resolvedObject) {
        this.resolvedObject = resolvedObject;
        this.exprVisitor = new OqlExprAstVisitor(resolvedObject);
    }*/

    /**
     * 获取 SQL 信息
     *
     * @return
     */
    public T getSqlInfo() {
        return this.sqlInfo;
    }
}
