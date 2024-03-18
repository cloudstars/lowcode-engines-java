package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;

public class OqlDeleteStatementParser extends OqlExprParser {

    private final XObject object;

    public OqlDeleteStatementParser(XObject object, Lexer lexer) {
        super(lexer);
        this.object = object;
    }

    public OqlDeleteStatementParser(XObject object, String oql) {
        this(object, new Lexer(oql));
    }

    public OqlDeleteStatement statement() {
        this.accept(Token.DELETE);
        this.accept(Token.FROM);

        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setFrom(this.parseExprObjectSource());
        if (this.lexer.token() == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhere(this.expr());
        }

        return statement;
    }
}
