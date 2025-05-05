package io.github.cloudstars.lowcode.object.view.engine.table;

import io.github.cloudstars.lowcode.object.view.editor.table.AbstractObjectTableViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.AbstractObjectViewImpl;

public abstract class AbstractObjectTableViewImpl<T extends AbstractObjectTableViewConfig> extends AbstractObjectViewImpl<T> {

    public AbstractObjectTableViewImpl(T viewConfig) {
        super(viewConfig);
    }


}
