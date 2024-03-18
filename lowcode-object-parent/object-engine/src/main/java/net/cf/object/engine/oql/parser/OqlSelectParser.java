package net.cf.object.engine.oql.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.SqlSelectParser;
import net.cf.form.repository.sql.parser.Token;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelect;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL 查询语句解析器
 *
 * @author clouds
 */
public class OqlSelectParser extends SqlSelectParser {

    private final XObject object;

    private final OqlExprParser exprParser;

    public OqlSelectParser(XObject object, String oql) {
        this(object, new Lexer(oql));
    }

    public OqlSelectParser(XObject object, Lexer lexer) {
        super(lexer);
        this.object = object;
        this.exprParser = new OqlExprParser(lexer);
    }

    /**
     * 解析select查询
     *
     * @return
     */
    public OqlSelect oqlSelect() {
        this.accept(Token.SELECT);

        OqlSelect select = new OqlSelect();
        List<SqlSelectItem> selectItems = this.parseSelectItems();
        List<SqlSelectItem> selectItemsX = new ArrayList<>();
        for (SqlSelectItem selectItem : selectItems) {
            SqlSelectItem selectItemX = selectItem;
            SqlExpr expr = selectItem.getExpr();
            SqlExpr realExpr = this.exprParser.getRealExprByObject(expr, object);
            if (expr != realExpr) {
                selectItemX = selectItem.cloneMe();
                selectItemX.setExpr(realExpr);
            }
            selectItemsX.add(selectItemX);
        }
        select.setSelectItems(selectItemsX);

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
