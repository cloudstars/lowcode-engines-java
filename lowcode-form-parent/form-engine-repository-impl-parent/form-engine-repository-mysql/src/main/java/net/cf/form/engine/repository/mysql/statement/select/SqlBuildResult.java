package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.JoinType;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlBuildResult {

    private SelectSqlInfo sqlInfo;

    /**
     * 本表的查询对象信息
     */
    private SelectObjectInfo selfSelectObjectInfo;

    /**
     * 相关联的查询对象的信息
     */
    private final Map<String, SelectObjectInfo> joinSelectObjectInfos = new HashMap<>();

    /**
     *
     */
    private final List<SelectItemInfo> selectItemInfos = new ArrayList<>();

    public SqlBuildResult(SelectSqlInfo sqlInfo) {
        this.sqlInfo = sqlInfo;
    }


    public SelectObjectInfo getSelfSelectObjectInfo() {
        return selfSelectObjectInfo;
    }

    public void setSelfSelectObjectInfo(SelectObjectInfo selfSelectObjectInfo) {
        this.selfSelectObjectInfo = selfSelectObjectInfo;
    }


    public Map<String, SelectObjectInfo> getJoinSelectObjectInfos() {
        return joinSelectObjectInfos;
    }

    /**
     * 根据对象的名称获取查询对象的信息
     *
     * @param objectAliasName
     * @return
     */
    public SelectObjectInfo getJoinSelectObjectInfoBy(String objectAliasName) {
        return this.joinSelectObjectInfos.get(objectAliasName);
    }

    /**
     * 根据对象的名称获取查询对象的信息
     *
     * @param objectAliasName
     * @return
     */
    public void addJoinSelectObjectInfo(String objectAliasName, SelectObjectInfo selectObjectInfo) {
        this.joinSelectObjectInfos.put(objectAliasName, selectObjectInfo);
    }

    /**
     * 获取全部的查询字段信息
     *
     * @return
     */
    public List<SelectItemInfo> getSelectItemInfos() {
        return selectItemInfos;
    }

    /**
     * 获取第i个查询字段的信息（i从0开始）
     *
     * @param index
     * @return
     */
    public SelectItemInfo getSelectItemInfoAt(int index) {
        assert (index >= 0 && index < this.selectItemInfos.size());

        return this.selectItemInfos.get(index);
    }


    /**
     * 追加一个查询字段信息
     *
     * @param itemInfo
     */
    public void addSelectItemInfo(SelectItemInfo itemInfo) {
        this.selectItemInfos.add(itemInfo);
    }

    /**
     *  查询的对象信息
     */
    public static class SelectObjectInfo {

        /**
         * 当前查询的对象
         */
        public DataObject object;

        /**
         * 当前对象与主对象的关联关联
         */
        public JoinType joinType;

        /**
         * 当前对象的在SQL的查询字段中的开始位置
         */
        public int startIndex = 0;

        /**
         * 当前对象的在SQL的查询字段中的结束位置
         */
        public int endIndex = 0;

        /**
         * 当前对象的Primary字段在SQL查询字段中的位置
         */
        public int primaryIndex = -1;
    }

    /**
     * 查询的列信息
     */
    public static class SelectItemInfo {
        /**
         * 归属的对象名称
         */
        public String objectAliasName;

        /**
         * 字段查询的别名
         */
        public String alias;

        /**
         * 是否额外增加的字段（比如记录ID），此类字段不需要返回给对象
         */
        public boolean isExtra;
    }

}
