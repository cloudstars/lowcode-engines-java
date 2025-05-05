package io.github.cloudstars.lowcode.object.view.engine.form;

import io.github.cloudstars.lowcode.object.view.editor.form.AbstractObjectFormViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.AbstractObjectViewImpl;

public abstract class AbstractObjectFormViewImpl<T extends AbstractObjectFormViewConfig> extends AbstractObjectViewImpl<T> {

    public AbstractObjectFormViewImpl(T viewConfig) {
        super(viewConfig);
    }


}
