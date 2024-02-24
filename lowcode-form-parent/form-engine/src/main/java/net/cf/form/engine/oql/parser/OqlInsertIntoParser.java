package net.cf.form.engine.oql.parser;

import net.cf.form.engine.oql.ast.OqlInsertInto;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.Token;

public class OqlInsertIntoParser extends OqlExprParser {

    public OqlInsertIntoParser(Lexer lexer) {
        super(lexer);
    }

    public OqlInsertIntoParser(String oql) {
        this(new Lexer(oql));
    }

    public OqlInsertInto insertInto() {
        this.accept(Token.INSERT);
        this.accept(Token.INTO);

        OqlInsertInto insert = new OqlInsertInto();
        insert.setObjectSource(this.parseExprObjectSource());
        this.parseIntoFields(insert);
        this.parserValuesList(insert);

        return insert;
    }

    /**
     * 解析插入的字段列表
     *
     * @param insert
     */
    private void parseIntoFields(OqlInsertInto insert) {
        this.accept(Token.LPAREN);
        this.parseCommaSeperatedList(() -> {
            insert.addField(this.primary());
        });
        this.accept(Token.RPAREN);
    }

    /**
     * 解析插入的值列表（支持多条）
     *
     * @param insert
     */
    private void parserValuesList(OqlInsertInto insert) {
        this.accept(Token.VALUES);
        this.parseCommaSeperatedList(() -> {
            insert.addValues(parseInsertValues());
        });
    }

    /**
     * 解析插入的单条值
     *
     * @return
     */
    private SqlInsertStatement.ValuesClause parseInsertValues() {
        this.accept(Token.LPAREN);
        SqlInsertStatement.ValuesClause insertValues = new SqlInsertStatement.ValuesClause();
        this.parseCommaSeperatedList(() -> {
            insertValues.addValue(this.primary());
        });
        this.accept(Token.RPAREN);
        return insertValues;
    }

}
