package io.github.cloudstars.lowcode.api.executor.invoke;

import java.util.List;
import java.util.Map;

/**
 * API 文件上传请求
 *
 * @author clouds
 */
public class ApiFileUploadRequest extends AbstractApiRequest {

    /**
     * 请求的表单项
     */
    private Map<String, List<Object>> formItems;

    public Map<String, List<Object>> getFormItems() {
        return formItems;
    }

    public void setFormItems(Map<String, List<Object>> formItems) {
        this.formItems = formItems;
    }
}
