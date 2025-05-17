package io.github.cloudstars.lowcode.object.form.editor.view.insert;

import io.github.cloudstars.lowcode.component.form.FormComponentConfig;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfigToComponentConfigConvertor;

/**
 * 表单插入视图配置到表单组件配置的转换器
 *
 * @author clouds
 */
public class ObjectInsertViewConfig2FormComponentConfigConvertor implements XObjectViewConfigToComponentConfigConvertor<ObjectInsertFormViewConfig, FormComponentConfig> {

    @Override
    public FormComponentConfig convert(ObjectInsertFormViewConfig viewConfig) {
        FormComponentConfig formComponentConfig = new FormComponentConfig();

        return formComponentConfig;
    }

}
