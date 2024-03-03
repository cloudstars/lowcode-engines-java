package net.cf.object.engine.oql.parser;

import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.Token;

public class OqlDeleteStatementParser extends OqlExprParser {

    public OqlDeleteStatementParser(Lexer lexer) {
        super(lexer);
    }

    public OqlDeleteStatementParser(String oql) {
        this(new Lexer(oql));
    }

    public OqlDeleteStatement statement() {
        this.accept(Token.DELETE);
        this.accept(Token.FROM);

        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setFrom(this.parseObjectSource());
        if (this.lexer.token() == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhere(this.expr());
        }

        return statement;
    }
}
