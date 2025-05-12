package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

public abstract class AbstractElementConvertor<T extends AbstractElementConfig> implements XElementConvertor<T> {

    private T elementConfig;

    public AbstractElementConvertor(T elementConfig) {
        this.elementConfig = elementConfig;
    }

    @Override
    public T getElementConfig() {
        return elementConfig;
    }

}
