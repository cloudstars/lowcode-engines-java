package net.cf.form.engine.oql.parser;

import net.cf.form.engine.oql.ast.statement.OqlUpdateSetItem;
import net.cf.form.engine.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.oql.ast.statement.OqlWhereClause;

public class OqlUpdateParser extends OqlExprParser {

    public OqlUpdateParser(Lexer lexer) {
        super(lexer);
    }

    public OqlUpdateParser(String oql) {
        this(new Lexer(oql));
    }

    public OqlUpdateStatement statement() {
        this.accept(Token.UPDATE);

        OqlUpdateStatement statement = new OqlUpdateStatement();
        statement.setObjectSource(this.parseObjectSource());
        this.parseSetItems(statement);
        if (this.lexer.token == Token.WHERE) {
            this.lexer.nextToken();
            statement.setWhereClause(new OqlWhereClause(this.expr()));
        }

        return statement;
    }

    /**
     * 解析set字段
     *
     * @param statement
     */
    private void parseSetItems(OqlUpdateStatement statement) {
        this.accept(Token.SET);
        this.parseCommaSeperatedList(() -> {
            OqlUpdateSetItem setItem = new OqlUpdateSetItem();
            setItem.setField(this.primary());
            this.accept(Token.EQ);
            setItem.setValue(this.primary());
            statement.addSetItem(setItem);
        });
    }
}
