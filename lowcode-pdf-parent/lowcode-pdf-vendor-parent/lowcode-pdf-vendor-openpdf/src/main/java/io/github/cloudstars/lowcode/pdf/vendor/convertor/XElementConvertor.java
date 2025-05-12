package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import com.lowagie.text.Element;
import io.github.cloudstars.lowcode.pdf.commons.config.element.XElementConfig;

/**
 * OpenPdf元素转换器
 *
 * @author clouds
 */
public interface XElementConvertor<T extends XElementConfig, E extends Element> {

    /**
     * 将PDF配置转换为OpenPdf的元素
     *
     * @param elementConfig PDF元素配置
     * @return OpenPdf元素
     */
    E toPdfElement(T elementConfig);
}
