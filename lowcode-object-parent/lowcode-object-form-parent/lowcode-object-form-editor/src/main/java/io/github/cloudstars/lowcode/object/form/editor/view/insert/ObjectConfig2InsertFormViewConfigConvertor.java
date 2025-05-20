package io.github.cloudstars.lowcode.object.form.editor.view.insert;

import io.github.cloudstars.lowcode.component.form.FormItemConfig;
import io.github.cloudstars.lowcode.object.commons.FormBasedObjectConfig;
import io.github.cloudstars.lowcode.object.view.editor.convert.XObjectConfig2ViewConfigConvertor;

public class ObjectConfig2InsertFormViewConfigConvertor implements XObjectConfig2ViewConfigConvertor<FormBasedObjectConfig, ObjectInsertFormViewConfig> {

    @Override
    public ObjectInsertFormViewConfig convert(FormBasedObjectConfig objectConfig) {
        ObjectInsertFormViewConfig viewConfig = new ObjectInsertFormViewConfig();

        return viewConfig;
    }

    private FormItemConfig to() {
        /*List<XObjectFieldConfig> fieldConfigs = objectConfig.getFields();
        for (XObjectFieldConfig fieldConfig : fieldConfigs) {
            String fieldType = fieldConfig.getType();
            XObjectFieldToComponentConvert convertor = ObjectFieldToComponentConvertorFactory.getByType(fieldType);
            FormComponentConfig componentConfig = convertor.convert(fieldConfig, FormViewMode.INSERT);

        }*/
        return null;
    }

}
