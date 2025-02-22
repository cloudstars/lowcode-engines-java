

package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.object.*;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.util.OqlUtils;

import java.util.*;

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
     * 查询字段的序号（如果没有给非模型字段添加as时，为默认添加as _n, n从0开始）
     */
    private int selectItemIndex = 0;

    /**
     * 字段映射表
     */
    private final List<FieldMapping> fieldMappings = new ArrayList<>();

    /**
     * 在查询的字段中直接可以输出的关联表的值
     */
    private final Map<String, Object> directResultMap = new HashMap<>();

    /**
     * 是否所有的列都是直接计算的
     */
    private boolean isAllColumnsDirectReturned = false;

    /**
     * 主查询的语名的模型
     */
    private XObject mainObject = null;

    /**
     * 主查询的语名的模型的别名
     */
    private String mainObjectAlias = null;

    public SqlSelectCmdBuilder(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        this(stmt, paramMap, false);
    }

    public SqlSelectCmdBuilder(OqlSelectStatement stmt, XObject mainObject, String mainObjectAlias) {
        this(stmt, null, mainObject, mainObjectAlias);
    }

    /**
     *
     * @param stmt
     * @param mainObject 当解析子查询时，可以传入主查询的模型
     * @param mainObject
     */
    public SqlSelectCmdBuilder(OqlSelectStatement stmt, Map<String, Object> paramMap, XObject mainObject, String mainObjectAlias) {
        this(stmt, paramMap, false);
        this.mainObject = mainObject;
        this.mainObjectAlias = mainObjectAlias;
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

        // 特殊处理，如果存在别名时，主查询当作子查询对待，设置mainObject和mainObjectAlias，用于给exists传递参数（SqlWhereBuilder时，最终传递给了parseExistsExpr），有点hardcode，今后可优化
        if (objectSource.getAlias() != null && this.mainObject == null) {
            this.mainObject = objectSource.getResolvedObject();
            this.mainObjectAlias = objectSource.getAlias();
        }

        // 初始化 SQL 查询语句
        SqlSelect sqlSelect = new SqlSelect();
        sqlSelect.setParenthesized(select.isParenthesized());
        sqlSelect.setDistinctOption(select.getDistinctOption());
        this.sqlStmt = new SqlSelectStatement(sqlSelect);
        List<OqlSelectItem> selectItems = select.getSelectItems();
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

        // 所有字段都是可以直接返回的，默认select 1
        if (selectItems.size() == this.directResultMap.size()) {
            String alias = OqlUtils.getSelectItemIndexAlias(this.selectItemIndex++);
            sqlStmt.getSelect().addSelectItem(new SqlSelectItem(new SqlIntegerExpr(1), alias));
            this.isAllColumnsDirectReturned = true;
        }

        // 解析表源
        sqlSelect.setFrom(this.buildSqlTableSource((OqlExprObjectSource) objectSource));

        // 解析条询条件
        SqlExpr where = select.getWhere();
        if (where != null) {
            SqlWhereBuilder whereBuilder = new SqlWhereBuilder(this.resolvedObject, this.mainObject, this.mainObjectAlias);
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
        String sqlAlias = OqlUtils.getSelectItemIndexAlias(this.selectItemIndex++);
        if (expr instanceof SqlValuableExpr) {
            // 可以直接返回的值
            String name = alias != null ? alias : sqlAlias;
            this.directResultMap.put(name, ((SqlValuableExpr) expr).getValue());
        } else {
            select.addSelectItem(new SqlSelectItem(exprX, sqlAlias));
            String columnName = OqlUtils.expr2String(expr);
            String name = alias != null ? alias : columnName;
            FieldMapping fieldMapping = new FieldMapping(name, sqlAlias);
            fieldMappings.add(fieldMapping);
        }
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
        selectCmd.setDirectResultMap(this.directResultMap);
        selectCmd.setAllColumnsDirectReturned(this.isAllColumnsDirectReturned);
        return selectCmd;
    }

}
