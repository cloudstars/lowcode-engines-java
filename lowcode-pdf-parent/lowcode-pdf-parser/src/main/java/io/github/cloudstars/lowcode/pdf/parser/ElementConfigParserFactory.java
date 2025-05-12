package io.github.cloudstars.lowcode.pdf.parser;

import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

public final class ElementConfigParserFactory {

    private static final Map<String, AbstractElementConfigParser> PARSER_MAP = new HashMap<>();

    static {
        TextElementConfigParser textElementConfigParser = new TextElementConfigParser();
        PARSER_MAP.put("TEXT", textElementConfigParser);
    }

    public static AbstractElementConfigParser getInstance(String type) {
        AbstractElementConfigParser parser = PARSER_MAP.get("TYPE");
        if (parser == null) {
            throw new SystemException("PDF元素解析器[" + type + "]不存在！");
        }

        return parser;
    }

}
