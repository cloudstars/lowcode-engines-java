package net.cf.form.engine.repository.sql.parser;

import net.cf.form.engine.repository.sql.ast.statement.SqlInsertInto;
import net.cf.form.engine.repository.sql.ast.statement.SqlInsertStatement;

public class SqlInsertParser extends SqlExprParser {

    public SqlInsertParser(Lexer lexer) {
        super(lexer);
    }

    public SqlInsertParser(String Sql) {
        this(new Lexer(Sql));
    }

    public SqlInsertInto insertInto() {
        this.accept(Token.INSERT);
        this.accept(Token.INTO);

        SqlInsertInto insert = new SqlInsertInto();
        insert.setTableSource(this.parseExprTableSource());
        this.parseIntoFields(insert);
        this.parserValuesList(insert);

        return insert;
    }

    /**
     * 解析插入的字段列表
     *
     * @param insert
     */
    private void parseIntoFields(SqlInsertInto insert) {
        this.accept(Token.LPAREN);
        this.parseCommaSeperatedList(() -> {
            insert.addColumn(this.primary());
        });
        this.accept(Token.RPAREN);
    }

    /**
     * 解析插入的值列表（支持多条）
     *
     * @param insert
     */
    private void parserValuesList(SqlInsertInto insert) {
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
