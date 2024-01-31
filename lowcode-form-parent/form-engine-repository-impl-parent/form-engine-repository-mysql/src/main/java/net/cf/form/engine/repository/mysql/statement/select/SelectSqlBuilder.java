package net.cf.form.engine.repository.mysql.statement.select;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.JoinType;
import net.cf.form.engine.repository.mysql.statement.AbstractSqlBuilder;
import net.cf.form.engine.repository.mysql.statement.OqlExprAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.statement.*;
import net.cf.form.engine.repository.sql_bak.SelectDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.util.CommaSeperatedListOutput;
import net.cf.form.engine.repository.oql.ast.statement.*;

import java.util.List;
import java.util.Map;

/**
 * 查询语句构建器
 *
 * @author clouds
 */
public class SelectSqlBuilder extends AbstractSqlBuilder<SelectSqlInfo> {

    private final SqlBuildResult buildResult = new SqlBuildResult(sqlInfo);

    private final StringBuilder builder = new StringBuilder();

    public SelectSqlBuilder(SelectSqlInfo sqlInfo) {
        super(sqlInfo);
    }

    public SqlBuildResult getBuildResult() {
        return buildResult;
    }

    @Override
    protected String buildSql() {
        DataObject object = this.sqlInfo.getObject();
        builder.append("select ");

        // 检查一些数据归并时需要的字段是否已经提供
        this.checkRequiredFields();

        int currentIndex = 0;
        // 添加本对象的字段
        currentIndex += this.addSelfSelectItems(currentIndex);
        // 添加子对象的字段（或有）
        currentIndex += this.addDetailSelectItems(currentIndex);
        // 添加主对象的字段（或有）
        currentIndex += this.addMasterSelectItems(currentIndex);
        // 添加关联对象的字段（或有）
        currentIndex += this.addLookupSelectItems(currentIndex);

        // 添加查询的表
        builder.append(" from ").append(object.getTableName());
        this.addSelectJoinTables();

        // 添加 where 条件
        OqlWhereClause whereClause = this.sqlInfo.getWhereClause();
        if (whereClause != null && whereClause.getExpr() != null) {
            builder.append(" where ");
            builder.append(exprAstVisitor.getSql(whereClause.getExpr()));
        }

        // 添加 group by 分组
        OqlSelectGroupBy groupBy = this.sqlInfo.getGroupBy();
        if (groupBy != null) {
            builder.append(" group by ");
            List<QqlExpr> groupByItems = groupBy.getItems();
            CommaSeperatedListOutput.output(builder, groupByItems, (groupByItem) -> {
                builder.append(exprAstVisitor.getSql(groupByItem));
            });
        }

        // 添加 order by 排序
        OqlSelectOrderBy orderBy = this.sqlInfo.getOrderBy();
        if (orderBy != null) {
            builder.append(" order by");
            List<OqlSelectOrderByItem> orderByItems = orderBy.getItems();
            for (OqlSelectOrderByItem orderByItem : orderByItems) {
                builder.append(" ");
                builder.append(exprAstVisitor.getSql(orderByItem.getExpr()));
                builder.append(orderByItem.isAscending() ? "acs" : "desc");
            }
        }

        // 添加 limit 限制行数
        OqlSelectLimit limit = this.sqlInfo.getLimit();
        if (limit != null) {
            builder.append(" limit ");
            if (limit.getOffset() >= 0) {
                builder.append(limit.getOffset()).append(", ");
            }
            builder.append(limit.getRowCount());
        }

        return builder.toString();
    }

    /**
     * 检查数据归档的字段是否已经提供
     * <p>
     * 当存在子表时，本表必需查询主键字段（primary），子表必须查询主表主键字段（master primary）
     * 当存在主表时，本表必需查询主表主键（master primary）字段，主表必须查询主键primary字段（primary）
     * 当存在相关时，本表必需查询相关表字段（lookup），相关表必须查询主键字段（primary）
     */
    private void checkRequiredFields() {
    }


    /**
     * 构建selectitem ，同时将字段添加到sql
     * @param selectItem
     * @param objectName
     * @param exprAstVisitor
     * @return
     */
    private SqlBuildResult.SelectItemInfo buildSelectItemInfo(OqlSelectItem selectItem, String objectName, OqlExprAstVisitor exprAstVisitor) {
        String fieldSql = exprAstVisitor.getSql(selectItem.getExpr());
        builder.append(fieldSql);

        String alias = selectItem.getAlias();
        if (alias == null) {
            QqlExpr itemExpr = selectItem.getExpr();
            if (itemExpr instanceof OqlIdentifierExpr) {
                alias = ((OqlIdentifierExpr) itemExpr).getName();
            } else {
                alias = fieldSql;
            }
        }
        SqlBuildResult.SelectItemInfo itemInfo = newSelectItemInfo(objectName, alias, false);
        return itemInfo;
    }

