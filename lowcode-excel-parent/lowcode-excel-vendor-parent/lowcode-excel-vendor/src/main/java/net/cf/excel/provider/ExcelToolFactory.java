package net.cf.excel.provider;

import net.cf.excel.provider.config.ExcelGeneratorConfig;
import net.cf.excel.provider.config.ExcelParserConfig;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Excel工具工厂
 *
 * @author clouds
 */
public interface ExcelToolFactory {
    /**
     * 实例化一个Excel生成器
     *
     * @param config
     * @param os
     * @return
     */
    <T extends Object> ExcelGenerator<T> newGenerator(ExcelGeneratorConfig<T> config, OutputStream os);

    /**
     * 实例化一个Excel解析器
     *
     * @param is
     * @param config
     * @return
     */
    <T extends Object> ExcelParser<T> newParser(InputStream is, ExcelParserConfig<T> config);

}
