package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

public abstract class AbstractObjectViewImpl<T extends AbstractObjectViewConfig> implements ObjectView<T> {

    private T viewConfig;

    public AbstractObjectViewImpl(T viewConfig) {
        this.viewConfig = viewConfig;
    }

    @Override
    public Object executeApi(String apiType, Object apiParams) {
        ObjectViewApi viewApi = this.resolveApi(apiType);
        return viewApi.execute(apiParams);
    }

    protected abstract ObjectViewApi resolveApi(String apiType);
}
