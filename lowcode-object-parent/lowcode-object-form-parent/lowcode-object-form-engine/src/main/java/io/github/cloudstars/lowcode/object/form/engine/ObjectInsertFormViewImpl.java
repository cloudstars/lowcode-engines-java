package io.github.cloudstars.lowcode.object.form.engine;

import io.github.cloudstars.lowcode.object.form.editor.view.insert.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.ObjectViewApi;

public class ObjectInsertFormViewImpl extends AbstractObjectFormViewImpl<ObjectInsertFormViewConfig> {

    public ObjectInsertFormViewImpl(ObjectInsertFormViewConfig viewConfig) {
        super(viewConfig);
    }

    @Override
    protected ObjectViewApi resolveApi(String apiType) {
        return null;
    }

}
