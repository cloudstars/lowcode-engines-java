package io.github.cloudstars.lowcode.commons.editor;

import java.util.List;

/**
 * 规范接口，用于代表某个概念的规范定义
 *
 * @author clouds
 */
public interface XDescriptor {

    /**
     * 获取规范的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取配置对象的类型
     * @return
     */
    String getConfigClassName();

    /**
     * 获取规范的属性列表
     *
     * @return
     */
    List<Attribute> getAttributes();

    /**
     * 属性接口，表示某个属性
     *
     * @author clouds
     */
    class Attribute {

        /**
         * 属性名
         */
        String name;

        /**
         * 属性标题
         */
        String title;

        /**
         * 属性描述
         */
        String description;

        /**
         * 属性设置器（在界面配置的时候用于配置这个属性的值）
         */
        SetterEnum setter;

        /**
         * 属性的默认配置
         */
        Object defaultConfig;

    }

}

