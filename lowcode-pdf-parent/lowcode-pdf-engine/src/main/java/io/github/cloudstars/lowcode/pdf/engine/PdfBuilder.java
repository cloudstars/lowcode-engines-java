package io.github.cloudstars.lowcode.pdf.engine;

import java.io.InputStream;
import java.util.Map;

/**
 * PDF生成器
 *
 * @author clouds
 */
public interface PdfBuilder {

    /**
     * 生成PDF文件
     *
     * @param dataMap PDF数据
     * @return PDF文件流
     */
    InputStream build(Map<String, Object> dataMap);

}
