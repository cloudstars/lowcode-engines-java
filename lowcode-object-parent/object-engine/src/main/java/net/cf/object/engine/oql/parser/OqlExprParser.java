package net.cf.object.engine.oql.parser;

import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.parser.Lexer;
import net.cf.form.repository.sql.parser.SqlExprParser;
import net.cf.form.repository.sql.parser.Token;

/**
 * OQL 表达式解析器
 *
 * @author clouds
 */
public class OqlExprParser extends SqlExprParser {

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
     * 解析方法调用的后面部分
     *
     * @param expr
     * @return
     */
    @Override
    protected SqlExpr methodInvokeRest(final SqlExpr expr) {
        SqlExpr invokeRest = super.methodInvokeRest(expr);
        if (invokeRest instanceof SqlMethodInvokeExpr) {
            SqlMethodInvokeExpr methodInvokeExpr = (SqlMethodInvokeExpr) invokeRest;
            String methodName = methodInvokeExpr.getMethodName();
            // TODO 如果methodName是一个模型的话，那这转换为objectExpandExpr
            return new OqlObjectExpandExpr(methodName, methodInvokeExpr.getArguments());
        } else {
            return invokeRest;
        }
    }

    /**
     * 解析模型源
     *
     * @return
     */
    protected OqlExprObjectSource parseObjectSource() {
        String objectName = this.lexer.stringVal();
        OqlExprObjectSource objectSource = new OqlExprObjectSource(objectName);
        this.lexer.nextToken();
        if (this.lexer.token() == Token.AS) {
            this.lexer.nextToken();
            String alias = this.lexer.stringVal();
            objectSource.setAlias(alias);
            this.lexer.nextToken();
        }

        return objectSource;
    }

    protected OqlExprObjectSource parseExprObjectSource() {
        String objectName = this.lexer.stringVal();
        OqlExprObjectSource objectSource = new OqlExprObjectSource(objectName);
        this.lexer.nextToken();
        if (this.lexer.token() == Token.AS) {
            this.lexer.nextToken();
            String alias = this.lexer.stringVal();
            objectSource.setAlias(alias);
            this.lexer.nextToken();
        }

        return objectSource;
    }
}
