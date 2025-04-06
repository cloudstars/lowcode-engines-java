package net.cf.formula.engine.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * 关键字表
 *
 * @author clouds
 */
public class Keywords {
    private final Map<String, Token> keywords;
    private Token[] tokens;
    public static final Keywords DEFAULT_KEYWORDS;

    public Keywords(Map<String, Token> keywords) {
        this.keywords = keywords;
        this.tokens = new Token[keywords.size()];
    }

    public Token getKeyword(String key) {
        return keywords.get(key.toUpperCase());
    }


    public boolean containsValue(Token token) {
        return this.keywords.containsValue(token);
    }

    public Map<String, Token> getKeywords() {
        return this.keywords;
    }

    static {
        Map<String, Token> map = new HashMap();
        map.put("AND", Token.AND);
        map.put("OR", Token.OR);
        map.put("NOT", Token.NOT);

        map.put("ALL", Token.ALL);
        map.put("ANY", Token.ANY);
        map.put("IN", Token.IN);
        map.put("IS", Token.IS);
        map.put("NULL", Token.NULL);

        map.put("TRUE", Token.TRUE);
        map.put("FALSE", Token.FALSE);

        DEFAULT_KEYWORDS = new Keywords(map);
    }
}

