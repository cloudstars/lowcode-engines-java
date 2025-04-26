package io.github.cloudstars.lowcode.object.view.editor.descriptor;

import io.github.cloudstars.lowcode.commons.descriptor.ConfigAttribute;
import io.github.cloudstars.lowcode.commons.descriptor.XDescriptor;

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
    public List<ConfigAttribute> getAttributes() {
        return null;
    }
}
