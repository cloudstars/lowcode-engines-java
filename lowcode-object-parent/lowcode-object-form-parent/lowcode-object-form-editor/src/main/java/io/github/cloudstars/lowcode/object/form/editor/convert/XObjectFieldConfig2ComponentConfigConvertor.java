package io.github.cloudstars.lowcode.object.form.editor.convert;

import io.github.cloudstars.lowcode.component.form.FormItemConfig;
import io.github.cloudstars.lowcode.object.commons.field.XObjectFieldConfig;

/**
 * 将模型字段配置转换为表单项组件的转换器
 *
 * @param <F> 模型字段配置
 */
public interface XObjectFieldConfig2ComponentConfigConvertor<F extends XObjectFieldConfig> {

    /**
     * 将字段转为表单项组件
     *
     * @param objectFieldConfig 模型字段配置
     * @param viewMode 视图模态
     * @return 表单项组件配置
     */
    FormItemConfig convert(F objectFieldConfig, FormViewMode viewMode);

}