    /**
     * 添加本表的查询字段
     *
     * @param startIndex
     * @return 添加的查询字段的数量
     */
    private int addSelfSelectItems(int startIndex) {
        int count = 0;

        DataObject object = this.sqlInfo.getObject();
        String objectName = object.getName();
        List<OqlSelectItem> selfSelectItems = this.sqlInfo.getSelfSelectItems();
        int selfFieldSize = selfSelectItems.size();
        boolean hasJoinTables = this.sqlInfo.getRelaObjects().size() > 0;
        CommaSeperatedListOutput.output(builder, selfSelectItems, (selfSelectItem) -> {
            String fieldSql = exprAstVisitor.getSql(selfSelectItem.getExpr());

            String alias = selfSelectItem.getAlias();
            if (alias == null) {
                QqlExpr itemExpr = selfSelectItem.getExpr();
                if (itemExpr instanceof OqlIdentifierExpr) {
                    alias = ((OqlIdentifierExpr) itemExpr).getName();
                } else {
                    alias = fieldSql;
                }
            }

            if (hasJoinTables) {
                builder.append(object.getTableName()).append(".");
            }
            builder.append(fieldSql);
            SqlBuildResult.SelectItemInfo itemInfo = newSelectItemInfo(objectName, alias, false);
            this.buildResult.addSelectItemInfo(itemInfo);
        });
        count += selfFieldSize;


        // 添加多选相关表
        for (Map.Entry<String, SelectDetailSqlInfo> entry : this.sqlInfo.getDetailSqlInfoMap().entrySet()) {
            String objectFieldName = entry.getKey();
            if (count > 0) {
                builder.append(", ");
            }
            if (hasJoinTables) {
                builder.append(object.getTableName()).append(".");
            }
            builder.append(object.getField(objectFieldName).getColumnName());
            SqlBuildResult.SelectItemInfo itemInfo = newSelectItemInfo(objectName, objectFieldName, false);
            this.buildResult.addSelectItemInfo(itemInfo);
            count ++;
        }

        // 如果是子查询且没有主键，需要补充主键
        if (this.sqlInfo instanceof SelectDetailSqlInfo && this.sqlInfo.getPrimaryFieldIndex() < 0) {
            if (count > 0) {
                builder.append(", ");
            }
            if (hasJoinTables) {
                builder.append(object.getTableName()).append(".");
            }
            builder.append(object.getPrimaryField().getColumnName());
            SqlBuildResult.SelectItemInfo itemInfo = newSelectItemInfo(objectName, object.getPrimaryField().getName(), false);
            this.buildResult.addSelectItemInfo(itemInfo);
            count ++;
        }


        SqlBuildResult.SelectObjectInfo selfObjectInfo = new SqlBuildResult.SelectObjectInfo();
        selfObjectInfo.object = object;
        selfObjectInfo.startIndex = startIndex;
        selfObjectInfo.endIndex = startIndex + count;
        selfObjectInfo.primaryIndex = this.sqlInfo.getPrimaryFieldIndex();
        this.buildResult.setSelfSelectObjectInfo(selfObjectInfo);

        return count;
    }

    /**
     * 添加子表的查询字段
     *
     * @param startIndex
     * @return 添加的查询字段的数量
     */
    private int addDetailSelectItems(int startIndex) {
        SqlBuildResult.SelectObjectInfo selfObjectInfo = this.buildResult.getSelfSelectObjectInfo();
        int count = 0;
        for (Map.Entry<String, OqlSelectItem> entry : this.sqlInfo.getDetailSelectItems().entrySet()) {
            String objectFieldName = entry.getKey();
            DataObject detailObject = this.sqlInfo.getRelaObject(objectFieldName);
            assert (detailObject != null);
            OqlSelectItem detailSelectItem = entry.getValue();
            assert (detailSelectItem.getExpr() instanceof OqlListExpr);
            int detailFieldSize = this.appendObjectExpandFields(detailObject, detailSelectItem);

            SqlBuildResult.SelectObjectInfo detailObjectInfo = new SqlBuildResult.SelectObjectInfo();
            detailObjectInfo.joinType = JoinType.DETAIL;
            detailObjectInfo.startIndex = startIndex;
            detailObjectInfo.endIndex = startIndex + detailFieldSize;

            // 判断本表的主键（primary）字段与子表的主键（primary）字段是否存在，若不存在则补充
            if (selfObjectInfo.primaryIndex < 0) {
                if (this.supplySelfPrimaryField(detailObjectInfo.endIndex)) {
                    detailObjectInfo.endIndex++;
                }
            }

            // 检查子表的主键是否存在，如果不存在的话，在SQL语句中以及相应的字段信息SelectItemInfo中添加
            DataField detailPrimaryField = detailObject.getPrimaryField();
            String detailPrimaryFieldName = detailPrimaryField.getName();
            List<QqlExpr> detailExprs = ((OqlListExpr) detailSelectItem.getExpr()).getItems();
            for (int i = 0; i < detailFieldSize; i++) {
                OqlSelectItem item = (OqlSelectItem) detailExprs.get(i);
                String itemName = item.getAlias();
                if (detailPrimaryFieldName.equals(itemName)) {
                    detailObjectInfo.primaryIndex = startIndex + i;
                    break;
                }
            }
            if (detailObjectInfo.primaryIndex == -1) {
                builder.append(", ").append(detailObject.getTableName()).append(".").append(detailPrimaryField.getColumnName());
                this.buildResult.addSelectItemInfo(newSelectItemInfo(detailSelectItem.getAlias(), detailPrimaryField.getName(), true));
                detailObjectInfo.primaryIndex = detailObjectInfo.endIndex;
                detailObjectInfo.endIndex++;
            }

            String objectAliasName = entry.getValue().getAlias();
            this.buildResult.addJoinSelectObjectInfo(objectAliasName, detailObjectInfo);
            count += detailFieldSize;
            startIndex = detailObjectInfo.endIndex;
        }

        return count;
    }

