package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;

public class SqlDeleteParser extends SqlExprParser {

    public SqlDeleteParser(Lexer lexer) {
        super(lexer);
    }

    public SqlDeleteParser(String Sql) {
        this(new Lexer(Sql));
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
