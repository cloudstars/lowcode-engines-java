package io.github.cloudstars.lowcode.object.editor.descriptor;

import io.github.cloudstars.lowcode.commons.editor.XDescriptor;

import java.util.List;

/**
 * 模型字段规范
 *
 * @author clouds
 */
public abstract class AbstractFieldDescriptor implements XDescriptor {

    @Override
    public String getName() {
        return "FieldDescriptor";
    }

    @Override
    public List<XDescriptor.Attribute> getAttributes() {
        return null;
    }
}
