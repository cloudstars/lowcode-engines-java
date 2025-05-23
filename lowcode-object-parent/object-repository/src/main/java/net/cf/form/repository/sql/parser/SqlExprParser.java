package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.op.*;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.util.SqlExprUtils;

import java.util.Arrays;
import java.util.List;

/**
 * SQL 表达式解析器
 *
 * @author clouds
 */
public class SqlExprParser extends AbstractSqlParser {

    /**
     * 聚合函数的名称
     */
    public static final List<String> AGGREGATE_FUNCTIONS = Arrays.asList("COUNT", "SUM", "AVG", "MAX", "MIN");

    /**
     * 是否在解析一个数组
     */
    private boolean isParsingArray = false;

    public SqlExprParser(String sql) {
        super(sql);
    }

    public SqlExprParser(String sql, SqlParserFeature[] features) {
        super(sql);
    }


    public SqlExprParser(Lexer lexer) {
        super(lexer);
    }

    /**
     * 解析表达式
     *
     * @return
     */
    public final SqlExpr expr() {
        SqlExpr expr = primary();
        return exprRest(expr);
    }

    /**
     * 解析表达式的剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr exprRest(final SqlExpr expr) {
        SqlExpr tExpr = expr;
        tExpr = this.multiplicativeRest(tExpr);
        tExpr = this.additiveRest(tExpr);
        tExpr = this.relationalRest(tExpr);
        tExpr = this.andRest(tExpr);
        tExpr = this.orRest(tExpr);
        return tExpr;
    }

    /**
     * 解析逻辑或
     *
     * @return
     */
    public final SqlExpr or() {
        SqlExpr expr = this.and();
        Token token = this.lexer.token;
        if (token == Token.OR || token == Token.BARBAR) {
            expr = this.orRest(expr);
        }

        return expr;
    }

    /**
     * 解析逻辑或的剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr orRest(final SqlExpr expr) {
        SqlExpr tExpr = expr;
        while (true) {
            Token token = this.lexer.token;
            if (token != Token.OR && token != Token.BARBAR) {
                break;
            }

            this.lexer.nextToken();
            SqlExpr rightExp = this.and();
            tExpr = SqlExprUtils.or(tExpr, rightExp);
        }

        return tExpr;
    }


    /**
     * 解析逻辑与
     *
     * @return
     */
    public final SqlExpr and() {
        SqlExpr expr = this.relational();
        Token token = this.lexer.token;
        if (token == Token.AND || token == Token.AMPAMP) {
            expr = this.andRest(expr);
        }

        return expr;
    }

    /**
     * 解析逻辑与的剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr andRest(final SqlExpr expr) {
        SqlExpr tExpr = expr;
        while (true) {
            Token token = this.lexer.token;
            if (token != Token.AND && token != Token.AMPAMP) {
                break;
            }

            this.lexer.nextToken();
            SqlExpr rightExp = this.relational();
            tExpr = SqlExprUtils.and(tExpr, rightExp);
        }

        return tExpr;
    }

    /**
     * 解析乘除法
     *
     * @return
     */
    public final SqlExpr multiplicative() {
        SqlExpr expr = this.primary();
        return this.multiplicativeRest(expr);
    }


    /**
     * 解析乘除法剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr multiplicativeRest(final SqlExpr expr) {
        SqlExpr targetExpr = expr;
        SqlExpr rightExpr;
        Token token = this.lexer.token;
        if (token == Token.STAR) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.MULTIPLY, rightExpr);
            targetExpr = this.multiplicativeRest(targetExpr);
        } else if (token == Token.SLASH) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.DIVIDE, rightExpr);
            targetExpr = this.multiplicativeRest(targetExpr);
        } else if (token == Token.PERCENT) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.MODULUS, rightExpr);
            targetExpr = this.multiplicativeRest(targetExpr);
        }

        return targetExpr;
    }


    /**
     * 加减法
     *
     * @return
     */
    public final SqlExpr additive() {
        SqlExpr expr = this.multiplicative();
        if (this.lexer.token == Token.PLUS || this.lexer.token == Token.SUB) {
            expr = this.additiveRest(expr);
        }

        return expr;
    }

