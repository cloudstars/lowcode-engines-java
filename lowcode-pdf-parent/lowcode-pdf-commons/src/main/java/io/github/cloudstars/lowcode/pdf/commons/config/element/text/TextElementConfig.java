package io.github.cloudstars.lowcode.pdf.commons.config.element.text;

import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

/**
 * PDF文本元素
 *
 * @author clouds
 */
public class TextElementConfig extends AbstractElementConfig<TextValueTypeConfig> {

    public TextElementConfig() {
    }

    @Override
    public String getType() {
        return "TEXT";
    }



}
