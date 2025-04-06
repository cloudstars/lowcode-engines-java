package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.SingleExcelTitle;

import java.math.BigDecimal;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 09:00
 * @Description: 整数Field实现类
 */
public class SingleExcelTitleImpl implements SingleExcelTitle {
    private String code;

    private String name;

    private DataFormatter dataFormatter;

    private static final String titleType = "number";

    public SingleExcelTitleImpl(String code, String name, DataFormatter dataFormatter) {
        this.code = code;
        this.name = name;
        this.dataFormatter = dataFormatter;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DataFormatter<BigDecimal> getDataFormatter() {
        return dataFormatter;
    }
}