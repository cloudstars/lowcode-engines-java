package net.cf.form.engine.repository.mongo.statement.select;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.LookupDataField;
import net.cf.form.engine.repository.mongo.statement.AbstractQueryableSqlInfo;
import net.cf.form.engine.repository.mongo.statement.VisitValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoSelectInfo extends AbstractQueryableSqlInfo<MongoSelectInfo> {

    private List<SelectItem> params = new ArrayList<>();



    /**
     * 单选的相关对象的SQL信息(可以被LEFT-JOIN)，KEY表示关联的字段
     */
    protected final Map<LookupDataField, MongoSelectInfo> lookupSqlInfos = new HashMap<>();

    /**
     * 多选的相关对象的SQL信息(不能被LEFT-JOIN)
     */
    protected final Map<LookupDataField, MongoSelectInfo> individualLookupSqlInfos = new HashMap<>();





    public MongoSelectInfo(DataObject object) {
        super(object);
    }



    public List<SelectItem> getParams() {
        return params;
    }

    public void setParams(List<SelectItem> params) {
        this.params = params;
    }




    /**
     * 获取关联对象的查询语句列表
     *
     * @return
     */
    public Map<LookupDataField, MongoSelectInfo> getLookupSqlInfos() {
        return lookupSqlInfos;
    }

    /**
     * 新增关联对象的查询语句
     *
     * @param lookupSqlInfo
     */
    public void addLookupSqlInfo(LookupDataField lookupField, MongoSelectInfo lookupSqlInfo) {
        this.lookupSqlInfos.put(lookupField, lookupSqlInfo);
    }

    /**
     * 获取独立查询的关联对象的查询语句列表
     *
     * @return
     */
    public Map<LookupDataField, MongoSelectInfo> getIndividualLookupSqlInfos() {
        return individualLookupSqlInfos;
    }

    /**
     * 新增独立查询的关联对象的查询语句
     *
     * @param lookupSqlInfo
     */
    public void addIndividualLookupSqlInfo(LookupDataField lookupDataField, MongoSelectInfo lookupSqlInfo) {
        this.individualLookupSqlInfos.put(lookupDataField, lookupSqlInfo);
    }




    public static class SelectItem {
        private VisitValue param;

        private String alias;

        public VisitValue getParam() {
            return param;
        }

        public void setParam(VisitValue param) {
            this.param = param;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }

    public static class TableInfo {
        private String table;

        private String objectName;

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getObjectName() {
            return objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }
    }
}
