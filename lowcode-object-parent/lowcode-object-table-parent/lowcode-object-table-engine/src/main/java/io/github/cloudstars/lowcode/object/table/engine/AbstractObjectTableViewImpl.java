package io.github.cloudstars.lowcode.object.table.engine;

import io.github.cloudstars.lowcode.object.table.editor.AbstractObjectTableViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.AbstractObjectView;

public abstract class AbstractObjectTableViewImpl<T extends AbstractObjectTableViewConfig> extends AbstractObjectView<T> {

    public AbstractObjectTableViewImpl(T viewConfig) {
        super(viewConfig);
    }


}
