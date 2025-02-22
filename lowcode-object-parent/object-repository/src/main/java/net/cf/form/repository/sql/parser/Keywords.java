package net.cf.form.repository.sql.parser;

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

    /**
     * 根据名称获取关键字 Token
     *
     * @param key
     * @return 如果是关键字则返回关键字 Token，否则返回 null
     */
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
        Token[] tokens = {
                Token.AND,
                Token.OR,
                Token.NOT,
                Token.CONTAINS,
                Token.ALL,
                Token.ANY,
                Token.IN,
                Token.IS,
                Token.LIKE,
                Token.EXISTS,
                Token.NULL,
                Token.TRUE,
                Token.FALSE,
                Token.SELECT,
                Token.DISTINCT,
                Token.FROM,
                Token.AS,
                Token.CASE,
                Token.WHEN,
                Token.THEN,
                Token.ELSE,
                Token.END,
                Token.LEFT,
                Token.RIGHT,
                Token.FULL,
                Token.INNER,
                Token.CROSS,
                Token.JOIN,
                Token.ON,
                Token.WHERE,
                Token.GROUP,
                Token.BY,
                Token.HAVING,
                Token.ORDER,
                Token.ASC,
                Token.DESC,
                Token.ESCAPE,
                Token.LIMIT,
                Token.INSERT,
                Token.INTO,
                Token.VALUES,
                Token.UPDATE,
                Token.SET,
                Token.DELETE
        };

        for (int i = 0; i < tokens.length; i++) {
            map.put(tokens[i].name, tokens[i]);
        }

        DEFAULT_KEYWORDS = new Keywords(map);
    }
}

