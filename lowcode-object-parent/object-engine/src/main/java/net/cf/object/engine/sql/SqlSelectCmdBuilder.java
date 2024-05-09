

package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.util.OqlUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * SQL 查询指令构建器
 * <p>
 * 职责：用于将一条 OQL 删除语句构建成 SQL 删除指令
 * OQL语句示例：如：
 * select ... from object where ...
 */
public class SqlSelectCmdBuilder extends AbstractSqlCmdBuilder<OqlSelectStatement, SqlSelectCmd> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 解析后的查询语句
     */
    private SqlSelectStatement sqlStmt;

    /**
     * 是否通过子表字段直接查询的（比如：select detailField from masterObject
     */
    private boolean isDetailFieldDirectQuery;

    private int nonFieldSelectItemIndex = 0;

    /**
     * 字段映射表
     */
    private final List<FieldMapping> fieldMappings = new ArrayList<>();

    public SqlSelectCmdBuilder(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        this(stmt, paramMap, false);
    }

    public SqlSelectCmdBuilder(OqlSelectStatement stmt, Map<String, Object> paramMap, boolean isQueryList) {
        super(stmt, isQueryList);
        this.paramMap = paramMap;
        this.checkStmt(this.stmt);
    }

    /**
     * 校验 OQL 语句的合法性
     *
     * @param stmt
     */
    private void checkStmt(OqlSelectStatement stmt) {
    }

    /**
     * 解析成OQL插入语句指令信息
     *
     * @return
     */
    @Override
    public SqlSelectCmd build() {
        OqlSelect select = this.stmt.getSelect();
        OqlObjectSource objectSource = select.getFrom();
        this.resolvedObject = objectSource.getResolvedObject();

        // 初始化 SQL 查询语句
        SqlSelect sqlSelect = new SqlSelect();
        this.sqlStmt = new SqlSelectStatement(sqlSelect);
        List<OqlSelectItem> selectItems = select.getSelectItems();
        if (selectItems.size() == 0) {
            OqlSelectItem selectItem = new OqlSelectItem();
            selectItem.setExpr(OqlUtils.defaultFieldExpr(this.resolvedObject.getPrimaryField()));
            selectItems = Arrays.asList(selectItem);
            // 特列处理，如果只查询了子表字段，默认会返回子表的ID数组
            this.isDetailFieldDirectQuery = true;
        }
        for (OqlSelectItem selectItem : selectItems) {
            SqlExpr selectItemExpr = selectItem.getExpr();
            String alias = selectItem.getAlias();

            // OQL语句中不含这两个表达式，如果有的话，一定是程序处理出错了
            assert (!(selectItemExpr instanceof SqlIdentifierExpr));
            assert (!(selectItemExpr instanceof SqlPropertyExpr));
            assert (!(selectItemExpr instanceof OqlObjectExpandExpr));

            if (selectItemExpr instanceof SqlAllColumnExpr) {
                this.addObjectAllFieldsToStmt();
            } else if (selectItemExpr instanceof OqlFieldExpr) {
                this.parseSelectFieldExpr((OqlFieldExpr) selectItemExpr, alias);
            } else if (selectItemExpr instanceof OqlPropertyExpr) {
                throw new FastOqlException("查询字段中不允许出现字段属性!");
            } else {
                this.parseExpr(selectItemExpr, alias);
            }
        }

        // 解析表源
        sqlSelect.setFrom(this.buildSqlTableSource((OqlExprObjectSource) objectSource));

        // 解析条询条件
        SqlExpr where = select.getWhere();
        if (where != null) {
            SqlWhereBuilder whereBuilder = new SqlWhereBuilder(this.resolvedObject);
            SqlExpr sqlWhere = whereBuilder.parseExpr(where);
            sqlSelect.setWhere(sqlWhere);
        }

        // 解析分组
        SqlSelectGroupByClause groupByClause = select.getGroupBy();
        if (groupByClause != null) {
            SqlSelectGroupByClause groupByClauseX = this.parseGroupByClause(groupByClause);
            sqlSelect.setGroupBy(groupByClauseX);
        }

        // 解析排序
        SqlOrderBy orderBy = select.getOrderBy();
        if (orderBy != null) {
            SqlOrderBy orderByX = this.parseOrderBy(orderBy);
            sqlSelect.setOrderBy(orderByX);
        }

        // 解析限制行数
        SqlLimit limit = select.getLimit();
        if (limit != null) {
            sqlSelect.setLimit(limit);
        }

        // 构建 SQL 查询指令
        SqlSelectCmd selectCmd = this.buildSqlSelectCmd();
        return selectCmd;
    }

    /**
     * 将一个模型的全部字段添加到模型对应的查询语句的字段中
     *
     */
    private void addObjectAllFieldsToStmt() {
        List<XField> fields = this.resolvedObject.getFields();
        for (XField field : fields) {
            if (field instanceof XObjectRefField && ((XObjectRefField) field).getRefType() == ObjectRefType.DETAIL) {
                continue;
            }
            this.addFieldToStmt(field, null);
        }
    }

    /**
     * 解析查询模型的字段，select field from object
     *
     * @param fieldExpr
     * @param alias
     */
    private void parseSelectFieldExpr(OqlFieldExpr fieldExpr, String alias) {
        XField field = fieldExpr.getResolvedField();
        this.addFieldToStmt(field, alias);
    }


    /**
     * 将一个模型的字段添加到模型对应的查询语句的字段中
     *
     * @param field
     * @param alias
     * @return
     */
    private void addFieldToStmt(XField field, String alias) {
        SqlSelect select = this.sqlStmt.getSelect();

        String name = alias != null ? alias : field.getName();
        FieldMapping fieldMapping = new FieldMapping(name, field.getColumnName());
        fieldMapping.setValueType(new ValueTypeImpl(field.getDataType(), field.isArray()));
        this.fieldMappings.add(fieldMapping);

        List<XProperty> properties = field.getProperties();
        if (properties.size() > 0) {
            List<FieldMapping> subFieldMappings = new ArrayList<>();
            for (XProperty property : properties) {
                SqlIdentifierExpr identExpr = new SqlIdentifierExpr(property.getColumnName());
                SqlSelectItem selectItem = new SqlSelectItem(identExpr);
                select.addSelectItem(selectItem);
                FieldMapping subFieldMapping = new FieldMapping(property.getName(), property.getColumnName());
                subFieldMapping.setValueType(new ValueTypeImpl(property.getDataType(), property.isArray()));
                subFieldMappings.add(subFieldMapping);
            }
            fieldMapping.addSubFields(subFieldMappings);
        } else {
            SqlIdentifierExpr identExpr = new SqlIdentifierExpr(field.getColumnName());
            identExpr.setAutoGen(field.isAutoGen());
            SqlSelectItem selectItem = new SqlSelectItem(identExpr);
            select.addSelectItem(selectItem);
        }
    }

    /**
     * 解析普通的非字段类表达式
     *
     * @param expr
     * @param alias
     */
    private void parseExpr(SqlExpr expr, String alias) {
        SqlExpr exprX = this.buildSqlExpr(expr);
        SqlSelect select = this.sqlStmt.getSelect();
        String itemAlias = "_" + nonFieldSelectItemIndex++;
        select.addSelectItem(new SqlSelectItem(exprX, itemAlias));

        String columnName = OqlUtils.expr2String(expr);
        String name = alias != null ? alias : columnName;
        FieldMapping fieldMapping = new FieldMapping(name, itemAlias);
        fieldMappings.add(fieldMapping);
    }


    /**
     * 分析分组子句
     *
     * @param groupByClause
     * @return
     */
    private SqlSelectGroupByClause parseGroupByClause(SqlSelectGroupByClause groupByClause) {
        SqlSelectGroupByClause groupByClauseX = new SqlSelectGroupByClause();
        List<SqlExpr> groupByItems = groupByClause.getItems();
        for (SqlExpr groupByItem : groupByItems) {
            SqlExpr groupByItemX = this.buildSqlExpr(groupByItem);
            groupByClauseX.addItem(groupByItemX);
        }

        return groupByClauseX;
    }

    /**
     * 解析orderBy
     *
     * @param orderBy
     * @return
     */
    private SqlOrderBy parseOrderBy(SqlOrderBy orderBy) {
        SqlOrderBy orderByX = new SqlOrderBy();
        List<SqlSelectOrderByItem> orderByItems = orderBy.getItems();
        for (SqlSelectOrderByItem orderByItem : orderByItems) {
            SqlSelectOrderByItem orderByItemX = new SqlSelectOrderByItem();
            orderByItemX.setExpr(this.buildSqlExpr(orderByItem.getExpr()));
            orderByItemX.setAscending(orderByItem.isAscending());
            orderByX.addItem(orderByItemX);
        }

        return orderByX;
    }


    /**
     * 构建 SQL 查询指令
     *
     * @return
     */
    private SqlSelectCmd buildSqlSelectCmd() {
        SqlSelectCmd selectCmd = new SqlSelectCmd();
        selectCmd.setResolvedObject(this.resolvedObject);
        selectCmd.setBatch(isBatch);
        selectCmd.setStatement(this.sqlStmt);
        selectCmd.setParamMap(this.paramMap);
        selectCmd.setFieldMappings(this.fieldMappings);
        selectCmd.setDetailFieldDirectQuery(isDetailFieldDirectQuery);
        return selectCmd;
    }

}
