package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.statement.*;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL 查询语句解析器
 *
 * @author clouds
 */
public class SqlSelectParser extends SqlExprParser {

    public SqlSelectParser(Lexer lexer) {
        super(lexer);
    }

    public SqlSelectParser(String sql) {
        this(new Lexer(sql));
    }

    /**
     * 解析select查询
     *
     * @return
     */
    public SqlSelect select() {
        this.accept(Token.SELECT);

        SqlSelect select = new SqlSelect();
        select.setSelectItems(this.parseSelectItems());
        select.setFrom(this.parseFrom());
        if (this.lexer.token == Token.WHERE) {
            select.setWhere((this.parseWhere()));
        }
        if (this.lexer.token == Token.GROUP) {
            select.setGroupBy(this.parseGroupBy());
        }
        if (this.lexer.token == Token.ORDER) {
            select.setOrderBy((this.parseOrderBy()));
        }
        if (this.lexer.token == Token.LIMIT) {
            select.setLimit(this.parseLimit());
        }

        return select;
    }


    /**
     * 解析 select 语句中的 select 字段列表
     *
     * @return
     */
    protected List<SqlSelectItem> parseSelectItems() {
        List<SqlSelectItem> selectList = new ArrayList<>();
        while (true) {
            SqlExpr selectItemExpr = this.parseSelectItemExpr(null);
            SqlSelectItem selectItem = new SqlSelectItem(selectItemExpr);

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

            if (this.lexer.token != Token.COMMA) {
                break;
            }

            this.lexer.nextToken();
        }

        return selectList;
    }

    /**
     * 解析 select 语句中的单个字段
     *
     * @return
     */
    private SqlExpr parseSelectItemExpr(final SqlExpr expr) {
        SqlExpr tExpr = expr;
        Token token = this.lexer.token;
        switch (token) {
            case STAR:
                if (tExpr == null) {
                    tExpr = new SqlAllColumnExpr();
                } else {
                    tExpr = new SqlPropertyExpr((SqlName) tExpr, Token.STAR.getName());
                }
                this.lexer.nextToken();
                break;
            case LITERAL_STRING:
            case LITERAL_INT:
            case LITERAL_NUMBER:
                tExpr = this.primary();
                break;
            case IDENTIFIER:
                String identifier = this.lexer.stringVal();
                this.lexer.nextToken();
                if (tExpr == null) {
                    tExpr = new SqlIdentifierExpr(identifier);
                    if (this.lexer.token == Token.LPAREN) {
                        tExpr = this.methodInvokeRest(tExpr);
                    }
                } else {
                    tExpr = new SqlPropertyExpr((SqlName) tExpr, identifier);
                }

                if (this.lexer.token == Token.DOT) {
                    this.lexer.nextToken();
                    tExpr = parseSelectItemExpr(tExpr);
                }

                break;
            default:
                printError(token);
        }

        return tExpr;
    }

    /**
     * 解析 select 语句中 from
     *
     */
    private SqlTableSource parseFrom() {
        accept(Token.FROM);
        return parseTableSource();
    }

    /**
     * 解析 select 语句中 where
     *
     */
    protected SqlExpr parseWhere() {
        accept(Token.WHERE);
        return this.expr();
    }

    /**
     * 解析 select 语句中 group by
     *
     */
    protected SqlSelectGroupByClause parseGroupBy() {
        this.accept(Token.GROUP);
        this.accept(Token.BY);

        SqlSelectGroupByClause groupBy = new SqlSelectGroupByClause();
        while (true) {
            SqlExpr expr = this.primary();
            groupBy.addItem(expr);

            if (this.lexer.token != Token.COMMA) {
                break;
            }

            this.lexer.nextToken();
        }

        if (this.lexer.token == Token.HAVING) {
            this.accept(Token.HAVING);

            SqlExpr expr = this.expr();
            groupBy.setHaving(expr);
        }

        return groupBy;
    }

    /**
     * 解析 select 语句中 order by
     *
     */
    protected SqlOrderBy parseOrderBy() {
        this.accept(Token.ORDER);
        this.accept(Token.BY);

        SqlOrderBy orderBy = new SqlOrderBy();
        while (true) {
            SqlExpr expr = this.primary();

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

        return orderBy;
    }

    /**
     * 解析 select 语句中 limit
     *
     */
    protected SqlLimit parseLimit() {
        this.accept(Token.LIMIT);

        SqlLimit limit = new SqlLimit();
        SqlExpr offsetExpr = this.primary();
        if (!(offsetExpr instanceof SqlIntegerExpr)) {
            this.printError(Token.LITERAL_INT);
        }
        limit.setRowCount(((SqlIntegerExpr) offsetExpr).getValue());

        if (this.lexer.token == Token.COMMA) {
            this.lexer.nextToken();

            SqlExpr rowCountExpr = this.primary();
            if (!(rowCountExpr instanceof SqlIntegerExpr)) {
                this.printError(Token.LITERAL_INT);
            }
            limit.setRowCount(((SqlIntegerExpr) rowCountExpr).getValue());
        }

        return limit;
    }
}
