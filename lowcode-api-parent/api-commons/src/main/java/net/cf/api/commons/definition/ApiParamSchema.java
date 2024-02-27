package net.cf.api.commons.definition;

import java.util.List;
import java.util.Map;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/2/27 11:14
 */
public class ApiParamSchema {
    private String id;
    private String type;
    private List<String> request;
    private Map<String, ApiParamSchema> properties;
    private List<ApiParamSchema> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRequest() {
        return request;
    }

    public void setRequest(List<String> request) {
        this.request = request;
    }

    public Map<String, ApiParamSchema> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, ApiParamSchema> properties) {
        this.properties = properties;
    }

    public List<ApiParamSchema> getItems() {
        return items;
    }

    public void setItems(List<ApiParamSchema> items) {
        this.items = items;
    }
}
