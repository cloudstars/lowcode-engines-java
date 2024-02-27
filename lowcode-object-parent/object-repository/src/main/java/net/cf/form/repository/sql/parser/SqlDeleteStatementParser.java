package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;

public class SqlDeleteStatementParser extends SqlExprParser {

    public SqlDeleteStatementParser(Lexer lexer) {
        super(lexer);
    }

    public SqlDeleteStatementParser(String sql) {
        this(new Lexer(sql));
    }

    public SqlDeleteStatement statement() {
        this.accept(Token.DELETE);
        this.accept(Token.FROM);

        SqlDeleteStatement statement = new SqlDeleteStatement();
        statement.setTableSource(this.parseTableSource());
        if (this.lexer.token == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhere(this.expr());
        }

        return statement;
    }
}
