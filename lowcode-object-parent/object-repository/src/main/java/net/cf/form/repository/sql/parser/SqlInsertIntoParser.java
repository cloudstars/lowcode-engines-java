package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.ast.statement.SqlInsertInto;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelect;

public class SqlInsertIntoParser extends SqlExprParser {

    public SqlInsertIntoParser(Lexer lexer) {
        super(lexer);
    }

    public SqlInsertIntoParser(String sql) {
        this(new Lexer(sql));
    }

    public SqlInsertInto insertInto() {
        this.accept(Token.INSERT);
        this.accept(Token.INTO);

        SqlInsertInto insert = new SqlInsertInto();
        insert.setTableSource(this.parseExprTableSource());
        this.parseIntoFields(insert);
        if (this.lexer.token == Token.VALUES) {
            this.parserValuesList(insert);
        } else if (this.lexer.token == Token.SELECT) {
            SqlSelectParser selectParser = new SqlSelectParser(this.lexer);
            SqlSelect query = selectParser.select();
            insert.setQuery(query);
        }

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
