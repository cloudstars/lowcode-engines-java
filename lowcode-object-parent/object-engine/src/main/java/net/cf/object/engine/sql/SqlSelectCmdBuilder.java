

package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.oql.ast.*;

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

    public SqlSelectCmdBuilder(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        this(stmt, paramMap, false);
    }

    public SqlSelectCmdBuilder(OqlSelectStatement stmt, Map<String, Object> paramMap, boolean isQueryOne) {
        super(stmt, !isQueryOne);
        this.paramMap = null;

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
        OqlSelect select = this.stmt.getSelect();;
        OqlObjectSource objectSource = select.getFrom();
        this.resolvedObject = objectSource.getResolvedObject();

        // 初始化 SQL 查询语句
        SqlSelect sqlSelect = new SqlSelect();
        for (OqlSelectItem selectItem : select.getSelectItems()) {
            this.buildSqlExpr(selectItem.getExpr());
        }
        sqlSelect.setFrom(this.buildSqlTableSource((OqlExprObjectSource) objectSource));

        SqlExpr where = select.getWhere();
        if (where != null) {
            SqlWhereBuilder whereBuilder = new SqlWhereBuilder(this.resolvedObject);
            SqlExpr sqlWhere = whereBuilder.parseExpr(where);
            select.setWhere(sqlWhere);
        }

        // 构建 SQL 查询指令
        SqlSelectCmd selectCmd = this.buildSqlSelectCmd();
        return selectCmd;
    }

    /**
     * 构建 SQL 查询指令
     *
     * @return
     */
    private SqlSelectCmd buildSqlSelectCmd() {
        SqlSelectCmd selectCmd = new SqlSelectCmd();
        return selectCmd;
    }
}
