package io.github.cloudstars.lowcode.object.form.editor.convert;

import io.github.cloudstars.lowcode.component.form.FormItemConfig;
import io.github.cloudstars.lowcode.object.commons.field.TextObjectFieldConfig;

@ObjectFieldConfig2ComponentConfigConvertorClass(type = "TextInput")
public class TextFieldConfig2ComponentConfigConvertor extends AbstractObjectFieldConfig2ComponentConfigConvertor<TextObjectFieldConfig> {

    @Override
    public FormItemConfig convert(TextObjectFieldConfig objectFieldConfig, FormViewMode viewMode) {
        return null;
    }

}
