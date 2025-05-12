package io.github.cloudstars.lowcode.pdf.engine;

import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;

import java.io.OutputStream;
import java.util.Map;

/**
 * PDF构建引擎
 *
 * @author clouds
 */
public interface PdfEngine {

    /**
     * 生成PDF文件
     *
     * @param config 构建配置
     * @param dataMap 输入数据
     * @param os 输出文件流
     */
    void build(PdfBuildConfig config, Map<String, Object> dataMap, OutputStream os);

}
