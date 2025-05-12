package io.github.cloudstars.lowcode.pdf.commons.config.element;

import io.github.cloudstars.lowcode.commons.config.XConfig;
import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;

/**
 * PDF元素
 *
 * @author clouds
 */
public interface XElementConfig extends XConfig {

    /**
     * 获取转换器的类型名称
     *
     * @return 转换器的类型名称
     */
    String getType();

    /**
     * 获取PDF元素占比
     *
     * @return 0-24间的整数
     */
    default int getSize() {
        return PdfConstants.MAX_ELEMENT_SIZE;
    }

    /**
     * 获取标签
     *
     * @return 标签
     */
    String getLabel();

    /**
     * 获取数据源配置
     *
     * @return 数据源配置
     */
    XDataSourceConfig getDataSource();

}
