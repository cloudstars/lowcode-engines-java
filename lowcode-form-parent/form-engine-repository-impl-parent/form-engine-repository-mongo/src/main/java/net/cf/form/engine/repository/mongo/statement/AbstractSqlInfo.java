package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.data.DataObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象的SQL信息
 *
 * @author clouds
 */
public abstract class AbstractSqlInfo<T> {

    /**
     * 数据对象
     */
    protected DataObject dataObject;

    /**
     * 子对象映射表
     */
    protected final Map<String, DataObject> detailObjects = new HashMap<>();

    /**
     * 子对象的SQL信息
     */
    protected final List<T> detailSqlInfos = new ArrayList<>();

    /**
     * 相关对象映射表
     */
    protected final Map<String, DataObject> lookupObjects = new HashMap<>();

    /**
     * 主对象
     */
    protected DataObject masterObject;

    /**
     * 主对象的SQL信息
     */
    protected T masterSqlInfo;

    public AbstractSqlInfo(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * 获取数据对象
     *
     * @return
     */
    public DataObject getDataObject() {
        return dataObject;
    }

    /**
     * 解析子对象的名称
     *
     * @param detailObjectName
     * @return
     */
    public DataObject resolveDetailObject(String detailObjectName) {
        return this.detailObjects.get(detailObjectName);
    }

    /**
     * 添加一个子对象
     *
     * @param detailObject
     */
    public void addDetailObject(DataObject detailObject) {
        this.detailObjects.put(detailObject.getName(), detailObject);
    }

    /**
     * 获取子对象的语句列表
     *
     * @return
     */
    public List<T> getDetailSqlInfos() {
        return detailSqlInfos;
    }

    /**
     * 新增子对象的语句
     *
     * @param detailSqlInfo
     */
    public void addDetailSqlInfo(T detailSqlInfo) {
        this.detailSqlInfos.add(detailSqlInfo);
    }

    /**
     * 解析关联对象的名称
     *
     * @param lookupObjectName
     * @return
     */
    public DataObject resolveLookupObject(String lookupObjectName) {
        return this.lookupObjects.get(lookupObjectName);
    }

    /**
     * 添加一个相关对象
     *
     * @param lookupObject
     */
    public void addLookupObject(DataObject lookupObject) {
        this.lookupObjects.put(lookupObject.getName(), lookupObject);
    }

    /**
     * 获取主对象
     *
     * @return
     */
    public DataObject getMasterObject() {
        return masterObject;
    }

    /**
     * 设置主对象
     *
     * @param masterObject
     */
    public void setMasterObject(DataObject masterObject) {
        this.masterObject = masterObject;
    }

    /**
     * 获取主对象的SQL信息
     *
     * @return
     */
    public T getMasterSqlInfo() {
        return masterSqlInfo;
    }

    /**
     * 设置主对象的SQL信息
     *
     * @param masterSqlInfo
     */
    public void setMasterSqlInfo(T masterSqlInfo) {
        this.masterSqlInfo = masterSqlInfo;
    }

}
