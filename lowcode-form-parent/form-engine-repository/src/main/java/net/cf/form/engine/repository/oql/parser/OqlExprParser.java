package net.cf.form.engine.repository.oql.parser;

import net.cf.form.engine.repository.oql.ast.OqlObject;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.*;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import net.cf.form.engine.repository.oql.ast.expr.operation.*;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectItem;
import net.cf.form.engine.repository.oql.util.StringUtils;
import net.cf.form.engine.repository.oql.ast.expr.identifier.*;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import net.cf.form.engine.repository.oql.ast.expr.operation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * OQL 表达式解析器
 *
 * @author clouds
 */
@Deprecated
public class OqlExprParser extends OqlParser {

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

    public OqlExprParser(String oql) {
        super(oql);
    }

    public OqlExprParser(Lexer lexer) {
        super(lexer);
    }

    /**
     * 解析表达式
     *
     * @return
     */
    public final QqlExpr expr() {
        QqlExpr expr = primary();
        return exprRest(expr);
    }

    /**
     * 解析表达式的剩余部分
     *
     * @param expr
     * @return
     */
    private QqlExpr exprRest(QqlExpr expr) {
        expr = this.multiplicativeRest(expr);
        expr = this.additiveRest(expr);
        expr = this.relationalRest(expr);
        expr = this.andRest(expr);
        expr = this.orRest(expr);
        return expr;
    }

    /**
     * 解析逻辑或
     *
     * @return
     */
    public final QqlExpr or() {
        QqlExpr expr = this.and();
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
    private QqlExpr orRest(QqlExpr expr) {
        while (true) {
            Token token = this.lexer.token;
            if (token != Token.OR && token != Token.BARBAR) {
                break;
            }

            this.lexer.nextToken();
            QqlExpr rightExp = this.and();
            expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.Or, rightExp);
        }

        return expr;
    }


    /**
     * 解析逻辑与
     *
     * @return
     */
    public final QqlExpr and() {
        QqlExpr expr = this.relational();
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
    private QqlExpr andRest(QqlExpr expr) {
        while (true) {
            Token token = this.lexer.token;
            if (token != Token.AND && token != Token.AMPAMP) {
                break;
            }

            this.lexer.nextToken();
            QqlExpr rightExp = this.relational();
            expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.And, rightExp);
        }

        return expr;
    }

    /**
     * 解析乘除法
     *
     * @return
     */
    public final QqlExpr multiplicative() {
        QqlExpr expr = this.primary();
        return this.multiplicativeRest(expr);
    }


