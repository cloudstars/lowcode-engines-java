package io.github.cloudstars.lowcode.pdf.vendor.convertor;


import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * OpenPdf元素转换器Java类工厂
 *
 * @author clouds
 */
public class ElementConvertorClassFactory {

    /**
     * key是节点类型名称，值是OpenPdf元素转换器Java类的映射表
     */
    private static final Map<String, Class<? extends XElementConvertor>> convertorClassMap = new HashMap<>();

    private ElementConvertorClassFactory() {
    }

    /**
     * 注册一种OpenPdf元素转换器Java类
     *
     * @param convertorClass OpenPdf元素转换器Java类
     */
    public static void register(Class<? extends XElementConvertor> convertorClass) {
        String typeName = convertorClass.getName();
        if (convertorClassMap.containsKey(typeName)) {
            throw new RuntimeException("OpenPdf元素转换器类型[" + typeName + "]已存在，注册失败！");
        }

        ElementConvertorClass annotation = convertorClass.getAnnotation(ElementConvertorClass.class);
        if (annotation == null) {
            throw new RuntimeException("OpenPdf元素转换器类型[" + typeName + "]必须添加注解@ElementConvertorClass，注册失败！");
        }

        Class<? extends AbstractElementConfig> elementClass = annotation.elementClass();
        try {
            convertorClass.getConstructor(elementClass);
        } catch (Exception e) {
            throw new RuntimeException("OpenPdf元素转换器类型" + typeName + "必须有一个带" + elementClass.getName() + "参数的public构造函数！");
        }

        convertorClassMap.put(annotation.type().toUpperCase(), convertorClass);
    }

    /**
     * 根据节点类型的名称获取OpenPdf元素转换器Java的类
     *
     * @param typeName 节点类型的名称
     * @return OpenPdf元素转换器的Java类
     */
    public static Class<? extends XElementConvertor> get(String typeName) {
        Class<? extends XElementConvertor> configClass = convertorClassMap.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("OpenPdf元素转换器类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
