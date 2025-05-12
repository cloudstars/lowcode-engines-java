package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import com.lowagie.text.pdf.BaseFont;
import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractElementConvertor<T extends AbstractElementConfig> implements XElementConvertor<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractElementConvertor.class);

    private String fontName = "STSong-Light";

    protected BaseFont bfChinese;

    private T elementConfig;

    public AbstractElementConvertor(T elementConfig) {
        this.elementConfig = elementConfig;

        try {
            this.bfChinese = BaseFont.createFont(fontName, "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (IOException e) {
            LOGGER.error("加载PDF构建的字体{}失败", e);
        }
    }

    @Override
    public T getElementConfig() {
        return elementConfig;
    }

}
