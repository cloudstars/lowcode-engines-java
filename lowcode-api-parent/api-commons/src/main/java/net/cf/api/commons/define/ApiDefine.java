package net.cf.api.commons.define;

import com.alibaba.fastjson.JSONObject;
import net.cf.api.commons.enums.ApiPublicizedEnum;
import net.cf.api.commons.enums.ApiTypeEnum;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 21:45
 */
public class ApiDefine {
    private String key;
    private String name;
    private JSONObject input;
    private JSONObject output;
    private ApiTypeEnum type;
    private Boolean enabled;
    private ApiPublicizedEnum publicized;
    private Boolean open;
    private String mockValue;
    private Boolean mockEnabled;
    private String bizKey;

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

    public JSONObject getInput() {
        return input;
    }

    public void setInput(JSONObject input) {
        this.input = input;
    }

    public JSONObject getOutput() {
        return output;
    }

    public void setOutput(JSONObject output) {
        this.output = output;
    }

    public ApiTypeEnum getType() {
        return type;
    }

    public void setType(ApiTypeEnum type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ApiPublicizedEnum getPublicized() {
        return publicized;
    }

    public void setPublicized(ApiPublicizedEnum publicized) {
        this.publicized = publicized;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getMockValue() {
        return mockValue;
    }

    public void setMockValue(String mockValue) {
        this.mockValue = mockValue;
    }

    public Boolean getMockEnabled() {
        return mockEnabled;
    }

    public void setMockEnabled(Boolean mockEnabled) {
        this.mockEnabled = mockEnabled;
    }

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }
}
