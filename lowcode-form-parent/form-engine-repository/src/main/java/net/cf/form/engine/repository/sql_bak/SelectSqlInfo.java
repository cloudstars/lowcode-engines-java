package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.statement.*;
import net.cf.form.engine.repository.oql.parser.ParserException;
import net.cf.form.engine.repository.oql.ast.statement.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询语句SQL信息
 *
 * @author clouds
 */
@Deprecated
public class SelectSqlInfo extends AbstractQueryableSqlInfo<OqlSelectStatement, SelectDetailSqlInfo> {

    /**
     * 本对象的本表字段
     */
    private final List<OqlSelectItem> selfSelectItems = new ArrayList<>();

    /**
     * 所有相关联的（主、子、相关）对象
     */
    private final Map<String, DataObject> relaObjects = new HashMap<>();

    /**
     * 子对象查询的字段
     */
    private final Map<String, OqlSelectItem> detailSelectItems = new HashMap<>();

    /**
     * 主对象查询的字段
     */
    private final Map<String, OqlSelectItem> masterSelectItems = new HashMap<>();

    /**
     * 相关对象（一对一关联，一多关联通过子查询解决）查询的字段
     */
    private final Map<String, OqlSelectItem> lookupSelectItems = new HashMap<>();


    private OqlSelectGroupBy groupBy;

    private OqlSelectOrderBy orderBy;

    private OqlSelectLimit limit;

    public SelectSqlInfo(DataObject object) {
        super(object);
    }

    public List<OqlSelectItem> getSelfSelectItems() {
        return selfSelectItems;
    }

    public void addSelfSelectItems(List<OqlSelectItem> selectItems) {
        this.selfSelectItems.addAll(selectItems);
        this.remarkPrimaryFieldIndex();
    }

    public void addSelfSelectItem(OqlSelectItem selectItem) {
        this.selfSelectItems.add(selectItem);
        this.remarkPrimaryFieldIndex();
    }

    /**
     * 重新计算主键的位置
     */
    private void remarkPrimaryFieldIndex() {
        if (this.getPrimaryFieldIndex() < 0) {
            String primaryFieldName = this.object.getPrimaryField().getName();
            for (int i = 0; i < selfSelectItems.size(); i++) {
                if (primaryFieldName.equalsIgnoreCase(selfSelectItems.get(i).getAlias())) {
                    this.setPrimaryFieldIndex(i);
                    break;
                }
            }
        }
    }

    public Map<String, DataObject> getRelaObjects() {
        return relaObjects;
    }

    public DataObject getRelaObject(String objectFileName) {
        return relaObjects.get(objectFileName);
    }

    public Map<String, OqlSelectItem> getDetailSelectItems() {
        return detailSelectItems;
    }

    public Map<String, OqlSelectItem> getMasterSelectItems() {
        return masterSelectItems;
    }

    public Map<String, OqlSelectItem> getLookupSelectItems() {
        return lookupSelectItems;
    }

    /**
     * 添加子对象的字段清单
     *
     * @param objectFieldName
     * @param detailObject
     * @param selectItems
     * @param alias
     */
    public void addDetailSelectItems(String objectFieldName, DataObject detailObject, OqlListExpr selectItems, String alias) {
        this.addJoinObjectSelectItems(objectFieldName, selectItems, alias, new JoinObjectSelectItemsFunction() {
                    @Override
                    public OqlSelectItem getSelectItem(String objectFieldName) {
                        return detailSelectItems.get(objectFieldName);
                    }

                    @Override
                    public void putSelectItem(String objectFieldName, OqlSelectItem selectItem) {
                        detailSelectItems.put(objectFieldName, selectItem);
                    }
                }
        );
        this.relaObjects.put(objectFieldName, detailObject);
    }

    /**
     * 添加主对象的字段清单
     *
     * @param objectFieldName
     * @param masterObject
     * @param selectItems
     * @param alias
     */
    public void addMasterSelectItems(String objectFieldName, DataObject masterObject, OqlListExpr selectItems, String alias) {
        this.addJoinObjectSelectItems(objectFieldName, selectItems, alias, new JoinObjectSelectItemsFunction() {
                    @Override
                    public OqlSelectItem getSelectItem(String objectFieldName) {
                        return masterSelectItems.get(objectFieldName);
                    }

                    @Override
                    public void putSelectItem(String objectFieldName, OqlSelectItem selectItem) {
                        masterSelectItems.put(objectFieldName, selectItem);
                    }
                }
        );
        this.relaObjects.put(objectFieldName, masterObject);
    }


    /**
     * 添加关联对象的字段清单
     *
     * @param objectFieldName
     * @param lookupObject
     * @param selectItems
     * @param alias
     */
    public void addLookupSelectItems(String objectFieldName, DataObject lookupObject, OqlListExpr selectItems, String alias) {
        this.addJoinObjectSelectItems(objectFieldName, selectItems, alias, new JoinObjectSelectItemsFunction() {
                    @Override
                    public OqlSelectItem getSelectItem(String objectFieldName) {
                        return lookupSelectItems.get(objectFieldName);
                    }

                    @Override
                    public void putSelectItem(String objectFieldName, OqlSelectItem selectItem) {
                        lookupSelectItems.put(objectFieldName, selectItem);
                    }
                }
        );
        this.relaObjects.put(objectFieldName, lookupObject);
    }

    /**
     * 添加有关联的对象（主、子、相关）的查询字段
     *
     * @param objectFieldName
     * @param selectItems
     * @param alias
     * @param joinObjectSelectItemsFunction
     */
    private void addJoinObjectSelectItems(String objectFieldName, OqlListExpr selectItems, String alias, JoinObjectSelectItemsFunction joinObjectSelectItemsFunction) {
        /**
         * 对于展开的数据，如果没有设置别名，使用展开的字段名称作为别名，别名用于归并数据到主数据时作为key作用
         * 如：{"f1": "xx", "f2": "yy", "alias": []}
         */
        if (alias == null) {
            alias = objectFieldName;
        }

        OqlSelectItem joinSelectItem = joinObjectSelectItemsFunction.getSelectItem(objectFieldName);
        if (joinSelectItem == null) {
            joinSelectItem = new OqlSelectItem(selectItems, alias);
            joinObjectSelectItemsFunction.putSelectItem(objectFieldName, joinSelectItem);
        } else {
            // 合并字段（TODO 此次没有考虑字段重复的问题）
            String detailSelectItemAlias = joinSelectItem.getAlias();
            if (detailSelectItemAlias != null && alias != null && !detailSelectItemAlias.equalsIgnoreCase(alias)) {
                throw new ParserException("字段" + objectFieldName + "展开时的别名不一致");
            }

            if (detailSelectItemAlias == null && alias != null) {
                joinSelectItem.setAlias(alias);
            }

            OqlListExpr existsListItems = (OqlListExpr) joinSelectItem.getExpr();
            existsListItems.addItems(selectItems.getItems());
        }
    }

    /**
     * 有关联的对象（主、子、相关）处理SelectItem的函数
     *
     * @author clouds
     */
    interface JoinObjectSelectItemsFunction {
        OqlSelectItem getSelectItem(String objectFieldName);

        void putSelectItem(String objectFieldName, OqlSelectItem selectItem);
    }

    public OqlSelectGroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(OqlSelectGroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public OqlSelectOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OqlSelectOrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public OqlSelectLimit getLimit() {
        return limit;
    }

    public void setLimit(OqlSelectLimit limit) {
        this.limit = limit;
    }

    @Override
    public OqlSelectStatement buildStatement() {
        return null;
    }
}
