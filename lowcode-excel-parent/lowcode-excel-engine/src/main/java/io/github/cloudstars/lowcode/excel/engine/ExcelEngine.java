package io.github.cloudstars.lowcode.excel.engine;

import net.cf.excel.provider.config.ExcelGeneratorConfig;
import net.cf.excel.provider.config.ExcelParserConfig;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Excel引擎接口（新）
 *
 * @author clouds
 */
public interface ExcelEngine {

    /**
     * 生成 Excel
     *
     * @param config Excel生成配置
     * @param os 输出的目标文件流
     * @param dataReader Excel数据读取器
     * @param <T> 数据的类型
     */
    <T extends Object> void generate(ExcelGeneratorConfig<T> config, OutputStream os, ExcelGeneratorDataReader dataReader);

    /**
     * 解析 Excel
     *
     * @param is 输入的源文件流
     * @param config Excel解析配置
     * @param dataWriter Excel数据写入器
     * @param <T> 数据的类型
     */
    <T extends Object> void parse(InputStream is, ExcelParserConfig<T> config, ExcelParserDataWriter dataWriter);

}
