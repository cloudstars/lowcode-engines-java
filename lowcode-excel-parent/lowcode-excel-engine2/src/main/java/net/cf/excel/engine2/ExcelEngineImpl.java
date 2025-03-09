package net.cf.excel.engine2;

import net.cf.excel.provider.ExcelGenerator;
import net.cf.excel.provider.ExcelParser;
import net.cf.excel.provider.ExcelToolFactory;
import net.cf.excel.provider.config.ExcelGeneratorConfig;
import net.cf.excel.provider.config.ExcelParserConfig;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 抽象的Excel引擎实现类，用于封装整个生成或解析过程中与EXCEL具体工具无关的框架代码
 *
 * @author clouds
 */
public class ExcelEngineImpl implements ExcelEngine {

    /**
     * Excel工具提供器
     */
    private ExcelToolFactory provider;

    public ExcelEngineImpl(ExcelToolFactory provider) {
        this.provider = provider;
    }

    @Override
    public <T extends Object> void generate(ExcelGeneratorConfig<T> config, OutputStream os, ExcelGeneratorDataReader dataReader) {
        ExcelGenerator<T> generator = this.provider.newGenerator(config, os);
        int batchIndex = 0;
        while (true) {
            List<T> dataList = dataReader.readBatchList(batchIndex);
            if (dataList == null || dataList.size() == 0) {
                generator.finish();
                break;
            }

            generator.append(batchIndex, dataList);

            batchIndex++;
        }
    }

    @Override
    public <T extends Object> void parse(InputStream is, ExcelParserConfig<T> config, ExcelParserDataWriter dataWriter) {
        ExcelParser parser = this.provider.newParser(is, config);
        int batchIndex = 0;
        while (true) {
            List<T> dataList = parser.subtract(batchIndex, 1000);
            if (dataList == null || dataList.size() == 0) {
                break;
            }

            dataWriter.writeBatchList(batchIndex, dataList);

            batchIndex++;
        }
    }

}