    /**
     * 补充本表的主键字段（数据合并时需要）
     *
     * @param index 补充的位置
     */
    private boolean supplySelfPrimaryField(int index) {
        SqlBuildResult.SelectObjectInfo selfObjectInfo = buildResult.getSelfSelectObjectInfo();
        int primaryFieldIndex = selfObjectInfo.primaryIndex;
        if (primaryFieldIndex == -1) {
            DataObject object = this.sqlInfo.getObject();
            DataField primaryField = object.getPrimaryField();
            builder.append(",").append(object.getTableName()).append(".").append(primaryField.getColumnName());
            this.buildResult.addSelectItemInfo(newSelectItemInfo(object.getName(), primaryField.getName(), true));
            selfObjectInfo.primaryIndex = index;
            return true;
        }

        return false;
    }

    /**
     * 添加子表的查询字段
     *
     * @param startIndex
     * @return 添加的查询字段的数量
     */
    private int addMasterSelectItems(int startIndex) {
        SqlBuildResult.SelectObjectInfo selfObjectInfo = this.buildResult.getSelfSelectObjectInfo();
        int count = 0;
        for (Map.Entry<String, OqlSelectItem> entry : this.sqlInfo.getMasterSelectItems().entrySet()) {
            String objectFieldName = entry.getKey();
            DataObject masterObject = this.sqlInfo.getRelaObject(objectFieldName);
            assert (masterObject != null);
            int masterFieldSize = this.appendObjectExpandFields(masterObject, entry.getValue());

            SqlBuildResult.SelectObjectInfo objectInfo = new SqlBuildResult.SelectObjectInfo();
            objectInfo.joinType = JoinType.MASTER;
            objectInfo.startIndex = startIndex;
            objectInfo.endIndex = startIndex + masterFieldSize;

            // 判断本表的主键（primary）字段与子表的主键（primary）字段是否存在，若不存在则补充
            if (selfObjectInfo.primaryIndex < 0) {
                if (this.supplySelfPrimaryField(objectInfo.endIndex)) {
                    objectInfo.endIndex++;
                }
            }

            String objectAliasName = entry.getValue().getAlias();
            this.buildResult.addJoinSelectObjectInfo(objectAliasName, objectInfo);
            count += masterFieldSize;
        }

        return count;
    }

    /**
     * 添加相关表的查询字段
     *
     * @param startIndex
     * @return 添加的查询字段的数量
     */
    private int addLookupSelectItems(int startIndex) {
        SqlBuildResult.SelectObjectInfo selfObjectInfo = this.buildResult.getSelfSelectObjectInfo();

        int count = 0;
        for (Map.Entry<String, OqlSelectItem> entry : this.sqlInfo.getLookupSelectItems().entrySet()) {
            String objectFieldName = entry.getKey();
            DataObject lookupObject = this.sqlInfo.getRelaObject(objectFieldName);
            assert (lookupObject != null);
            int lookupFieldSize = this.appendObjectExpandFields(lookupObject, entry.getValue());

            SqlBuildResult.SelectObjectInfo objectInfo = new SqlBuildResult.SelectObjectInfo();
            objectInfo.joinType = JoinType.LOOKUP;
            objectInfo.startIndex = startIndex;
            objectInfo.endIndex = startIndex + lookupFieldSize;

            // 判断本表的主键（primary）字段与子表的主键（primary）字段是否存在，若不存在则补充
            if (selfObjectInfo.primaryIndex < 0) {
                if (this.supplySelfPrimaryField(objectInfo.endIndex)) {
                    objectInfo.endIndex++;
                }
            }

            String objectAliasName = entry.getValue().getAlias();
            this.buildResult.addJoinSelectObjectInfo(objectAliasName, objectInfo);
            count += lookupFieldSize;
            startIndex = objectInfo.endIndex;
        }

        return count;
    }


