package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.util.XObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OQL语句解析器，解析的过程中会将识别到的模型、字段、属性保存下来
 *
 * @author clouds
 */
public class OqlStatementParser extends OqlExprParser {

    private SqlStatementParser sqlStmtParser;

    public OqlStatementParser(XObjectResolver resolver, String oql) {
        super(resolver, oql);
        this.sqlStmtParser = new SqlStatementParser(oql);
    }

    public OqlStatementParser(XObjectResolver resolver, String oql, Map<String, XObject> objectAliasMap) {
        super(resolver, oql, objectAliasMap);
        this.sqlStmtParser = new SqlStatementParser(oql);
    }

    /**
     * 解析 OQL 语句
     *
     * @return
     */
    public List<OqlStatement> parseStatementList() {
        List<SqlStatement> sqlStmts = this.sqlStmtParser.parseStatementList();
        List<OqlStatement> oqlStmts = new ArrayList<>();
        for (SqlStatement sqlStmt : sqlStmts) {
            OqlStatement oqlStmt;
            if (sqlStmt instanceof SqlSelectStatement) {
                OqlSelect oqlSelect = this.toOqlSelect(((SqlSelectStatement) sqlStmt).getSelect());
                oqlStmt = new OqlSelectStatement(oqlSelect);
            } else if (sqlStmt instanceof SqlInsertStatement) {
                SqlSelect query = ((SqlInsertStatement) sqlStmt).getQuery();
                OqlInsertInto oqlInsertInto = this.toOqlInsertInto((SqlInsertStatement) sqlStmt);
                if (query == null) {
                    oqlStmt = new OqlInsertStatement(oqlInsertInto);
                } else {
                    oqlStmt = new OqlInsertSelectStatement(oqlInsertInto);
                }
            } else if (sqlStmt instanceof SqlUpdateStatement) {
                oqlStmt = this.toOqlUpdateStatement((SqlUpdateStatement) sqlStmt);
            } else if (sqlStmt instanceof SqlDeleteStatement) {
                oqlStmt = this.toOqlDeleteStatement((SqlDeleteStatement) sqlStmt);
            } else {
                throw new FastOqlException("不支持的SQL语句类型：" + sqlStmt.getClass().getName());
            }
            oqlStmts.add(oqlStmt);
        }

        return oqlStmts;
    }

    /**
     * 将SQL查询转换为OQL查询
     *
     * @param sqlSelect
     * @return
     */
    private OqlSelect toOqlSelect(SqlSelect sqlSelect) {
        OqlSelect oqlSelect = new OqlSelect();
        oqlSelect.setParenthesized(sqlSelect.isParenthesized());
        oqlSelect.setDistinctOption(sqlSelect.getDistinctOption());
        SqlTableSource tableSource = sqlSelect.getFrom();
        OqlObjectSource objectSource = this.parseObjectSource(tableSource);
        oqlSelect.setFrom(objectSource);
        XObject resolvedObject = objectSource.getResolvedObject();

        List<SqlSelectItem> selectItems = sqlSelect.getSelectItems();
        for (SqlSelectItem selectItem : selectItems) {
            SqlExpr expr = selectItem.getExpr();
            SqlExpr exprX = this.parseSqlExpr(resolvedObject, expr);
            OqlSelectItem oqlSelectItem = new OqlSelectItem(exprX);
            oqlSelectItem.setAlias(selectItem.getAlias());
            oqlSelect.addSelectItem(oqlSelectItem);
        }

        SqlExpr sqlWhere = sqlSelect.getWhere();
        if (sqlWhere != null) {
            SqlExpr oqlWhere = this.toOqlWhere(resolvedObject, sqlWhere);
            oqlSelect.setWhere(oqlWhere);
        }

        SqlSelectGroupByClause groupByClause = sqlSelect.getGroupBy();
        if (groupByClause != null) {
            SqlSelectGroupByClause oqlGroupByClause = this.toOqlGroupByClause(resolvedObject, groupByClause);
            oqlSelect.setGroupBy(oqlGroupByClause);
        }

        SqlOrderBy orderBy = sqlSelect.getOrderBy();
        if (orderBy != null) {
            SqlOrderBy oqlOrderBy = this.toOqlOrderBy(resolvedObject, orderBy);
            oqlSelect.setOrderBy(oqlOrderBy);
        }

        oqlSelect.setLimit(sqlSelect.getLimit());

        return oqlSelect;
    }

    /**
     * 将SQL插入转换为OQL插入
     *
     * @param sqlInsertInto
     * @return
     */
    private OqlInsertInto toOqlInsertInto(SqlInsertInto sqlInsertInto) {
        OqlInsertInto oqlInsertInto = new OqlInsertInto();
        SqlExprTableSource tableSource = sqlInsertInto.getTableSource();
        OqlExprObjectSource objectSource = this.parseExprObjectSource(tableSource);
        oqlInsertInto.setObjectSource(objectSource);
        XObject resolvedObject = objectSource.getResolvedObject();

        List<SqlExpr> columns = sqlInsertInto.getColumns();
        for (SqlExpr column : columns) {
            SqlExpr columnX = this.parseSqlExpr(resolvedObject, column);
            if (!(columnX instanceof OqlExpr)) {
                throw new FastOqlException("OQL插入语句的列中只允许出现OqlExpr类型的表达式");
            }
            oqlInsertInto.addField((OqlExpr) columnX);
        }

        SqlSelect query = sqlInsertInto.getQuery();
        if (query == null) {
            oqlInsertInto.addValuesList(sqlInsertInto.getValuesList());
        } else {
            oqlInsertInto.setQuery(this.toOqlSelect(query));
        }

        return oqlInsertInto;
    }

