package io.github.cloudstars.lowcode.object.form.engine;

import io.github.cloudstars.lowcode.object.form.editor.AbstractObjectFormViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.AbstractObjectViewImpl;

public abstract class AbstractObjectFormViewImpl<T extends AbstractObjectFormViewConfig> extends AbstractObjectViewImpl<T> {

    public AbstractObjectFormViewImpl(T viewConfig) {
        super(viewConfig);
    }


}
