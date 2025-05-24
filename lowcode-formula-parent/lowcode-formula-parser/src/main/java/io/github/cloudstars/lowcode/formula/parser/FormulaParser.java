package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.formula.parser.g4.FxLexer;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public static FxParser.FxContext parse(String fx) {
        LOGGER.debug("公式：{}解析开始。", fx);

        // 生成错误监听器
        FormulaParseErrorListener errorListener = new FormulaParseErrorListener();

        // 生成词法解析器
        FxLexer lexer = new FxLexer(CharStreams.fromString(fx));
        // 移除默认的错误监听器
        lexer.removeErrorListeners();
        // 添加新的错误监听器
        lexer.addErrorListener(errorListener);

        // 生成语法解析器
        TokenStream tokenStream = new CommonTokenStream(lexer);
        FxParser parser = new FxParser(tokenStream);
        // 添加语法解析监听器
        FormulaParseListener parserListener = new FormulaParseListener();
        parser.addParseListener(parserListener);
        // 移除默认的错误监听器
        parser.removeErrorListeners();
        // 添加新的错误监听器
        parser.addErrorListener(errorListener);
        FxParser.FxContext context = parser.fx();

        // 错误检查
        String errors = errorListener.getErrors();
        if (errors.length() > 0) {
            LOGGER.debug("公式[{}]解析错误：{}", fx, errors);
            throw new SyntaxException("公式[" + fx + "]语法错误：" + errors);
        }
        String parserErrors = parserListener.getErrors();
        if (parserErrors.length() > 0) {
            LOGGER.debug("公式[{}]解析错误：{}", fx, parserErrors);
            throw new SyntaxException("公式[" + fx + "]语法错误：" + parserErrors);
        }

        LOGGER.debug("公式：{}解析结束。", fx);

        return context;
    }
}