    /**
     * 解析加减法的剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr additiveRest(final SqlExpr expr) {
        SqlExpr targetExpr = expr;
        SqlExpr rightExpr;
        Token token = this.lexer.token;
        if (token == Token.PLUS) {
            this.lexer.nextToken();
            rightExpr = this.multiplicative();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.ADD, rightExpr);
            targetExpr = this.additiveRest(targetExpr);
        } else if (token == Token.SUB) {
            this.lexer.nextToken();
            rightExpr = this.multiplicative();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.SUBTRACT, rightExpr);
            targetExpr = this.additiveRest(targetExpr);
        }

        return targetExpr;
    }


    /**
     * 解析关系表达式
     *
     * @return
     */
    public final SqlExpr relational() {
        SqlExpr expr = this.additive();
        return this.relationalRest(expr);
    }

    /**
     * 解析关系表达式的剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr relationalRest(final SqlExpr expr) {
        SqlExpr targetExpr = expr;
        SqlExpr rightExp;
        boolean isNot = false;
        if (this.lexer.token == Token.NOT) {
            isNot = true;
            this.lexer.nextToken();
        }

        Token token = this.lexer.token;
        switch (token) {
            case EQ:
            case EQEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.EQUALITY, rightExp);
                break;
            case LTGT:
            case BANGEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.NOT_EQUAL, rightExp);
                break;
            case LT:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.LESS_THAN, rightExp);
                break;
            case LTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.LESS_THAN_OR_EQUAL, rightExp);
                break;
            case GT:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.GREATER_THAN, rightExp);
                break;
            case GTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.GREATER_THAN_OR_EQUAL, rightExp);
                break;
            case LIKE:
                this.lexer.nextToken();
                rightExp = this.expr();
                SqlLikeOpExpr likeOpExpr = new SqlLikeOpExpr(targetExpr, rightExp);
                likeOpExpr.setNot(isNot);
                if (this.lexer.token == Token.ESCAPE) {
                    this.lexer.nextToken();
                    likeOpExpr.setEscape(this.lexer.stringVal());
                    this.lexer.nextToken();
                }
                targetExpr = likeOpExpr;

                break;
            case IN:
                this.lexer.nextToken();
                this.accept(Token.LPAREN);
                SqlInListExpr inListExpr = new SqlInListExpr(targetExpr);
                inListExpr.setNot(isNot);
                targetExpr = inListExpr;
                this.exprList(((SqlInListExpr) targetExpr).getTargetList(), targetExpr);
                this.accept(Token.RPAREN);

                break;
            case IS:
                this.lexer.nextToken();
                SqlBinaryOperator binaryOp;
                if (this.lexer.token == Token.NOT) {
                    binaryOp = SqlBinaryOperator.IS_NOT;
                    this.lexer.nextToken();
                } else {
                    binaryOp = SqlBinaryOperator.IS;
                }
                this.accept(Token.NULL);
                targetExpr = new SqlBinaryOpExpr(targetExpr, binaryOp, new SqlNullExpr());

                break;
            case CONTAINS:
                this.lexer.nextToken();
                Token nextToken = this.lexer.token;
                if (nextToken == Token.ALL || nextToken == Token.ANY) {
                    SqlBinaryOperator bop = this.lexer.token == Token.ANY ? SqlBinaryOperator.CONTAINS_ANY : SqlBinaryOperator.CONTAINS_ALL;
                    SqlListExpr array = new SqlListExpr();
                    SqlArrayContainsOpExpr arrayContainsOpExpr = new SqlArrayContainsOpExpr(targetExpr, bop, array);
                    arrayContainsOpExpr.setNot(isNot);
                    this.lexer.nextToken();
                    this.accept(Token.LPAREN);
                    this.exprList(array.getItems(), targetExpr);
                    this.accept(Token.RPAREN);

                    targetExpr = arrayContainsOpExpr;
                } else {
                    rightExp = this.additive();
                    SqlContainsOpExpr containsOpExpr = new SqlContainsOpExpr(targetExpr, rightExp);
                    containsOpExpr.setNot(isNot);
                    targetExpr = containsOpExpr;
                }
                break;
            default:
                break;
        }

        return targetExpr;
    }


    /**
     * 解析一个原子部分
     *
     * @return
     */
    public final SqlExpr primary() {
        boolean isNot = false;
        if (this.lexer.token == Token.NOT) {
            isNot = true;
            this.lexer.nextToken();
        }

        SqlExpr expr = null;
        Token token = this.lexer.token;
        switch (token) {
            case STAR:
                expr = new SqlAllColumnExpr();
                this.lexer.nextToken();
                break;
            case LITERAL_STRING:
                expr = new SqlCharExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case LITERAL_INT:
                expr = new SqlIntegerExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case LITERAL_NUMBER:
                String numberValue = this.lexer.stringVal();
                expr = new SqlDecimalExpr(numberValue.toCharArray());
                this.lexer.nextToken();
                break;
            case TRUE:
                this.lexer.nextToken();
                expr = new SqlBooleanExpr(true);
                break;
            case FALSE:
                this.lexer.nextToken();
                expr = new SqlBooleanExpr(false);
                break;
            case NULL:
                expr = new SqlNullExpr();
                this.lexer.nextToken();
                break;
            case IDENTIFIER:
                expr = new SqlIdentifierExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case VARIANT_REF:
                expr = new SqlVariantRefExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case BANG:
            case LBRACKET:
                this.lexer.nextToken();
                expr = new SqlJsonArrayExpr();
                this.isParsingArray = true;
                if (this.lexer.token != Token.RBRACKET) {
                    this.exprList(((SqlJsonArrayExpr) expr).getItems(), expr);
                }

                this.accept(Token.RBRACKET);
                this.isParsingArray = false;
                break;
            case LBRACE:
                this.lexer.nextToken();
                expr = new SqlJsonObjectExpr();
                if (this.lexer.token != Token.RBRACE) {
                    while (this.lexer.token != Token.RBRACE) {
                        String key = this.lexer.stringVal();
                        this.lexer.nextToken();
                        this.accept(Token.COLON);
                        ((SqlJsonObjectExpr) expr).putItem(key, (SqlValuableExpr) this.expr());
                        if (this.lexer.token == Token.COMMA) {
                            this.lexer.nextToken();
                        }
                    }
                }
                this.accept(Token.RBRACE);
                break;
            case LPAREN:
                this.lexer.nextToken();
                if (this.lexer.token != Token.RPAREN) {
                    if (!this.isParsingArray) {
                        expr = this.expr();
                        if (expr instanceof SqlBinaryOpExpr) {
                            ((SqlBinaryOpExpr) expr).setParenthesized(true);
                        }
                    } else {
                        // 当在解析[(x,x,x), (y,y,y), ...]数组时，遇到逗号“(”就收集一个List表达式
                        SqlListExpr listExpr = new SqlListExpr();
                        this.exprList(listExpr.getItems(), listExpr);
                        expr = listExpr;
                    }
                }

                this.accept(Token.RPAREN);
                break;
            case EXISTS: // exists子查询
                this.lexer.nextToken();

                SqlSelectParser subQuerySelectParser = new SqlSelectParser(this.lexer);
                SqlSelect subQuery = subQuerySelectParser.select(true);
                SqlExistsExpr existsExpr = new SqlExistsExpr();
                existsExpr.setNot(isNot);
                existsExpr.setSubQuery(subQuery);
                expr = existsExpr;

                break;
            default:
                printError(this.lexer.token);
        }

        expr = this.primaryRest(expr);

        return expr;
    }