    /**
     * 解析乘除法剩余部分
     *
     * @param expr
     * @return
     */
    private QqlExpr multiplicativeRest(QqlExpr expr) {
        Token token = this.lexer.token;
        QqlExpr rightExpr;
        if (token == Token.STAR) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.Multiply, rightExpr);
            expr = this.multiplicativeRest(expr);
        } else if (token == Token.SLASH) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.Divide, rightExpr);
            expr = this.multiplicativeRest(expr);
        } else if (token == Token.PERCENT) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.Modulus, rightExpr);
            expr = this.multiplicativeRest(expr);
        }

        return expr;
    }


    /**
     * 加减法
     *
     * @return
     */
    public final QqlExpr additive() {
        QqlExpr expr = this.multiplicative();
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
    private QqlExpr additiveRest(QqlExpr expr) {
        Token token = this.lexer.token;
        QqlExpr rightExpr;
        if (token == Token.PLUS) {
            this.lexer.nextToken();
            rightExpr = this.multiplicative();
            expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.Add, rightExpr);
            expr = this.additiveRest(expr);
        } else if (token == Token.SUB) {
            this.lexer.nextToken();
            rightExpr = this.multiplicative();
            expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.Subtract, rightExpr);
            expr = this.additiveRest(expr);
        }

        return expr;
    }


    /**
     * 解析关系表达式
     *
     * @return
     */
    public final QqlExpr relational() {
        QqlExpr expr = this.additive();
        return this.relationalRest(expr);
    }

    /**
     * 解析关系表达式的剩余部分
     *
     * @param expr
     * @return
     */
    private QqlExpr relationalRest(QqlExpr expr) {
        Token token = this.lexer.token;
        QqlExpr rightExp;
        switch (token) {
            case EQ:
            case EQEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.Equal, rightExp);
                break;
            case LTGT:
            case BANGEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.NotEqual, rightExp);
                break;
            case LT:
                this.lexer.nextToken();
                rightExp = this.additive();
                expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.LessThan, rightExp);
                break;
            case LTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.LessThanOrEqual, rightExp);
                break;
            case GT:
                this.lexer.nextToken();
                rightExp = this.additive();
                expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.GreaterThan, rightExp);
                break;
            case GTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                expr = new OqlBinaryOpExpr(expr, OqlBinaryOperator.GreaterThanOrEqual, rightExp);
                break;
            case LIKE:
                this.lexer.nextToken();
                rightExp = this.expr();
                this.lexer.nextToken();
                expr = new OqlLikeOpExpr(expr, rightExp);

                if (this.lexer.token == Token.ESCAPE) {
                    this.lexer.nextToken();
                    ((OqlLikeOpExpr) expr).setEscape(this.lexer.stringVal());
                    this.lexer.nextToken();
                }

                break;
            case IN:
                this.lexer.nextToken();
                this.accept(Token.LPAREN);
                OqlListExpr listExpr = new OqlListExpr();
                expr = new OqlInOpExpr(expr, listExpr);
                this.exprList(listExpr.getItems(), listExpr);
                this.accept(Token.RPAREN);

                break;
            case CONTAINS:
                this.lexer.nextToken();

                OqlJsonArrayExpr array = new OqlJsonArrayExpr();
                expr = new OqlArrayContainsOpExpr(expr, array);
                if (this.lexer.token == Token.ANY) {
                    ((OqlArrayContainsOpExpr) expr).setOption(OqlArrayContainsOption.ANY);
                    this.lexer.nextToken();
                } else if (this.lexer.token == Token.ALL) {
                    ((OqlArrayContainsOpExpr) expr).setOption(OqlArrayContainsOption.ALL);
                    this.lexer.nextToken();
                }

                this.accept(Token.LBRACKET);
                this.exprList(array.getItems(), array);
                this.accept(Token.RBRACKET);

                break;
            default:
                break;
        }

        return expr;
    }


    /**
     * 解析一个原子部分
     *
     * @return
     */
    public final QqlExpr primary() {
        Token token = this.lexer.token;
        QqlExpr expr = null;
        switch (token) {
            case STAR:
                expr = new OqlAllFieldExpr();
                this.lexer.nextToken();
                break;
            case LITERAL_STRING:
                expr = new OqlCharExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case LITERAL_INT:
                expr = new OqlIntegerExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case LITERAL_FLOAT:
                String floatValue = this.lexer.stringVal();
                if (!StringUtils.isNumber(floatValue.toCharArray())) {
                    throw new ParserException(floatValue + " is not a number! " + this.lexer.info());
                }

                expr = new OqlNumberExpr(Float.parseFloat(floatValue));
                this.lexer.nextToken();
                break;
            case TRUE:
                this.lexer.nextToken();
                expr = new OqlBooleanExpr(true);
                break;
            case FALSE:
                this.lexer.nextToken();
                expr = new OqlBooleanExpr(false);
                break;
            case NULL:
                expr = new OqlNullExpr();
                this.lexer.nextToken();
                break;
            case IDENTIFIER:
                expr = new OqlIdentifierExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case VARIANT_REF:
                expr = new OqlVariantRefExpr(this.lexer.stringVal());
                this.lexer.nextToken();
                break;
            case BANG:
            case NOT:
                this.lexer.nextToken();
                expr = new OqlNotExpr(this.primary());
                break;
            case LBRACKET:
                this.lexer.nextToken();
                expr = new OqlJsonArrayExpr();
                this.isParsingArray = true;
                if (this.lexer.token != Token.RBRACKET) {
                    this.exprList(((OqlJsonArrayExpr) expr).getItems(), expr);
                }

                this.accept(Token.RBRACKET);
                this.isParsingArray = false;
                break;
            case LBRACE:
                this.lexer.nextToken();
                expr = new OqlJsonObjectExpr();
                if (this.lexer.token != Token.RBRACE) {
                    while (this.lexer.token != Token.RBRACE) {
                        String key = this.lexer.stringVal();
                        this.lexer.nextToken();
                        this.accept(Token.COLON);
                        ((OqlJsonObjectExpr) expr).putItem(key, this.expr());
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
                        if (expr instanceof OqlBinaryOpExpr) {
                            ((OqlBinaryOpExpr) expr).setParenthesized(true);
                        }
                    } else {
                        // 当在解析[(x,x,x), (y,y,y), ...]数组时，遇到逗号“(”就收集一个List表达式
                        OqlListExpr listExpr = new OqlListExpr();
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
    private QqlExpr primaryRest(QqlExpr expr) {
        if (expr == null) {
            throw new IllegalArgumentException("expr parse error: null.");
        } else {
            Token token = this.lexer.token;

            if (token == Token.DOT) {
                this.lexer.nextToken();
                expr = this.dotRest(expr);
                return this.primaryRest(expr);
            } else if (this.lexer.token == Token.LPAREN) {
                QqlExpr method = this.methodInvokeRest(expr);
                return method;
            }

            return expr;
        }
    }

    /**
     * 解析"."之后的部分
     *
     * @param expr
     * @return
     */
    protected QqlExpr dotRest(QqlExpr expr) {
        String name;
        if (this.lexer.token == Token.IDENTIFIER) {
            name = this.lexer.stringVal();
            this.lexer.nextToken();
            expr = new OqlPropertyExpr((QqlNameExpr) expr, name);
        }

        expr = this.primaryRest(expr);
        return expr;
    }

    /**
     * 解析方法调用的后面部分
     *
     * @param expr
     * @return
     */
    protected QqlExpr methodInvokeRest(QqlExpr expr) {
        this.accept(Token.LPAREN);

        if (expr instanceof OqlIdentifierExpr) {
            OqlMethodInvokeExpr methodInvokeExpr;
            OqlIdentifierExpr idExpr = (OqlIdentifierExpr) expr;
            String methodName = idExpr.getName();
            String methodNameUppercase = methodName.toUpperCase();
            if (AGGREGATE_FUNCTIONS.contains(methodNameUppercase)) {
                boolean distinct = false;
                if (this.lexer.token == Token.DISTINCT) {
                    this.lexer.nextToken();
                    distinct = true;
                }

                methodInvokeExpr = new OqlAggregateExpr(methodName);
                if (distinct) {
                    ((OqlAggregateExpr) methodInvokeExpr).setOption(OqlAggregateOption.DISTINCT);
                }
            } else if (METHOD_NAMES.contains(methodNameUppercase)) {
                methodInvokeExpr = new OqlMethodInvokeExpr(methodName);
            } else {
                methodInvokeExpr = new OqlObjectExpandExpr(methodName);
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
    public final void exprList(Collection<QqlExpr> exprCol, OqlObject parent) {
        int countCommaCount = 0;
        //if (this.lexer.token != Token.RPAREN && this.lexer.token != Token.EOF) {
            while (this.lexer.token != Token.RPAREN) {
                QqlExpr expr;
                if (this.lexer.token == Token.COMMA) {
                    expr = new OqlUndefinedExpr();
                } else {
                    expr = this.expr();
                }

                //if (expr != null) {
                    if (this.lexer.token == Token.AS) {
                        accept(Token.AS);
                        String alias = this.lexer.stringVal();
                        this.lexer.nextToken();
                        expr = new OqlSelectItem(expr, alias);
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

        // 逗号的数量 + 1 等于 元数的个数，不足时初充OqlUndefinedExpr，如：(1,)
        if (countCommaCount > 0 && exprCol.size() < countCommaCount + 1) {
            exprCol.add(new OqlUndefinedExpr());
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
            throw new ParserException(sb.toString());
        }
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

    /**
     * 解析对象源
     *
     * @return
     */
    protected OqlObjectSource parseObjectSource() {
        String objectName = this.lexer.stringVal();
        OqlObjectSource objectSource = new OqlExprObjectSource(objectName);
        this.lexer.nextToken();
        if (this.lexer.token == Token.AS) {
            this.lexer.nextToken();
            String alias = this.lexer.stringVal();
            objectSource.setAlias(alias);
            this.lexer.nextToken();
        }

        return objectSource;
    }
}
