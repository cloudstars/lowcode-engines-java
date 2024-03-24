package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SQL查询语句构建器
 *
 * @author clouds
 */
public class SqlSelectStatementBuilder extends AbstractSqlStatementBuilder<OqlSelectStatement, SqlSelectStatement> {

    private final SqlSelect select = new SqlSelect();

    /**
     * 标识符集合（用于过滤一些重复的列）
     */
    private final Set<String> identifiers = new HashSet<>();

    /**
     * 关联的模型集合（避免重复JOIN）
     */
    private final Set<String> refObjectNames = new HashSet<>();

    /**
     * 查询字段的信息
     */
    private final List<SelectItemInfo> selectItemInfos = new ArrayList<>();

    public SqlSelectStatementBuilder() {
    }

    /**
     * 添加查询项信息
     *
     * @param itemInfo
     * @return
     */
    public SqlSelectStatementBuilder appendSelectItemInfo(SelectItemInfo itemInfo) {
        String fieldName = itemInfo.getFieldName();
        if (!identifiers.contains(fieldName)) {//去重
            identifiers.add(fieldName);
            this.selectItemInfos.add(itemInfo);
            List<SqlSelectItem> selectItems = itemInfo.getSelectItems();
            if (selectItems != null) { // 字段未展开
                this.select.addSelectItems(selectItems);
            } else { // 字段展开
                List<SelectItemInfo> subItemInfos = itemInfo.getSubItemInfos();
                for (SelectItemInfo subItemInfo : subItemInfos) {
                    List<SqlSelectItem> subSelectItems = subItemInfo.getSelectItems();
                    if (subSelectItems != null) {
                        this.select.addSelectItems(subSelectItems);
                    }
                }
            }
        }

        return this;
    }

    /**
     * 设置主表
     *
     * @param from
     */
    public void from(SqlExprTableSource from) {
        this.select.setFrom(from);
    }

    /**
     * 设置关联表
     *
     * @param objectRefField 关联的字段
     */
    public void refObject(XObjectRefField objectRefField, XObject refObject) {
        String refObjectName = objectRefField.getRefObjectName();
        if (refObjectNames.contains(refObjectName)) {
            return;
        }
        refObjectNames.add(refObjectName);

        SqlExprTableSource joinTable = new SqlExprTableSource();
        joinTable.setExpr(refObject.getTableName());
        SqlBinaryOpExpr joinCondition = new SqlBinaryOpExpr();
        joinCondition.setOperator(SqlBinaryOperator.EQUALITY);
        {
            String leftTableName = objectRefField.getOwner().getTableName();
            String leftColumnName = objectRefField.getColumnName();
            joinCondition.setLeft(new SqlPropertyExpr(leftTableName, leftColumnName));
        }
        {
            String rightTableName = refObject.getTableName();
            String rightColumnName = refObject.getPrimaryField().getColumnName();
            joinCondition.setRight(new SqlPropertyExpr(rightTableName, rightColumnName));
        }
        this.join(joinTable, joinCondition);
    }

    /**
     * 构建 SqlJoinTableSource
     *
     * @param joinTable
     * @param joinCondition
     */
    private void join(SqlExprTableSource joinTable, SqlExpr joinCondition) {
        SqlJoinTableSource joinTableSource = new SqlJoinTableSource();
        joinTableSource.setLeft(this.select.getFrom());
        joinTableSource.setRight(joinTable);
        joinTableSource.setJoinType(SqlJoinTableSource.JoinType.LEFT_OUTER_JOIN);
        joinTableSource.setCondition(joinCondition);
        this.select.setFrom(joinTableSource);
    }


    /**
     * 设置查询表件
     *
     * @param where
     */
    public void where(SqlExpr where) {
        this.select.setWhere(where);
    }

    /**
     * 设置分组
     *
     * @param groupBy
     */
    public void groupBy(SqlSelectGroupByClause groupBy) {
        this.select.setGroupBy(groupBy);
    }

    /**
     * 设置排序
     *
     * @param orderBy
     */
    public void orderBy(SqlOrderBy orderBy) {
        this.select.setOrderBy(orderBy);
    }

    /**
     * 设置限制数量
     *
     * @param limit
     */
    public void limit(SqlLimit limit) {
        this.select.setLimit(limit);
    }

    @Override
    public SqlSelectStatement build() {
        return new SqlSelectStatement(this.select);
    }

    /**
     * 获取查询字段的信息
     *
     * @return
     */
    public List<SelectItemInfo> getSelectItemInfos() {
        return selectItemInfos;
    }

}