    /**
     * 解析原子的剩余部分
     *
     * @param expr
     * @return
     */
    private SqlExpr primaryRest(final SqlExpr expr) {
        if (expr == null) {
            throw new IllegalArgumentException("expr parse error: null.");
        }

        SqlExpr tExpr = expr;
        Token token = this.lexer.token;

        if (token == Token.DOT) {
            this.lexer.nextToken();
            tExpr = this.dotRest(tExpr);
            return this.primaryRest(tExpr);
        } else if (this.lexer.token == Token.LPAREN) {
            SqlExpr method = this.methodInvokeRest(tExpr);
            return method;
        }

        return tExpr;
    }

    /**
     * 解析"."之后的部分
     *
     * @param expr
     * @return
     */
    protected SqlExpr dotRest(final SqlExpr expr) {
        String name;
        SqlExpr tExpr = expr;
        if (this.lexer.token == Token.IDENTIFIER) {
            name = this.lexer.stringVal();
            this.lexer.nextToken();
            if (tExpr instanceof SqlPropertyExpr) {
                // 碰到a.b.c的情况，保持a作为owner不变，使名称带点，SQL层不支持属性
                SqlPropertyExpr propExpr = (SqlPropertyExpr) tExpr;
                String newName = propExpr.getName() + Token.DOT.name + name;
                propExpr.setName(newName);
            } else {
                tExpr = new SqlPropertyExpr((SqlIdentifierExpr) tExpr, name);
            }
        }

        tExpr = this.primaryRest(tExpr);
        return tExpr;
    }

