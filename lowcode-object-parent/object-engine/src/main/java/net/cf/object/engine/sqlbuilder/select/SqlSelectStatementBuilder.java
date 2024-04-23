package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;

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
     * 关联的模型集合（避免重复JOIN）
     */
    private final Set<String> refObjectNames = new HashSet<>();

    public SqlSelectStatementBuilder() {
    }

    public SqlSelectStatementBuilder appendSelectItemInfo(SelectItemInfo itemInfo) {
        FieldMapping fieldMapping = itemInfo.getFieldMapping();
        List<SqlSelectItem> selectItems = itemInfo.getSelectItems();
        this.appendSelectItems(fieldMapping, selectItems);
        return this;
    }

    /**
     * 添加多个字段
     *
     * @param mapping
     * @param selectItems
     * @return
     */
    private SqlSelectStatementBuilder appendSelectItems(FieldMapping mapping, List<SqlSelectItem> selectItems) {
        this.fieldMappings.add(mapping);
        this.select.addSelectItems(selectItems);
        return this;
    }

    /**
     * 设置主表
     *
     * @param from
     */
    public SqlSelectStatementBuilder from(SqlExprTableSource from) {
        this.select.setFrom(from);
        return this;
    }

    /**
     * 设置（1:1）关联表
     *
     * @param objectRefField 关联的字段
     */
    public SqlSelectStatementBuilder refNonMultiRefObject(XObjectRefField objectRefField, XObject refObject) {
        assert (!objectRefField.isMultiRef());

        String refObjectName = objectRefField.getRefObjectName();
        if (refObjectNames.contains(refObjectName)) {
            return this;
        }
        refObjectNames.add(refObjectName);

        SqlExprTableSource joinTable = new SqlExprTableSource();
        joinTable.setExpr(refObject.getTableName());
        SqlBinaryOpExpr joinCondition = new SqlBinaryOpExpr();
        joinCondition.setOperator(SqlBinaryOperator.EQUALITY);
        /*{
            String leftTableName = objectRefField.getOwner().getTableName();
            String leftColumnName = objectRefField.getColumnName();
            joinCondition.setLeft(new SqlPropertyExpr(leftTableName, leftColumnName));
        }
        {
            String rightTableName = refObject.getTableName();
            String rightColumnName = refObject.getPrimaryField().getColumnName();
            joinCondition.setRight(new SqlPropertyExpr(rightTableName, rightColumnName));
        }*/
        //this.join(joinTable, joinCondition);

        return this;
    }

    /**
     * 构建 SqlJoinTableSource
     *
     * @param joinTable
     * @param joinCondition
     */
    /*private void join(SqlExprTableSource joinTable, SqlExpr joinCondition) {
        SqlJoinTableSource joinTableSource = new SqlJoinTableSource();
        joinTableSource.setLeft(this.select.getFrom());
        joinTableSource.setRight(joinTable);
        joinTableSource.setJoinType(SqlJoinTableSource.JoinType.LEFT_OUTER_JOIN);
        joinTableSource.setCondition(joinCondition);
        this.select.setFrom(joinTableSource);
    }*/

    /**
     * 设置查询表件
     *
     * @param where
     */
    public SqlSelectStatementBuilder where(SqlExpr where) {
        this.select.setWhere(where);
        return this;
    }

    /**
     * 设置分组
     *
     * @param groupBy
     */
    public SqlSelectStatementBuilder groupBy(SqlSelectGroupByClause groupBy) {
        this.select.setGroupBy(groupBy);
        return this;
    }

    /**
     * 设置排序
     *
     * @param orderBy
     */
    public SqlSelectStatementBuilder orderBy(SqlOrderBy orderBy) {
        this.select.setOrderBy(orderBy);
        return this;
    }

    /**
     * 设置限制数量
     *
     * @param limit
     */
    public SqlSelectStatementBuilder limit(SqlLimit limit) {
        this.select.setLimit(limit);
        return this;
    }

    /**
     * 添加一对多关联的模型展开表达式
     *
     * @param objectExpandExpr
     * @return
     */
    /*public SqlSelectStatementBuilder appendDetailObjectExpandExpr(OqlObjectExpandExpr objectExpandExpr) {
        assert (objectExpandExpr.getResolvedObjectRefField().isMultiRef());
        //this.detailObjectExpandExprs.add(objectExpandExpr);
        return this;
    }*/

    @Override
    public SqlSelectStatement build() {
        return new SqlSelectStatement(this.select);
    }

    /**
     * 获取一多对的关联表
     *
     * @return
     */
    /*public List<OqlObjectExpandExpr> getDetailObjectExpandExprs() {
        return detailObjectExpandExprs;
    }*/
}