    /**
     * 添加对象展开的字段列表
     *
     * @param expandObject
     * @param selectItem
     * @return 展开的字段的数量
     */
    private int appendObjectExpandFields(DataObject expandObject, OqlSelectItem selectItem) {
        QqlExpr selectItemExpr = selectItem.getExpr();
        List<QqlExpr> fields = ((OqlListExpr) selectItemExpr).getItems();
        String objectTableName = expandObject.getTableName();
        builder.append(", ");
        OqlExprAstVisitor detailExprAstVisitor = new OqlExprAstVisitor(expandObject);
        CommaSeperatedListOutput.output(builder, fields, (field) -> {
            assert (field instanceof OqlSelectItem);
            OqlSelectItem detailSelectItem = (OqlSelectItem) field;
            builder.append(objectTableName);
            builder.append(".");
            SqlBuildResult.SelectItemInfo itemInfo = buildSelectItemInfo(detailSelectItem, detailSelectItem.getAlias(), detailExprAstVisitor);
            buildResult.addSelectItemInfo(itemInfo);
        });

        return fields.size();
    }

    /**
     * 构建一个查询字段信息
     *
     * @param objectAliasName
     * @param alias
     * @param isExtra
     * @return
     */
    private SqlBuildResult.SelectItemInfo newSelectItemInfo(String objectAliasName, String alias, boolean isExtra) {
        SqlBuildResult.SelectItemInfo itemInfo = new SqlBuildResult.SelectItemInfo();
        itemInfo.objectAliasName = objectAliasName;
        itemInfo.alias = alias;
        itemInfo.isExtra = isExtra;
        return itemInfo;
    }


    /**
     * 添加关联查询的表信息
     */
    private void addSelectJoinTables() {
        DataObject object = this.sqlInfo.getObject();
        // 添加子对象的JOIN的表名（或有）
        for (Map.Entry<String, OqlSelectItem> entry : this.sqlInfo.getDetailSelectItems().entrySet()) {
            String objectFieldName = entry.getKey();
            DataObject detailObject = this.sqlInfo.getRelaObject(objectFieldName);
            this.appendObjectExpandJoinClause(object, detailObject, JoinType.DETAIL, null);
        }
        // 添加主对象的JOIN的表名（或有）
        for (Map.Entry<String, OqlSelectItem> entry : this.sqlInfo.getMasterSelectItems().entrySet()) {
            String objectFieldName = entry.getKey();
            DataObject masterObject = this.sqlInfo.getRelaObject(objectFieldName);
            this.appendObjectExpandJoinClause(object, masterObject, JoinType.MASTER, null);
        }
        // 添加关联对象的JOIN的表名（或有）
        for (Map.Entry<String, OqlSelectItem> entry : this.sqlInfo.getLookupSelectItems().entrySet()) {
            String objectFieldName = entry.getKey();
            DataObject lookupObject = this.sqlInfo.getRelaObject(objectFieldName);
            DataField lookupField = object.getField(objectFieldName);
            this.appendObjectExpandJoinClause(object, lookupObject, JoinType.LOOKUP, lookupField);
        }
    }

    /**
     * 添加目标对象的JOIN子句
     *
     * @param object       当前对象
     * @param expandObject 关联的展开对象
     * @param joinType     连接类型
     */
    private void appendObjectExpandJoinClause(DataObject object, DataObject expandObject, JoinType joinType, DataField lookupField) {
        String expandObjectTableName = expandObject.getTableName();
        builder.append(" left join ").append(expandObjectTableName).append(" on ");
        builder.append(object.getTableName()).append(".");
        if (joinType == JoinType.DETAIL) {
            // 本表的主键字段与子表的主表主键字段关联
            builder.append(object.getPrimaryField().getColumnName());
            builder.append(" = ").append(expandObjectTableName).append(".");
            builder.append(expandObject.getMasterField().getColumnName());
        } else if (joinType == JoinType.MASTER) {
            // 本表的主表主键字段与主表的主键字段关联
            builder.append(object.getMasterField().getColumnName());
            builder.append(" = ").append(expandObjectTableName).append(".");
            builder.append(expandObject.getPrimaryField().getColumnName());
        } else if (joinType == JoinType.LOOKUP) {
            // 本表的相关表字段与相关表的主键字段关联
            builder.append(lookupField.getColumnName());
            builder.append(" = ").append(expandObjectTableName).append(".");
            builder.append(expandObject.getPrimaryField().getColumnName());
        } else {
            throw new RuntimeException("不支持的JoinType类型：" + joinType);
        }
    }

}
