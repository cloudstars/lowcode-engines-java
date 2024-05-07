

package net.cf.object.engine.oqlnew.sql;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;

import java.util.List;
import java.util.Map;

/**
 * SQL 删除指令构建器
 * <p>
 * 职责：用于将一条 OQL 删除语句构建成 SQL 删除指令
 * OQL语句示例：如：
 * delete from object where ...
 * delete from object where ...
 */
public class SqlDeleteCmdBuilder extends AbstractSqlCmdBuilder<OqlDeleteStatement, SqlDeleteCmd> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;

    /**
     * 模型关联的删除语句
     */
    private SqlDeleteStatement sqlStmt;

    public SqlDeleteCmdBuilder(OqlDeleteStatement stmt, Map<String, Object> paramMap) {
        super(stmt, false);
        this.paramMap = paramMap;
        this.paramMaps = null;

        this.checkStmt(this.stmt);
    }

    public SqlDeleteCmdBuilder(OqlDeleteStatement stmt, List<Map<String, Object>> paramMaps) {
        super(stmt, true);
        this.paramMap = null;
        this.paramMaps = paramMaps;

        this.checkStmt(this.stmt);
    }

    /**
     * 校验 OQL 语句的合法性
     *
     * @param stmt
     */
    private void checkStmt(OqlDeleteStatement stmt) {
    }


    /**
     * 解析成OQL插入语句指令信息
     *
     * @return
     */
    @Override
    public SqlDeleteCmd build() {
        OqlExprObjectSource objectSource = this.stmt.getFrom();
        this.resolvedObject = objectSource.getResolvedObject();

        // 初始化 SQL 删除语句
        this.sqlStmt = new SqlDeleteStatement();
        this.sqlStmt.setFrom(this.buildSqlTableSource(objectSource));

        SqlExpr where = this.stmt.getWhere();
        if (where != null) {
            SqlWhereBuilder whereBuilder = new SqlWhereBuilder(this.resolvedObject);
            SqlExpr sqlWhere = whereBuilder.parseExpr(where);
            this.sqlStmt.setWhere(sqlWhere);
        }

        // 构建 SQL 删除指令
        SqlDeleteCmd deleteCmd = this.buildSqlDeleteCmd();
        return deleteCmd;
    }

    /**
     * 构建 SQL 删除指令
     *
     * @return
     */
    private SqlDeleteCmd buildSqlDeleteCmd() {
        SqlDeleteCmd deleteCmd = new SqlDeleteCmd();
        deleteCmd.setResolvedObject(this.resolvedObject);
        deleteCmd.setStatement(this.sqlStmt);
        deleteCmd.setBatch(this.isBatch);
        deleteCmd.setParamMap(this.paramMap);
        deleteCmd.setParamMaps(this.paramMaps);
        return deleteCmd;
    }

}
