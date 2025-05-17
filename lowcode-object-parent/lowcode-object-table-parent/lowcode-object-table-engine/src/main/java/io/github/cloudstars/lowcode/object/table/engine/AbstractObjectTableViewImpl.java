package io.github.cloudstars.lowcode.object.table.engine;

import io.github.cloudstars.lowcode.object.table.editor.AbstractObjectTableViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.AbstractObjectViewImpl;

public abstract class AbstractObjectTableViewImpl<T extends AbstractObjectTableViewConfig> extends AbstractObjectViewImpl<T> {

    public AbstractObjectTableViewImpl(T viewConfig) {
        super(viewConfig);
    }


}
