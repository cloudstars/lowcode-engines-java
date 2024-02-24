package net.cf.form.engine.oql.parser;

import net.cf.form.engine.oql.ast.OqlObjectSource;
import net.cf.form.engine.oql.ast.OqlSelect;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.SqlSelectParser;
import net.cf.form.repository.sql.parser.Token;

/**
 * OQL 查询语句解析器
 *
 * @author clouds
 */
public class OqlSelectParser extends SqlSelectParser {

    private final OqlExprParser exprParser;

    public OqlSelectParser(Lexer lexer) {
        super(lexer);
        this.exprParser = new OqlExprParser(lexer);
    }

    public OqlSelectParser(String oql) {
        this(new Lexer(oql));
    }

    /**
     * 解析select查询
     *
     * @return
     */
    public OqlSelect oqlSelect() {
        this.accept(Token.SELECT);

        OqlSelect select = new OqlSelect();
        select.setSelectItems((this.parseSelectItems()));
        select.setFrom(this.parseFrom());
        if (this.lexer.token() == Token.WHERE) {
            select.setWhere(this.parseWhere());
        }
        if (this.lexer.token() == Token.GROUP) {
            select.setGroupBy(this.parseGroupBy());
        }
        if (this.lexer.token() == Token.ORDER) {
            select.setOrderBy(this.parseOrderBy());
        }
        if (this.lexer.token() == Token.LIMIT) {
            select.setLimit(this.parseLimit());
        }

        return select;
    }

    /**
     * 解析 select 语句中 from
     *
     */
    private OqlObjectSource parseFrom() {
        accept(Token.FROM);
        return this.exprParser.parseExprObjectSource();
    }
}
