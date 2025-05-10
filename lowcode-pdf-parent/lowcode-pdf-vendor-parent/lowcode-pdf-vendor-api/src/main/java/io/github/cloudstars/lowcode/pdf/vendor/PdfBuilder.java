package io.github.cloudstars.lowcode.pdf.vendor;

import java.io.OutputStream;
import java.util.Map;

/**
 * PDF构建器
 *
 * @author clouds
 */
public interface PdfBuilder {

    /**
     * 构建PDF
     *
     * @param dataMap 输入数据
     * @param os 输出流
     */
    void build(Map<String, Object> dataMap, OutputStream os);

}
