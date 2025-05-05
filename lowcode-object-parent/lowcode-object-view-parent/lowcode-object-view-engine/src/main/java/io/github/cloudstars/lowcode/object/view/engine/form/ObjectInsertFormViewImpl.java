package io.github.cloudstars.lowcode.object.view.engine.form;

import io.github.cloudstars.lowcode.object.view.editor.form.ObjectInsertFormViewConfig;
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
