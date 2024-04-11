package net.cf.form.repository.mongo.data;

import java.util.Map;

public class DataConvertContext {

    /**
     * 替换字段
     */
    private Map<String, String> replaceFields;

    public DataConvertContext(Map<String, String> replaceFields) {
        this.replaceFields = replaceFields;
    }

    public Map<String, String> getReplaceFields() {
        return replaceFields;
    }

    public boolean isReplaceField() {
        return replaceFields != null && replaceFields.size() > 0;
    }
}
