package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
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
        boolean isParenthesized = false;
        if (this.lexer.token == Token.LPAREN) {
            isParenthesized = true;
            this.lexer.nextToken();
        }

        this.accept(Token.SELECT);

        SqlSelect select = new SqlSelect();
        select.addSelectItems(this.parseSelectItems());
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

        if (isParenthesized) {
            this.accept(Token.RPAREN);
            select.setParenthesized(true);
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
            SqlExpr selectItemExpr = this.parseSelectItemExpr();
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
    private SqlExpr parseSelectItemExpr() {
        SqlExpr tExpr = null;
        Token token = this.lexer.token;
        switch (token) {
            case STAR:
                tExpr = new SqlAllColumnExpr();
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
                tExpr = new SqlIdentifierExpr(identifier);
                tExpr = this.parseIdentifierRest((SqlIdentifierExpr) tExpr);
                break;
            default:
                try {
                    tExpr = this.primary();
                } catch (Exception e) {
                    printError(token);
                }
        }

        return tExpr;
    }

    /**
     * 解析标识符的剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr parseIdentifierRest(SqlIdentifierExpr expr) {
        SqlExpr tExpr = expr;
        if (this.lexer.token == Token.LPAREN) {
            tExpr = this.methodInvokeRest(tExpr);
        } else {
            int level = 0;
            while (this.lexer.token == Token.DOT) {
                level++;
                if (level > 2) {
                    throw new SqlParseException("不支持3级以上的属性表达式，如：a.b.c.d");
                }

                this.lexer.nextToken();
                if (this.lexer.token == Token.STAR) { // x.*
                    tExpr = new SqlPropertyExpr((SqlIdentifierExpr) tExpr, Token.STAR.name);
                } else {
                    String propName = this.lexer.stringVal();
                    if (tExpr instanceof SqlIdentifierExpr) {
                        tExpr = new SqlPropertyExpr((SqlIdentifierExpr) tExpr, propName);
                    } else {
                        SqlPropertyExpr propExpr = (SqlPropertyExpr) tExpr;
                        // a.b.c的情况下，owner不变，属性名带点
                        String newPropName = propExpr.getName() + Token.STAR.name + propName;
                        propExpr.setName(newPropName);
                    }
                }
                this.lexer.nextToken();
            }
        }
        return tExpr;
    }

    /**
     * 解析 select 语句中 from
     */
    private SqlTableSource parseFrom() {
        accept(Token.FROM);
        return this.parseTableSource();
    }

    /**
     * 解析 select 语句中 where
     */
    protected SqlExpr parseWhere() {
        accept(Token.WHERE);
        return this.expr();
    }

    /**
     * 解析 select 语句中 group by
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
     */
    protected SqlLimit parseLimit() {
        this.accept(Token.LIMIT);

        SqlLimit limit = new SqlLimit();
        SqlExpr offsetExpr = this.primary();
        if (!(offsetExpr instanceof SqlIntegerExpr)) {
            this.printError(Token.LITERAL_INT);
        }

        if (this.lexer.token == Token.COMMA) {
            limit.setOffset(offsetExpr);
            this.lexer.nextToken();

            SqlExpr rowCountExpr = this.primary();
            if (!(rowCountExpr instanceof SqlIntegerExpr)) {
                this.printError(Token.LITERAL_INT);
            }
            limit.setRowCount(rowCountExpr);
        } else {
            limit.setRowCount(offsetExpr);
        }

        return limit;
    }
}
