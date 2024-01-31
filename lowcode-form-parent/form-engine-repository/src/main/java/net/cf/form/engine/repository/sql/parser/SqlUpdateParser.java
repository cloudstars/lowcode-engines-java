package net.cf.form.engine.repository.sql.parser;

import net.cf.form.engine.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.form.engine.repository.sql.ast.statement.SqlUpdateStatement;

public class SqlUpdateParser extends SqlExprParser {

    public SqlUpdateParser(Lexer lexer) {
        super(lexer);
    }

    public SqlUpdateParser(String Sql) {
        this(new Lexer(Sql));
    }

    public SqlUpdateStatement statement() {
        this.accept(Token.UPDATE);

        SqlUpdateStatement statement = new SqlUpdateStatement();
        statement.setTableSource(this.parseTableSource());
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
            setItem.setField(this.primary());
            this.accept(Token.EQ);
            setItem.setValue(this.primary());
            statement.addSetItem(setItem);
        });
    }
}
