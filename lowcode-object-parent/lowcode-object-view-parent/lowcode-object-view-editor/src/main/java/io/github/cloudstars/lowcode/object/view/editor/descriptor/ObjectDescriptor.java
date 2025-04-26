package io.github.cloudstars.lowcode.object.view.editor.descriptor;


import io.github.cloudstars.lowcode.commons.descriptor.ConfigAttribute;
import io.github.cloudstars.lowcode.commons.descriptor.XDescriptor;

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
    public List<ConfigAttribute> getAttributes() {
        return null;
    }

}
