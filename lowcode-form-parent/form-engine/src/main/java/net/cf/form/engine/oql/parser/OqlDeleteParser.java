package net.cf.form.engine.oql.parser;

import net.cf.form.engine.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.Token;

public class OqlDeleteParser extends OqlExprParser {

    public OqlDeleteParser(Lexer lexer) {
        super(lexer);
    }

    public OqlDeleteParser(String oql) {
        this(new Lexer(oql));
    }

    public OqlDeleteStatement statement() {
        this.accept(Token.DELETE);
        this.accept(Token.FROM);

        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setObjectSource(this.parseObjectSource());
        if (this.lexer.token() == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhere(this.expr());
        }

        return statement;
    }
}
