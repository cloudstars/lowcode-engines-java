package io.github.cloudstars.lowcode.object.view.editor.descriptor;


import io.github.cloudstars.lowcode.commons.descriptor.ConfigAttribute;

import java.util.List;

/**
 * 文本字段规范
 *
 * @author clouds
 */
public abstract class TextFieldDescriptor extends AbstractFieldDescriptor {

    @Override
    public String getName() {
        return "TextField";
    }

    @Override
    public List<ConfigAttribute> getAttributes() {
        return null;
    }
}
