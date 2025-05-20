package io.github.cloudstars.lowcode.object.form.editor.view.insert;

import io.github.cloudstars.lowcode.component.form.FormComponentConfig;
import io.github.cloudstars.lowcode.component.form.FormItemConfig;
import io.github.cloudstars.lowcode.object.commons.XObjectConfig;
import io.github.cloudstars.lowcode.object.commons.XObjectConfigResolver;
import io.github.cloudstars.lowcode.object.commons.field.XObjectFieldConfig;
import io.github.cloudstars.lowcode.object.form.editor.FormViewField;
import io.github.cloudstars.lowcode.object.form.editor.convert.FormViewMode;
import io.github.cloudstars.lowcode.object.form.editor.convert.ObjectFieldConfig2ComponentConfigConvertorFactory;
import io.github.cloudstars.lowcode.object.form.editor.convert.XObjectFieldConfig2ComponentConfigConvertor;
import io.github.cloudstars.lowcode.object.view.editor.convert.AbstractObjectViewConfig2ComponentConfigConvertor;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单插入视图配置到表单组件配置的转换器
 *
 * @author clouds
 */
public class ObjectInsertViewConfig2FormComponentConfigConvertor extends AbstractObjectViewConfig2ComponentConfigConvertor<ObjectInsertFormViewConfig, FormComponentConfig> {

    public ObjectInsertViewConfig2FormComponentConfigConvertor(XObjectConfigResolver resolver) {
        super(resolver);
    }

    @Override
    public FormComponentConfig convert(ObjectInsertFormViewConfig viewConfig) {
        String objectKey = viewConfig.getObjectKey();
        XObjectConfig objectConfig = this.resolver.resolve(objectKey);
        List<FormViewField> viewFields = viewConfig.getFields();
        FormComponentConfig formComponentConfig = new FormComponentConfig();
        List<FormItemConfig> formItemConfigs = new ArrayList<>();
        for (FormViewField viewField : viewFields) {
            String formFieldKey = viewField.getFieldKey();
            XObjectFieldConfig fieldConfig = objectConfig.getFieldByKey(formFieldKey);
            XObjectFieldConfig2ComponentConfigConvertor convert = ObjectFieldConfig2ComponentConfigConvertorFactory.getByType(null);
            FormItemConfig formItemConfig = convert.convert(fieldConfig, FormViewMode.INSERT);
            formItemConfigs.add(formItemConfig);
        }
        formComponentConfig.setItems(formItemConfigs);

        return formComponentConfig;
    }

}
