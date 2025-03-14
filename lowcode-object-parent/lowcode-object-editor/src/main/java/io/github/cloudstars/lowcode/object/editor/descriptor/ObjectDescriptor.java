package io.github.cloudstars.lowcode.object.editor.descriptor;


import io.github.cloudstars.lowcode.commons.editor.XDescriptor;

import java.util.List;

/**
 * 模型规范
 *
 * @author clouds
 */
public class ObjectDescriptor implements XDescriptor {

    @Override
    public String getName() {
        return "Object";
    }

    @Override
    public String getConfigClassName() {
        return null;
    }

    @Override
    public List<Attribute> getAttributes() {
        return null;
    }

}
