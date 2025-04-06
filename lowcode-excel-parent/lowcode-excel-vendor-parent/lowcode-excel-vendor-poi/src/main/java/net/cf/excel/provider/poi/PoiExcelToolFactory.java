package net.cf.excel.provider.poi;

import net.cf.excel.provider.ExcelGenerator;
import net.cf.excel.provider.ExcelParser;
import net.cf.excel.provider.ExcelToolFactory;
import net.cf.excel.provider.config.ExcelGeneratorConfig;
import net.cf.excel.provider.config.ExcelParserConfig;

import java.io.InputStream;
import java.io.OutputStream;

public class PoiExcelToolFactory implements ExcelToolFactory {

    @Override
    public ExcelGenerator newGenerator(ExcelGeneratorConfig config, OutputStream os) {
        return new PoiExcelGenerator(config, os);
    }

    @Override
    public ExcelParser newParser(InputStream is, ExcelParserConfig config) {
        return null;
    }
}
