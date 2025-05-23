package net.cf.formula.engine.parser;

import net.cf.formula.engine.ast.FxObject;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxMethodInvokeExpr;
import net.cf.formula.engine.ast.expr.identifier.FxNameExpr;
import net.cf.formula.engine.ast.expr.identifier.FxPropertyExpr;
import net.cf.formula.engine.ast.expr.literal.*;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOperator;
import net.cf.formula.engine.ast.expr.operation.FxUnaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxUnaryOperator;

import java.util.Collection;

/**
 * 公式解析器
 *
 * @author clouds
 */
public class FxExprParser {

    /**
     * 词法分析器
     */
    protected final Lexer lexer;

    public FxExprParser(String fx) {
        this(new Lexer(fx));
    }

    public FxExprParser(Lexer lexer) {
        this.lexer = lexer;
        this.lexer.nextToken();
    }

    /**
     * 解析公式
     *
     * @return
     */
    public final FxExpr expr() {
        FxExpr expr = primary();
        return exprRest(expr);
    }

    /**
     * 解析公式的剩余部分
     *
     * @param expr
     * @return
     */
    private FxExpr exprRest(final FxExpr expr) {
        FxExpr tExpr = expr;
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
    public final FxExpr or() {
        FxExpr expr = this.and();
        if (this.lexer.token == Token.OR || this.lexer.token == Token.BARBAR) {
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
    private FxExpr orRest(final FxExpr expr) {
        FxExpr tExpr = expr;
        while (true) {
            Token token = this.lexer.token;
            if (token != Token.OR && token != Token.BARBAR) {
                break;
            }

            this.lexer.nextToken();
            FxExpr rightExp = this.and();
            tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.OR, rightExp);
        }

        return tExpr;
    }


    /**
     * 解析逻辑与
     *
     * @return
     */
    public final FxExpr and() {
        FxExpr expr = this.relational();
        if (this.lexer.token == Token.AND || this.lexer.token == Token.AMPAMP) {
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
    private FxExpr andRest(final FxExpr expr) {
        FxExpr tExpr = expr;
        while (true) {
            Token token = this.lexer.token;
            if (token != Token.AND && token != Token.AMPAMP) {
                break;
            }

            this.lexer.nextToken();
            FxExpr rightExp = this.relational();
            tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.AND, rightExp);
        }

        return tExpr;
    }

    /**
     * 解析乘除法
     *
     * @return
     */
    public final FxExpr multiplicative() {
        FxExpr expr = this.primary();
        return this.multiplicativeRest(expr);
    }


    /**
     * 解析乘除法剩余部分
     *
     * @param expr
     * @return
     */
    private FxExpr multiplicativeRest(final FxExpr expr) {
        Token token = this.lexer.token;
        FxExpr tExpr = expr;
        FxExpr rightExpr;
        if (token == Token.STAR) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.MULTIPLY, rightExpr);
            tExpr = this.multiplicativeRest(tExpr);
        } else if (token == Token.SLASH) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.DIVIDE, rightExpr);
            tExpr = this.multiplicativeRest(tExpr);
        } else if (token == Token.PERCENT) {
            this.lexer.nextToken();
            rightExpr = this.primary();
            tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.MODULUS, rightExpr);
            tExpr = this.multiplicativeRest(tExpr);
        }

        return tExpr;
    }


    /**
     * 加减法
     *
     * @return
     */
    public final FxExpr additive() {
        FxExpr expr = this.multiplicative();
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
    private FxExpr additiveRest(final FxExpr expr) {
        Token token = this.lexer.token;
        FxExpr tExpr = expr;
        FxExpr rightExpr;
        if (token == Token.PLUS) {
            this.lexer.nextToken();
            rightExpr = this.multiplicative();
            tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.ADD, rightExpr);
            tExpr = this.additiveRest(tExpr);
        } else if (token == Token.SUB) {
            this.lexer.nextToken();
            rightExpr = this.multiplicative();
            tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.SUBTRACT, rightExpr);
            tExpr = this.additiveRest(tExpr);
        }

        return tExpr;
    }


    /**
     * 解析关系表达式
     *
     * @return
     */
    public final FxExpr relational() {
        FxExpr expr = this.additive();
        return this.relationalRest(expr);
    }

    /**
     * 解析关系表达式的剩余部分
     *
     * @param expr
     * @return
     */
    private FxExpr relationalRest(final FxExpr expr) {
        Token token = this.lexer.token;
        FxExpr rightExp;
        FxExpr tExpr = expr;
        switch (token) {
            case EQEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.EQUAL, rightExp);
                break;
            case BANGEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.NOT_EQUAL, rightExp);
                break;
            case LT:
                this.lexer.nextToken();
                rightExp = this.additive();
                tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.LESS_THAN, rightExp);
                break;
            case LTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.LESS_THAN_OR_EQUAL, rightExp);
                break;
            case GT:
                this.lexer.nextToken();
                rightExp = this.additive();
                tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.GREATER_THAN, rightExp);
                break;
            case GTEQ:
                this.lexer.nextToken();
                rightExp = this.additive();
                tExpr = new FxBinaryOpExpr(tExpr, FxBinaryOperator.GREATER_THAN_OR_EQUAL, rightExp);
                break;
            default:
                break;
        }

        return tExpr;
    }


    /**
     * 解析一个原子部分
     *
     * @return
     */
    public final FxExpr primary() {
        Token token = this.lexer.token;
        FxExpr expr = null;
        switch (token) {
            case LITERAL_CHARS:
                expr = new FxCharExpr(this.lexer.getStringValue());
                this.lexer.nextToken();
                break;
            case LITERAL_INT:
                expr = new FxIntegerExpr(this.lexer.getStringValue());
                this.lexer.nextToken();
                break;
            case LITERAL_FLOAT:
                expr = new FxNumberExpr(this.lexer.charsNumberValue());
                this.lexer.nextToken();
                break;
            case TRUE:
                this.lexer.nextToken();
                expr = new FxBooleanExpr(true);
                break;
            case FALSE:
                this.lexer.nextToken();
                expr = new FxBooleanExpr(false);
                break;
            case NULL:
                expr = new FxNullExpr();
                this.lexer.nextToken();
                break;
            case IDENTIFIER:
                expr = new FxIdentifierExpr(this.lexer.getStringValue());
                this.lexer.nextToken();
                break;
            case BANG:
            case NOT:
                this.lexer.nextToken();
                expr = new FxUnaryOpExpr(FxUnaryOperator.NOT, this.primary());
                break;
            case LBRACKET:
                this.lexer.nextToken();
                expr = new FxJsonArrayExpr();
                if (this.lexer.token != Token.RBRACKET) {
                    this.exprList(((FxJsonArrayExpr) expr).getItems(), expr);
                }
                this.accept(Token.RBRACKET);
                break;
            case LBRACE:
                this.lexer.nextToken();
                expr = new FxJsonObjectExpr();
                if (this.lexer.token != Token.RBRACE) {
                    while (this.lexer.token != Token.RBRACE) {
                        String key = this.lexer.getStringValue();
                        this.lexer.nextToken();
                        this.accept(Token.COLON);
                        ((FxJsonObjectExpr) expr).putItem(key, this.expr());
                        if (this.lexer.token == Token.COMMA) {
                            this.lexer.nextToken();
                        }
                    }
                }
                this.accept(Token.RBRACE);
                break;
            case LPAREN:
                this.lexer.nextToken();
                expr = this.expr();
                if (expr instanceof FxBinaryOpExpr) {
                    ((FxBinaryOpExpr) expr).setParenthesized(true);
                }

                this.accept(Token.RPAREN);
                break;
            default:
                break;
        }

        expr = this.primaryRest(expr);

        /* if (beforeComments != null) {
            expr.addBeforeComment(beforeComments);
        } */

        return expr;
    }

    /**
     * 解析原子的剩余部分
     *
     * @param expr
     * @return
     */
    private FxExpr primaryRest(final FxExpr expr) {
        if (expr == null) {
            throw new IllegalArgumentException("expr");
        }

        FxExpr tExpr = expr;
        Token token = this.lexer.token;

        if (token == Token.DOT) {
            this.lexer.nextToken();
            tExpr = this.dotRest(tExpr);
            return this.primaryRest(tExpr);
        } else if (this.lexer.token == Token.LPAREN) {
            FxExpr method = this.methodInvokeRest(tExpr);
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
    protected FxExpr dotRest(final FxExpr expr) {
        String name;
        FxExpr tExpr = expr;
        if (this.lexer.token == Token.IDENTIFIER) {
            name = this.lexer.getStringValue();
            this.lexer.nextToken();
            tExpr = new FxPropertyExpr((FxNameExpr) tExpr, name);
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
    protected FxExpr methodInvokeRest(final FxExpr expr) {
        this.accept(Token.LPAREN);

        if (expr instanceof FxIdentifierExpr) {
            FxIdentifierExpr idExpr = (FxIdentifierExpr) expr;
            String methodName = idExpr.getName();
            FxMethodInvokeExpr invokeExpr = new FxMethodInvokeExpr(methodName);
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
     * 解析一个逗号隔开的列表表达式，并设置父节点
     *
     * @param exprCol
     * @param parent
     */
    public final void exprList(Collection<FxExpr> exprCol, FxObject parent) {
        if (this.lexer.token != Token.RPAREN && this.lexer.token != Token.EOF) {
            while (true) {
                FxExpr expr = this.expr();

                if (expr != null) {
                    expr.setParent(parent);
                    exprCol.add(expr);
                }

                if (this.lexer.token != Token.COMMA) {
                    return;
                }

                this.lexer.nextToken();
            }
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
}