    /**
     * 将SQL更新语句转换为OQL更新语句
     *
     * @param sqlUpdateStmt
     * @return
     */
    private OqlUpdateStatement toOqlUpdateStatement(SqlUpdateStatement sqlUpdateStmt) {
        OqlUpdateStatement oqlUpdateStmt = new OqlUpdateStatement();
        SqlExprTableSource tableSource = sqlUpdateStmt.getTableSource();
        OqlExprObjectSource objectSource = this.parseExprObjectSource(tableSource);
        oqlUpdateStmt.setObjectSource(objectSource);
        XObject resolvedObject = objectSource.getResolvedObject();

        List<SqlUpdateSetItem> setItems = sqlUpdateStmt.getSetItems();
        for (SqlUpdateSetItem setItem : setItems) {
            // 获取准确的字段类型
            SqlExpr column = setItem.getColumn();
            SqlExpr columnX = this.parseSqlExpr(resolvedObject, column);
            if (!(columnX instanceof OqlExpr)) {
                throw new FastOqlException("OQL更新语句的列中只允许出现OqlExpr类型的表达式");
            }

            SqlExpr valueX = this.parseSqlExpr(resolvedObject, setItem.getValue());
            OqlUpdateSetItem oqlSetItem = new OqlUpdateSetItem();
            oqlSetItem.setField((OqlExpr) columnX);
            oqlSetItem.setValue(valueX);
            oqlUpdateStmt.addSetItem(oqlSetItem);
        }
        SqlExpr sqlWhere = sqlUpdateStmt.getWhere();
        SqlExpr oqlWhere = this.toOqlWhere(resolvedObject, sqlWhere);
        oqlUpdateStmt.setWhere(oqlWhere);

        return oqlUpdateStmt;

    }

    /**
     * 将SQL删除语句转换为OQL删除语句
     *
     * @param sqlDeleteStmt
     * @return
     */
    private OqlDeleteStatement toOqlDeleteStatement(SqlDeleteStatement sqlDeleteStmt) {
        OqlDeleteStatement oqlDeleteStmt = new OqlDeleteStatement();
        SqlExprTableSource tableSource = sqlDeleteStmt.getFrom();
        OqlExprObjectSource objectSource = this.parseExprObjectSource(tableSource);
        oqlDeleteStmt.setFrom(objectSource);
        XObject resolvedObject = objectSource.getResolvedObject();
        SqlExpr sqlWhere = sqlDeleteStmt.getWhere();
        SqlExpr oqlWhere = this.toOqlWhere(resolvedObject, sqlWhere);
        oqlDeleteStmt.setWhere(oqlWhere);

        List<XObjectRefField> detailFields = XObjectUtils.getDetailFields(resolvedObject);
        if (detailFields.size() > 0) {
            // 解析出子模型的列表
            List<OqlExprObjectSource> detailObjectSources = new ArrayList<>();
            List<XObject> detailObjects = new ArrayList<>();
            for (XObjectRefField detailField : detailFields) {
                String detailObjectName = detailField.getRefObjectName();
                OqlExprObjectSource detailObjectSource = new OqlExprObjectSource(detailObjectName);
                XObject detailObject = this.resolver.resolve(detailObjectName);
                detailObjectSource.setResolvedObject(detailObject);
                detailObjectSources.add(detailObjectSource);
                detailObjects.add(detailObject);
            }
            oqlDeleteStmt.setDetailFroms(detailObjectSources);
            objectSource.setResolvedDetailObjects(detailObjects);
        }

        return oqlDeleteStmt;
    }

    /**
     * 将 SQL 语法的 where 条件转 OQL 语法的 where 条件
     *
     * @param resolvedObject
     * @param sqlWhere
     * @return
     */
    private SqlExpr toOqlWhere(XObject resolvedObject, SqlExpr sqlWhere) {
        SqlExpr oqlWhere = null;
        if (sqlWhere != null) {
            oqlWhere = this.parseSqlExpr(resolvedObject, sqlWhere);
            // where 条件语法检查（放在OqlEngineImpl的实现中）
            // WhereExprValidCheckAstVisitor whereCheckVisitor = new WhereExprValidCheckAstVisitor();
            // oqlWhere.accept(whereCheckVisitor);
        }

        return oqlWhere;
    }


    /**
     * 将 SQL group by 转换为 oql group by
     *
     * @param resolvedObject
     * @param groupByClause
     * @return
     */
    private SqlSelectGroupByClause toOqlGroupByClause(XObject resolvedObject, SqlSelectGroupByClause groupByClause) {
        SqlSelectGroupByClause oqlGroupBy = new SqlSelectGroupByClause();
        List<SqlExpr> groupItems = groupByClause.getItems();
        for (SqlExpr groupItem : groupItems) {
            oqlGroupBy.addItem(this.parseSqlExpr(resolvedObject, groupItem));
        }
        return oqlGroupBy;
    }

    /**
     * 将 SQL order by 转换为 oql order by
     *
     * @param resolvedObject
     * @param orderBy
     * @return
     */
    private SqlOrderBy toOqlOrderBy(XObject resolvedObject, SqlOrderBy orderBy) {
        SqlOrderBy oqlOrderBy = new SqlOrderBy();
        List<SqlSelectOrderByItem> orderByItems = orderBy.getItems();
        for (SqlSelectOrderByItem orderByItem : orderByItems) {
            SqlSelectOrderByItem oqlOrderByItem = new SqlSelectOrderByItem();
            oqlOrderByItem.setExpr(this.parseSqlExpr(resolvedObject, orderByItem.getExpr()));
            oqlOrderByItem.setAscending(orderByItem.isAscending());
            oqlOrderBy.addItem(oqlOrderByItem);
        }
        return oqlOrderBy;
    }

}
