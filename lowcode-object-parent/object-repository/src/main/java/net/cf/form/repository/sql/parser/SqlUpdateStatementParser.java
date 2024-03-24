package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.FastSqlException;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;

public class SqlUpdateStatementParser extends SqlExprParser {

    public SqlUpdateStatementParser(Lexer lexer) {
        super(lexer);
    }

    public SqlUpdateStatementParser(String sql) {
        this(new Lexer(sql));
    }

    public SqlUpdateStatement statement() {
        this.accept(Token.UPDATE);

        SqlUpdateStatement statement = new SqlUpdateStatement();
        SqlTableSource tableSource = this.parseTableSource();
        if (!(tableSource instanceof SqlExprTableSource)) {
            throw new FastSqlException("Update SQL语句只允许操作标识符表名");
        }
        statement.setTableSource((SqlExprTableSource) tableSource);
        this.parseSetItems(statement);
        if (this.lexer.token == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhere(this.expr());
        }

        return statement;
    }

    /**
     * 解析set字段
     *
     * @param statement
     */
    private void parseSetItems(SqlUpdateStatement statement) {
        this.accept(Token.SET);
        this.parseCommaSeperatedList(() -> {
            SqlUpdateSetItem setItem = new SqlUpdateSetItem();
            setItem.setColumn(this.primary());
            this.accept(Token.EQ);
            setItem.setValue(this.primary());
            statement.addSetItem(setItem);
        });
    }
}
