package io.github.cloudstars.lowcode.object.editor.descriptor;

import io.github.cloudstars.lowcode.commons.editor.XDescriptor;

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
    public List<XDescriptor.Attribute> getAttributes() {
        return null;
    }
}
