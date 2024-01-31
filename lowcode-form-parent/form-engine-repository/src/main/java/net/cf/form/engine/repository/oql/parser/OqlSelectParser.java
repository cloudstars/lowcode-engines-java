package net.cf.form.engine.repository.oql.parser;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlAllFieldExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.QqlNameExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.ast.statement.*;
import net.cf.form.engine.repository.oql.ast.statement.*;

import java.util.List;

/**
 * OQL 查询语句解析器
 *
 * @author clouds
 */
@Deprecated
public class OqlSelectParser extends OqlExprParser {

    public OqlSelectParser(Lexer lexer) {
        super(lexer);
    }

    public OqlSelectParser(String oql) {
        this(new Lexer(oql));
    }

    /**
     * 解析select查询
     *
     * @return
     */
    public OqlSelect select() {
        this.accept(Token.SELECT);

        OqlSelect select = new OqlSelect();
        this.parseSelectList(select);
        this.parseFrom(select);
        if (this.lexer.token == Token.WHERE) {
            this.parseWhere(select);
        }
        if (this.lexer.token == Token.GROUP) {
            this.parseGroupBy(select);
        }
        if (this.lexer.token == Token.ORDER) {
            this.parseOrderBy(select);
        }
        if (this.lexer.token == Token.LIMIT) {
            this.parseLimit(select);
        }

        return select;
    }


    /**
     * 解析 select 语句中的 select 字段列表
     *
     * @return
     */
    private void parseSelectList(OqlSelect query) {
        List<OqlSelectItem> selectList = query.getSelectItems();

        while (true) {
            QqlExpr selectItemExpr = this.parseSelectItemExpr(null);
            OqlSelectItem selectItem = new OqlSelectItem(selectItemExpr);

            if (this.lexer.token == Token.AS) {
                this.lexer.nextToken();
                if (this.lexer.token != Token.IDENTIFIER) {
                    this.printError(Token.IDENTIFIER);
                }
            }

            if (this.lexer.token == Token.IDENTIFIER) {
                String alias = this.lexer.stringVal();
                selectItem.setAlias(alias);
                this.lexer.nextToken();
            }

            selectList.add(selectItem);
            selectItem.setParent(query);

            if (this.lexer.token != Token.COMMA) {
                return;
            }

            this.lexer.nextToken();
        }
    }

    /**
     * 解析 select 语句中的单个字段
     *
     * @return
     */
    private QqlExpr parseSelectItemExpr(QqlExpr expr) {
        Token token = this.lexer.token;
        switch (token) {
            case STAR:
                if (expr == null) {
                    expr = new OqlAllFieldExpr();
                } else {
                    expr = new OqlPropertyExpr((QqlNameExpr) expr, Token.STAR.getName());
                }
                this.lexer.nextToken();
                break;
            case LITERAL_STRING:
            case LITERAL_INT:
            case LITERAL_FLOAT:
                expr = this.primary();
                break;
            case IDENTIFIER:
                String identifier = this.lexer.stringVal();
                this.lexer.nextToken();
                if (expr == null) {
                    expr = new OqlIdentifierExpr(identifier);
                    if (this.lexer.token == Token.LPAREN) {
                        expr = this.methodInvokeRest(expr);
                    }
                } else {
                    expr = new OqlPropertyExpr((QqlNameExpr) expr, identifier);
                }

                if (this.lexer.token == Token.DOT) {
                    this.lexer.nextToken();
                    expr = parseSelectItemExpr(expr);
                }

                break;
            default:
                printError(token);
        }

        return expr;
    }

    /**
     * 解析 select 语句中 from
     *
     * @param select
     */
    private void parseFrom(OqlSelect select) {
        accept(Token.FROM);
        select.setFrom(parseObjectSource());
    }

    /**
     * 解析 select 语句中 where
     *
     * @param select
     */
    private void parseWhere(OqlSelect select) {
        accept(Token.WHERE);
        select.setWhere(new OqlWhereClause(this.expr()));
    }

    /**
     * 解析 select 语句中 group by
     *
     * @param select
     */
    private void parseGroupBy(OqlSelect select) {
        this.accept(Token.GROUP);
        this.accept(Token.BY);

        OqlSelectGroupBy groupBy = new OqlSelectGroupBy();
        while (true) {
            QqlExpr expr = this.primary();
            groupBy.addItem(expr);

            if (this.lexer.token != Token.COMMA) {
                break;
            }

            this.lexer.nextToken();
        }

        if (this.lexer.token == Token.HAVING) {
            this.accept(Token.HAVING);

            QqlExpr expr = this.expr();
            groupBy.setHaving(expr);
        }

        select.setGroupBy(groupBy);
    }

    /**
     * 解析 select 语句中 order by
     *
     * @param select
     */
    private void parseOrderBy(OqlSelect select) {
        this.accept(Token.ORDER);
        this.accept(Token.BY);

        OqlSelectOrderBy orderBy = new OqlSelectOrderBy();
        while (true) {
            QqlExpr expr = this.primary();

            if (this.lexer.token == Token.ASC) {
                orderBy.addItem(expr, true);
                this.lexer.nextToken();
            } else if (this.lexer.token == Token.DESC) {
                orderBy.addItem(expr, false);
                this.lexer.nextToken();
            } else {
                orderBy.addItem(expr);
            }

            if (this.lexer.token != Token.COMMA) {
                break;
            }

            this.lexer.nextToken();
        }

        select.setOrderBy(orderBy);
    }

    /**
     * 解析 select 语句中 limit
     *
     * @param select
     */
    private void parseLimit(OqlSelect select) {
        this.accept(Token.LIMIT);

        OqlSelectLimit limit = new OqlSelectLimit();
        QqlExpr expr1 = this.primary();
        if (!(expr1 instanceof OqlIntegerExpr)) {
            this.printError(Token.LITERAL_INT);
        }

        if (this.lexer.token == Token.COMMA) {
            this.lexer.nextToken();

            QqlExpr expr2 = this.primary();
            if (!(expr2 instanceof OqlIntegerExpr)) {
                this.printError(Token.LITERAL_INT);
            }
            limit.setOffset(((OqlIntegerExpr) expr1).getValue());
            limit.setRowCount(((OqlIntegerExpr) expr2).getValue());
        } else {
            limit.setRowCount(((OqlIntegerExpr) expr1).getValue());
        }

        select.setLimit(limit);
    }
}
