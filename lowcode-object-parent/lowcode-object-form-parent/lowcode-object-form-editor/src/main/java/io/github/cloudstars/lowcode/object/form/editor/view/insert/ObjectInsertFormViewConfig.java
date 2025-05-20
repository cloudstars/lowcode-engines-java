
package io.github.cloudstars.lowcode.object.form.editor.view.insert;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.form.editor.AbstractObjectFormViewConfig;
import io.github.cloudstars.lowcode.object.form.editor.FormViewField;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

import java.util.List;

/**
 * 数据插入表单视图配置
 *
 * @author clouds
 */
@ObjectViewConfigClass(name = "InsertForm")
public class ObjectInsertFormViewConfig extends AbstractObjectFormViewConfig {

    /**
     * 字段编号
     */
    // private List<String> fieldKeys;

    private List<FormViewField> fields;

    public ObjectInsertFormViewConfig() {
    }

    public ObjectInsertFormViewConfig(JsonObject configJson) {
        super(configJson);
    }

    public List<FormViewField> getFields() {
        return fields;
    }

    public void setFields(List<FormViewField> fields) {
        this.fields = fields;
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
