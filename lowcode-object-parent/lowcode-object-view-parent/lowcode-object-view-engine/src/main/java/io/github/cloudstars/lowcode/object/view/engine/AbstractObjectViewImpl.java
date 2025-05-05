package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

public class AbstractObjectViewImpl<T extends AbstractObjectViewConfig> implements ObjectView<T> {

    private T viewConfig;

    public AbstractObjectViewImpl(T viewConfig) {
        this.viewConfig = viewConfig;
    }

}