    /**
     * 解析方法调用的后面部分
     *
     * @param expr
     * @return
     */
    protected SqlExpr methodInvokeRest(final SqlExpr expr) {
        this.accept(Token.LPAREN);

        if (expr instanceof SqlIdentifierExpr) {
            SqlMethodInvokeExpr invokeExpr;
            SqlIdentifierExpr idExpr = (SqlIdentifierExpr) expr;
            String methodName = idExpr.getName();
            String methodNameUppercase = methodName.toUpperCase();
            if (AGGREGATE_FUNCTIONS.contains(methodNameUppercase)) {
                boolean distinct = false;
                if (this.lexer.token == Token.DISTINCT) {
                    this.lexer.nextToken();
                    distinct = true;
                }

                invokeExpr = new SqlAggregateExpr(methodName);
                if (distinct) {
                    ((SqlAggregateExpr) invokeExpr).setOption(SqlAggregateOption.DISTINCT);
                }
            } else {
                invokeExpr = new SqlMethodInvokeExpr(methodName);
            }

            Token token = this.lexer.token;
            if (token != Token.RPAREN) {
                this.exprList(invokeExpr.getArguments(), invokeExpr);
            }

            this.accept(Token.RPAREN);

            return invokeExpr;
        } else {
            return expr;
        }
    }

    /**
     * 解析一个逗号隔开的列表表达式(a, b, c)，并设置父节点
     *
     * @param exprCol
     * @param parent
     */
    public final <T extends SqlExpr> void exprList(List<T> exprCol, SqlObject parent) {
        while (this.lexer.token != Token.RPAREN) {
            SqlExpr expr = this.expr();

            if (this.lexer.token == Token.AS) {
                accept(Token.AS);
                String alias = this.lexer.stringVal();
                this.lexer.nextToken();
                // 存在这样的场景：object(f1 as f1')
                expr = new SqlSelectItem(expr, alias);
            }

            expr.setParent(parent);
            exprCol.add((T) expr);

            if (this.lexer.token != Token.COMMA) {
                return;
            }

            this.lexer.nextToken();
        }
    }


    /**
     * 断言接受一个token，不满足时将抛出异常
     *
     * @param token
     */
    public void accept(Token token) {
        if (this.lexer.token == token) {
            this.lexer.nextToken();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("syntax error, expect ");
            sb.append(token.name != null ? token.name : token.toString());
            sb.append(", actual ");
            sb.append(this.lexer.token.name != null ? this.lexer.token.name : this.lexer.token.toString());
            sb.append(" ");
            sb.append(this.lexer.info());
            throw new SqlParseException(sb.toString());
        }
    }

    /**
     * 解析表
     *
     * @return
     */
    protected SqlTableSource parseTableSource() {
        SqlTableSource tableSource = this.parseExprTableSource();
        return tableSource;
    }

    protected SqlExprTableSource parseExprTableSource() {
        String tableName = this.lexer.stringVal();
        SqlExprTableSource tableSource = new SqlExprTableSource(tableName);
        this.lexer.nextToken();

        String alias = null;
        if (this.lexer.token == Token.AS) {
            tableSource.setHasAliasKeyword(true);
            this.lexer.nextToken();
            alias = this.lexer.stringVal();
            this.accept(Token.IDENTIFIER);
        } else if (this.lexer.token == Token.IDENTIFIER) {
            alias = this.lexer.stringVal();
            this.lexer.nextToken();
        }

        if (alias != null) {
            tableSource.setAlias(alias);
        }

        return tableSource;
    }

    /**
     * 解析以逗号隔开的列表
     *
     * @param function
     */
    protected void parseCommaSeperatedList(ParseListFunction function) {
        while (true) {
            function.parseItem();
            if (this.lexer.token != Token.COMMA) {
                break;
            }

            this.lexer.nextToken();
        }
    }

}
