package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.FastSqlException;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.statement.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Sql语句解析器
 *
 * @author clouds
 */
public class SqlStatementParser extends SqlExprParser {

    public SqlStatementParser(String sql) {
        super(sql);
    }

    public List<SqlStatement> parseStatementList() {
        List<SqlStatement> statementList = new ArrayList();
        this.parseStatementList(statementList, -1, null);
        return statementList;
    }

    public List<SqlStatement> parseStatementList(SqlObject parent) {
        List<SqlStatement> statementList = new ArrayList();
        this.parseStatementList(statementList, -1, parent);
        return statementList;
    }

    public void parseStatementList(List<SqlStatement> statementList) {
        this.parseStatementList(statementList, -1, null);
    }

    public void parseStatementList(List<SqlStatement> statementList, int max) {
        this.parseStatementList(statementList, max, null);
    }

    public void parseStatementList(List<SqlStatement> statementList, int max, SqlObject parent) {
        while (max == -1 || statementList.size() < max) {
            Token token = this.lexer.token;
            while (token == Token.MULTI_LINE_COMMENT || token == Token.LINE_COMMENT) {
                this.lexer.nextToken();
            }

            SqlStatement stmt = null;
            if (token == Token.EOF) {
                break;
            }

            if (token == Token.SEMI) {
                this.lexer.nextToken();
                if (statementList.size() > 0) {
                    SqlStatement lastStmt = statementList.get(statementList.size() - 1);
                    lastStmt.setAfterSemi(true);
                }

                continue;
            } else if (token == Token.SELECT) {
                stmt = this.parseSelectStatement();
            } else if (token == Token.UPDATE) {
                stmt = this.parseUpdateStatement();
            } else if (token == Token.INSERT) {
                stmt = this.parseInsertStatement();
            } else if (token == Token.DELETE) {
                stmt = this.parseDeleteStatement();
            }

            if (stmt != null) {
                stmt.setParent(parent);
                statementList.add(stmt);
            } else {
                throw new FastSqlException("Not a valid SQL Statement, SQL: " + this.lexer.text);
            }
        }
    }


    /**
     * 解析 Select 语句
     *
     * @return
     */
    private SqlSelectStatement parseSelectStatement() {
        SqlSelectParser selectParser = new SqlSelectParser(this.lexer);
        SqlSelect select = selectParser.select(false);
        return new SqlSelectStatement(select);
    }

    /**
     * 解析 Insert 语句
     *
     * @return
     */
    private SqlInsertStatement parseInsertStatement() {
        SqlInsertIntoParser insertParser = new SqlInsertIntoParser(this.lexer);
        SqlInsertInto insertInto = insertParser.insertInto();
        return new SqlInsertStatement(insertInto);
    }

    /**
     * 解析 Update 语句
     *
     * @return
     */
    private SqlUpdateStatement parseUpdateStatement() {
        SqlUpdateStatementParser parser = new SqlUpdateStatementParser(this.lexer);
        return parser.statement();
    }

    /**
     * 解析 Delete 语句
     *
     * @return
     */
    private SqlDeleteStatement parseDeleteStatement() {
        SqlDeleteStatementParser parser = new SqlDeleteStatementParser(this.lexer);
        return parser.statement();
    }
}
