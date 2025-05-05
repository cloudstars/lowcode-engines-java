package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;

public abstract class AbstractObjectViewApi implements ObjectViewApi {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ApiConfig getApiConfig() {
        return null;
    }

}
