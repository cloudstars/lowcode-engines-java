package io.github.cloudstars.lowcode.api.executor.invoke;

import java.util.Map;

/**
 * API Post请求
 *
 * @author clouds
 */
public class ApiPostRequest extends AbstractApiRequest {

    /**
     * 请求参数
     */
    private Object body;

    /**
     * 请求的表单项
     */
    private Map<String, Object> formItems;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<String, Object> getFormItems() {
        return formItems;
    }

    public void setFormItems(Map<String, Object> formItems) {
        this.formItems = formItems;
    }
}
