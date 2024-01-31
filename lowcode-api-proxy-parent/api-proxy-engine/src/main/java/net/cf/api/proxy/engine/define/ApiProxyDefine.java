package net.cf.api.proxy.engine.define;

import net.cf.api.proxy.engine.enums.AuthTypeEnum;
import net.cf.api.proxy.engine.enums.MethodEnum;
import net.cf.api.proxy.engine.enums.RespBodyTypeEnum;
import net.cf.api.proxy.engine.enums.SignTypeEnum;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 22:45
 */
public class ApiProxyDefine {
    private String key;
    private String name;

    private String url;
    private RespBodyTypeEnum respBodyType;

    private MethodEnum method;
    private Auth auth;
    private Sign sign;

    private Boolean mockEnabled;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MethodEnum getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(MethodEnum method) {
        this.method = method;
    }

    public RespBodyTypeEnum geRespBodyType() {
        return respBodyType;
    }

    public RespBodyTypeEnum getRespBodyType() {
        return respBodyType;
    }

    public void setRespBodyType(RespBodyTypeEnum respBodyType) {
        this.respBodyType = respBodyType;
    }

    public AuthTypeEnum getAuthType() {
        return null;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public boolean getMockEnabled() {
        return mockEnabled;
    }

    public void setMockEnabled(Boolean mockEnabled) {
        this.mockEnabled = mockEnabled;
    }
    public class Auth {
        private AuthTypeEnum type;

        public AuthTypeEnum getType() {
            return type;
        }

        public void setType(AuthTypeEnum type) {
            this.type = type;
        }
    }

    public class Sign {
        private SignTypeEnum type;

        public SignTypeEnum getType() {
            return type;
        }

        public void setType(SignTypeEnum type) {
            this.type = type;
        }
    }

}
