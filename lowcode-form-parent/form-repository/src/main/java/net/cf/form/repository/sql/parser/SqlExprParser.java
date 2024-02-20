package net.cf.form.repository.sql.parser;

import net.cf.form.repository.sql.FastSqlException;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.SqlListExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.operation.*;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlJoinTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
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
    public static final List<String> AGGREGATE_FUNCTIONS = Arrays.asList("AVG", "COUNT", "MAX", "MIN", "STDDEV", "SUM");

    /**
     * 方法的名称（当前写死在代码中，要支持扩展的话，需要开放正定义能力）
     */
    public static final List<String> METHOD_NAMES = Arrays.asList("NOW", "DATE");

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
            tExpr = new SqlBinaryOpExpr(tExpr, SqlBinaryOperator.BooleanOr, rightExp);
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
            tExpr = new SqlBinaryOpExpr(tExpr, SqlBinaryOperator.BooleanAnd, rightExp);
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
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.Multiply, rightExpr);
            targetExpr = this.multiplicativeRest(targetExpr);
        } else if (token == Token.SLASH) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.Divide, rightExpr);
            targetExpr = this.multiplicativeRest(targetExpr);
        } else if (token == Token.PERCENT) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.Modulus, rightExpr);
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
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.Add, rightExpr);
            targetExpr = this.additiveRest(targetExpr);
        } else if (token == Token.SUB) {
            this.lexer.nextToken();
            rightExpr = this.multiplicative();
            targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.Subtract, rightExpr);
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
        Token token = this.lexer.token;
        switch (token) {
            case EQ:
            case EQEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.Equality, rightExp);
                break;
            case LTGT:
            case BANGEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.NotEqual, rightExp);
                break;
            case LT:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.LessThan, rightExp);
                break;
            case LTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.LessThanOrEqual, rightExp);
                break;
            case GT:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.GreaterThan, rightExp);
                break;
            case GTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                targetExpr = new SqlBinaryOpExpr(targetExpr, SqlBinaryOperator.GreaterThanOrEqual, rightExp);
                break;
            case LIKE:
                this.lexer.nextToken();
                rightExp = this.expr();
                this.lexer.nextToken();
                targetExpr = new SqlLikeOpExpr(targetExpr, rightExp);

                if (this.lexer.token == Token.ESCAPE) {
                    this.lexer.nextToken();
                    ((SqlLikeOpExpr) targetExpr).setEscape(this.lexer.stringVal());
                    this.lexer.nextToken();
                }

                break;
            case IN:
                this.lexer.nextToken();
                this.accept(Token.LPAREN);
                targetExpr = new SqlInListExpr(targetExpr);
                this.exprList(((SqlInListExpr) targetExpr).getTargetList(), targetExpr);
                this.accept(Token.RPAREN);

                break;
            case CONTAINS:
                this.lexer.nextToken();

                /*SqlJsonArrayExpr array = new SqlJsonArrayExpr();
                targetExpr = new SqlArrayContainsOpExpr(targetExpr, array);
                if (this.lexer.token == Token.ANY) {
                    ((SqlArrayContainsOpExpr) targetExpr).setOption(SqlArrayContainsOption.ANY);
                    this.lexer.nextToken();
                } else if (this.lexer.token == Token.ALL) {
                    ((SqlArrayContainsOpExpr) targetExpr).setOption(SqlArrayContainsOption.ALL);
                    this.lexer.nextToken();
                }

                this.accept(Token.LBRACKET);
                this.exprList(array.getItems(), array);
                this.accept(Token.RBRACKET);
*/
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
        Token token = this.lexer.token;
        SqlExpr expr = null;
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
                expr = new SqlNumberExpr(new BigDecimal(numberValue.toCharArray()));
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
            case NOT:
                this.lexer.nextToken();
                expr = new SqlNotExpr(this.primary());
                break;
            case LBRACKET:
                this.lexer.nextToken();
                /*expr = new SqlJsonArrayExpr();
                this.isParsingArray = true;
                if (this.lexer.token != Token.RBRACKET) {
                    this.exprList(((SqlJsonArrayExpr) expr).getItems(), expr);
                }*/

                this.accept(Token.RBRACKET);
                this.isParsingArray = false;
                break;
            case LBRACE:
                this.lexer.nextToken();
                /*expr = new SqlJsonObjectExpr();
                if (this.lexer.token != Token.RBRACE) {
                    while (this.lexer.token != Token.RBRACE) {
                        String key = this.lexer.stringVal();
                        this.lexer.nextToken();
                        this.accept(Token.COLON);
                        ((SqlJsonObjectExpr) expr).putItem(key, this.expr());
                        if (this.lexer.token == Token.COMMA) {
                            this.lexer.nextToken();
                        }
                    }
                }*/
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
            tExpr = new SqlPropertyExpr((SqlName) tExpr, name);
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
            SqlMethodInvokeExpr methodInvokeExpr = null;
            SqlIdentifierExpr idExpr = (SqlIdentifierExpr) expr;
            String methodName = idExpr.getSimpleName();
            String methodNameUppercase = methodName.toUpperCase();
            if (AGGREGATE_FUNCTIONS.contains(methodNameUppercase)) {
                boolean distinct = false;
                if (this.lexer.token == Token.DISTINCT) {
                    this.lexer.nextToken();
                    distinct = true;
                }

                methodInvokeExpr = new SqlAggregateExpr(methodName);
                if (distinct) {
                    ((SqlAggregateExpr) methodInvokeExpr).setOption(SqlAggregateOption.DISTINCT);
                }
            } else if (METHOD_NAMES.contains(methodNameUppercase)) {
                methodInvokeExpr = new SqlMethodInvokeExpr(methodName);
            }

            Token token = this.lexer.token;
            if (token != Token.RPAREN) {
                this.exprList(methodInvokeExpr.getArguments(), methodInvokeExpr);
            }

            this.accept(Token.RPAREN);

            return methodInvokeExpr;
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
    public final void exprList(Collection<SqlExpr> exprCol, SqlObject parent) {
        int countCommaCount = 0;
        //if (this.lexer.token != Token.RPAREN && this.lexer.token != Token.EOF) {
        while (this.lexer.token != Token.RPAREN) {
            SqlExpr expr = null;
            if (this.lexer.token == Token.COMMA) {
                // expr = new SqlUndefinedExpr();
            } else {
                expr = this.expr();
            }

            //if (expr != null) {
            if (this.lexer.token == Token.AS) {
                accept(Token.AS);
                String alias = this.lexer.stringVal();
                this.lexer.nextToken();
                expr = new SqlSelectItem(expr, alias);
            }

            expr.setParent(parent);
            exprCol.add(expr);
            //}

            if (this.lexer.token != Token.COMMA) {
                return;
            }
            countCommaCount++;

            this.lexer.nextToken();
        }
        //}

        // 逗号的数量 + 1 等于 元数的个数，不足时初充SqlUndefinedExpr，如：(1,)
        if (countCommaCount > 0 && exprCol.size() < countCommaCount + 1) {
            //exprCol.add(new SqlUndefinedExpr());
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
        SqlTableSource tableSource = null;
        SqlJoinTableSource.JoinType joinType = null;
        while (this.lexer.token != Token.WHERE) {
            if (this.lexer.token == Token.LEFT) {
                joinType = SqlJoinTableSource.JoinType.LEFT_OUTER_JOIN;
                this.lexer.nextToken();
                this.accept(Token.JOIN);
            } else if (this.lexer.token == Token.RIGHT) {
                joinType = SqlJoinTableSource.JoinType.RIGHT_OUTER_JOIN;
                this.lexer.nextToken();
                this.accept(Token.JOIN);
            } else if (this.lexer.token == Token.JOIN) {
                joinType = SqlJoinTableSource.JoinType.JOIN;
                this.accept(Token.JOIN);
            } else if (this.lexer.token == Token.CROSS) {
                joinType = SqlJoinTableSource.JoinType.CROSS_JOIN;
                this.lexer.nextToken();
                this.accept(Token.JOIN);
            } else if (this.lexer.token == Token.COMMA) {
                joinType = SqlJoinTableSource.JoinType.COMMA;
                this.lexer.nextToken();
            } else if (this.lexer.token == Token.ON) {
                if (tableSource == null || !(tableSource instanceof SqlJoinTableSource)) {
                    throw new FastSqlException("keyword \'on\' is allowed follow table join");
                }
                this.accept(Token.ON);
                ((SqlJoinTableSource) tableSource).setCondition(this.expr());
            } else {
                if (tableSource == null) {
                    tableSource = this.parseExprTableSource();
                } else {
                    SqlJoinTableSource joinTableSource = new SqlJoinTableSource();
                    joinTableSource.setLeft(tableSource);
                    joinTableSource.setJoinType(joinType);
                    joinTableSource.setRight(this.parseExprTableSource());
                    tableSource = joinTableSource;
                }
            }
        }

        return tableSource;
    }

    protected SqlExprTableSource parseExprTableSource() {
        String tableName = this.lexer.stringVal();
        SqlExprTableSource tableSource = new SqlExprTableSource(tableName);
        this.lexer.nextToken();
        if (this.lexer.token == Token.AS) {
            this.lexer.nextToken();
            String alias = this.lexer.stringVal();
            tableSource.setAlias(alias);
            this.lexer.nextToken();
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
