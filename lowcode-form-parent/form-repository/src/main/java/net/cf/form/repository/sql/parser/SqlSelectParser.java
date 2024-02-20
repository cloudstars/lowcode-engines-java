package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.statement.SqlOrderBy;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectGroupByClause;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;

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
    private void parseSelectList(SqlSelect query) {
        List<SqlSelectItem> selectList = query.getSelectItems();

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
    private SqlExpr parseSelectItemExpr(SqlExpr expr) {
        Token token = this.lexer.token;
        switch (token) {
            case STAR:
                if (expr == null) {
                    expr = new SqlAllColumnExpr();
                } else {
                    expr = new SqlPropertyExpr((SqlName) expr, Token.STAR.getName());
                }
                this.lexer.nextToken();
                break;
            case LITERAL_STRING:
            case LITERAL_INT:
            case LITERAL_NUMBER:
                expr = this.primary();
                break;
            case IDENTIFIER:
                String identifier = this.lexer.stringVal();
                this.lexer.nextToken();
                if (expr == null) {
                    expr = new SqlIdentifierExpr(identifier);
                    if (this.lexer.token == Token.LPAREN) {
                        expr = this.methodInvokeRest(expr);
                    }
                } else {
                    expr = new SqlPropertyExpr((SqlName) expr, identifier);
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
    private void parseFrom(SqlSelect select) {
        accept(Token.FROM);
        select.setFrom(parseTableSource());
    }

    /**
     * 解析 select 语句中 where
     *
     * @param select
     */
    private void parseWhere(SqlSelect select) {
        accept(Token.WHERE);
        select.setWhere(this.expr());
    }

    /**
     * 解析 select 语句中 group by
     *
     * @param select
     */
    private void parseGroupBy(SqlSelect select) {
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

        select.setGroupBy(groupBy);
    }

    /**
     * 解析 select 语句中 order by
     *
     * @param select
     */
    private void parseOrderBy(SqlSelect select) {
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

        select.setOrderBy(orderBy);
    }

    /**
     * 解析 select 语句中 limit
     *
     * @param select
     */
    private void parseLimit(SqlSelect select) {
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

        select.setLimit(limit);
    }
}
