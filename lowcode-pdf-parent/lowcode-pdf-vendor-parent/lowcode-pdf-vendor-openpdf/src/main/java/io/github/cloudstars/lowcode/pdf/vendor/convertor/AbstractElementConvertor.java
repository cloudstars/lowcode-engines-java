package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import com.lowagie.text.Element;
import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

public abstract class AbstractElementConvertor<T extends AbstractElementConfig, E extends Element> implements XElementConvertor<T, E> {

    private T elementConfig;

    public AbstractElementConvertor(T elementConfig) {
        this.elementConfig = elementConfig;
    }

}
