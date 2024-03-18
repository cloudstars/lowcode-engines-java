package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertInto;

import java.util.ArrayList;
import java.util.List;

public class OqlInsertIntoParser extends OqlExprParser {

    private final XObject object;

    public OqlInsertIntoParser(XObject object, Lexer lexer) {
        super(lexer);
        this.object = object;
    }

    public OqlInsertIntoParser(XObject object, String oql) {
        this(object, new Lexer(oql));
    }

    public OqlInsertInto insertInto() {
        this.accept(Token.INSERT);
        this.accept(Token.INTO);

        OqlInsertInto insert = new OqlInsertInto();
        insert.setObjectSource(this.parseExprObjectSource());
        this.parseIntoFields(insert);
        this.parserValuesList(insert);

        List<SqlExpr> fields = insert.getFields();
        List<SqlExpr> fieldsX = new ArrayList<>();
        for (SqlExpr field : fields) {
            SqlExpr fieldX = field;
            SqlExpr realExpr = this.getRealExprByObject(field, object);
            if (realExpr != field) {
                fieldX = realExpr;
            }
            fieldsX.add(fieldX);
        }
        insert.getFields().clear();
        insert.getFields().addAll(fieldsX);

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
            SqlExpr expr = this.primary();
            insert.addField(expr);
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
