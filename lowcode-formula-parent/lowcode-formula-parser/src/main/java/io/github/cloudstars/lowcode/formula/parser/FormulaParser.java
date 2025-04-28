package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.formula.parser.g4.FxExprLexer;
import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 公式解析器
 *
 * @author clouds
 */
public final class FormulaParser {

    // Slf4j Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(FormulaParser.class);

    /**
     * 解析公式
     *
     * @param fx 公式文本
     * @return 解析后的公式
     */
    public static FxExprParser.ProgramContext parse(String fx) {
        return parse(fx, null);
    }

    /**
     * 解析公式，并且输入了错误监听器列表
     *
     * @param fx 公式文本
     * @param errorListeners 错误监听器列表
     * @return 解析后的公式
     */
    public static FxExprParser.ProgramContext parse(String fx, List<BaseErrorListener> errorListeners) {
        LOGGER.debug("公式：{}解析开始。", fx);

        FxExprLexer lexer = new FxExprLexer(CharStreams.fromString(fx));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        FxExprParser parser = new FxExprParser(tokenStream);

        // 添加自定义的监听器
        if (errorListeners != null && errorListeners.size() > 0) {
            for (BaseErrorListener errorListener : errorListeners) {
                parser.addErrorListener(errorListener);
            }
        }

        FxExprParser.ProgramContext  context = parser.program();
        LOGGER.debug("公式：{}解析结束。", fx);

        return context;

    }
}