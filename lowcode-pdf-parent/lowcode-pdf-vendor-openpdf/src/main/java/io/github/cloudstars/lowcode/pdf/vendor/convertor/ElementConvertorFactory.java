package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import io.github.cloudstars.lowcode.pdf.commons.config.element.XElementConfig;

import java.lang.reflect.Constructor;

/**
 * OpenPdf元素转换器工厂
 *
 * @author clouds
 */
public class ElementConvertorFactory {

    private ElementConvertorFactory() {
    }

    /**
     * 根据转换器的配置实例化一个OpenPdf元素转换器
     *
     * @param elementConfig
     * @return
     */
    public static XElementConvertor newInstance(XElementConfig elementConfig) {
        String type = elementConfig.getType();
        Class<? extends XElementConvertor> convertorClass = ElementConvertorClassFactory.get(type);
        try {
            ElementConvertorClass annotations = convertorClass.getAnnotation(ElementConvertorClass.class);
            Constructor<? extends XElementConvertor> constructor = convertorClass.getConstructor(annotations.elementClass());
            return constructor.newInstance(elementConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化OpenPdf元素转换器，类型为[" + type + "]出错！", e);
        }
    }

}
