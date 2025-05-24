package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.formula.parser.function.FunctionConfig;
import io.github.cloudstars.lowcode.formula.parser.function.FunctionConfigFactory;
import io.github.cloudstars.lowcode.formula.parser.function.FunctionParam;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParserBaseListener;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

/**
 * 公式解析错误监听器
 *
 * @author clouds
 */
public class FormulaParseListener extends FxParserBaseListener {

    private StringBuilder errors;

    public FormulaParseListener() {
        this.errors = new StringBuilder();
    }

    @Override
    public void exitFunctionCallExpression(FxParser.FunctionCallExpressionContext ctx) {
        FxParser.IdentifierContext funcNameContext = (FxParser.IdentifierContext) ctx.getChild(0);

        Token startToken = ctx.getStart();
        int line = startToken.getLine();
        int charPositionInLine = startToken.getCharPositionInLine();

        String funcName = funcNameContext.getText();
        FunctionConfig functionConfig = FunctionConfigFactory.get(funcName);
        if (functionConfig == null) {
            String msg = "函数" + funcName + "不存在！";
            errors.append("line " + line + ":" + charPositionInLine + " " + msg);
            return;
        }

        FxParser.ArgumentsContext argsContext = (FxParser.ArgumentsContext) ctx.getChild(1);
        int fxFuncParamSize = 0;
        for (int i = 0, l = argsContext.getChildCount(); i < l; i++) {
            if (!(argsContext.getChild(i) instanceof TerminalNode)) {
                fxFuncParamSize++;
            }
        }
        List<FunctionParam> configFuncParams = functionConfig.getParams();
        int configFuncParamSize = configFuncParams != null ? configFuncParams.size() : 0;
        if (fxFuncParamSize > configFuncParamSize) {
            String msg = "函数" + funcName + "参数个数不正确！";
            errors.append("line " + line + ":" + charPositionInLine + " " + msg);
        }

        for (int i = fxFuncParamSize; i < configFuncParamSize; i++) {
            if (configFuncParams.get(i).isRequired()) {
                String msg = "函数" + funcName + "第" + (i + 1) + "个参数必填";
                errors.append("line " + line + ":" + charPositionInLine + " " + msg);
            }
        }
    }

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    public String getErrors() {
        return this.errors.toString();
    }

}
