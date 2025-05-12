package io.github.cloudstars.lowcode.pdf.parser;

import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

/**
 * PDF元素配置解析器
 *
 * @author clouds
 */
public final class PdfElementConfigParserFactory {

    /**
     * 从PDF元素类型到对应的解析器的映射
     */
    private static final Map<String, AbstractElementConfigParser> PARSER_MAP = new HashMap<>();

    static {
        TextElementConfigParser textElementConfigParser = new TextElementConfigParser();
        PARSER_MAP.put("TEXT", textElementConfigParser);
    }

    public static AbstractElementConfigParser getInstance(String type) {
        AbstractElementConfigParser parser = PARSER_MAP.get(type);
        if (parser == null) {
            throw new SystemException("PDF元素解析器[" + type + "]不存在！");
        }

        return parser;
    }

}
