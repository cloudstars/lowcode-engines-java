package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL语句解析器
 *
 * @author clouds
 */
public class OqlStatementParser extends OqlExprParser {

    private SqlStatementParser sqlStmtParser;

    public OqlStatementParser(XObjectResolver resolver, String oql) {
        super(resolver, oql);
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
                OqlInsertInto oqlInsertInto = this.toOqlInsertInto((SqlInsertStatement) sqlStmt);
                oqlStmt = new OqlInsertStatement(oqlInsertInto);
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
        SqlTableSource tableSource = sqlSelect.getFrom();
        OqlObjectSource objectSource = this.parseObjectSource(tableSource);
        oqlSelect.setFrom(objectSource);
        XObject resolvedObject = objectSource.getResolvedObject();

        List<SqlSelectItem> selectItems = sqlSelect.getSelectItems();
        for (SqlSelectItem selectItem : selectItems) {
            SqlExpr expr = selectItem.getExpr();
            SqlExpr exprX = this.parseSqlExpr(resolvedObject, expr);
            if (exprX != expr) {
                selectItem.setExpr(exprX);
            }
            oqlSelect.addSelectItem(selectItem);
        }
        SqlExpr sqlWhere = sqlSelect.getWhere();
        SqlExpr oqlWhere = this.toValidOqlWhere(resolvedObject, sqlWhere);
        oqlSelect.setWhere(oqlWhere);
        oqlSelect.setGroupBy(sqlSelect.getGroupBy());
        oqlSelect.setOrderBy(sqlSelect.getOrderBy());
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
            // 获取精确的字段类型
            oqlInsertInto.addField(this.parseSqlExpr(resolvedObject, column));
        }
        oqlInsertInto.addValuesList(sqlInsertInto.getValuesList());

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
            // 获取准备的字段类型
            SqlExpr column = setItem.getColumn();
            setItem.setColumn(this.parseSqlExpr(resolvedObject, column));
            setItem.setValue(setItem.getValue());
            oqlUpdateStmt.addSetItem(setItem);
        }
        SqlExpr sqlWhere = oqlUpdateStmt.getWhere();
        SqlExpr oqlWhere = this.toValidOqlWhere(resolvedObject, sqlWhere);
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
        SqlExpr sqlWhere = oqlDeleteStmt.getWhere();
        SqlExpr oqlWhere = this.toValidOqlWhere(resolvedObject, sqlWhere);
        oqlDeleteStmt.setWhere(oqlWhere);
        return oqlDeleteStmt;
    }

    /**
     * 将 SQL 语法的 where 条件转换合法的 OQL 语法的 where 条件
     *
     * @param resolvedObject
     * @param sqlWhere
     * @return
     */
    private SqlExpr toValidOqlWhere(XObject resolvedObject, SqlExpr sqlWhere) {
        SqlExpr oqlWhere = null;
        if (sqlWhere != null) {
            oqlWhere = this.parseSqlExpr(resolvedObject, sqlWhere);
            // where 条件语法检查
            WhereExprValidCheckAstVisitor whereCheckVisitor = new WhereExprValidCheckAstVisitor();
            oqlWhere.accept(whereCheckVisitor);
        }

        return oqlWhere;
    }

}
