package net.cf.api.proxy.engine.filter.builtin.auth.token;

/**
 * token
 * @author 80345746
 * @version v1.0
 * @date 2024/1/30 19:28
 */
public class Token {
    private final String data;
    private final Integer expires;

    public Token(String data, Integer expires) {
        this.data = data;
        this.expires = expires;
    }

    public String getData() {
        return data;
    }

    public Integer getExpires() {
        return expires;
    }
}
