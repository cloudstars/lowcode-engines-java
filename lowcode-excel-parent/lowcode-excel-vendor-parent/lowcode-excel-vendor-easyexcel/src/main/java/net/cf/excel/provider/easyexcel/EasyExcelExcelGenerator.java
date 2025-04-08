package net.cf.excel.provider.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import net.cf.excel.provider.ExcelGenerator;
import net.cf.excel.provider.config.ExcelGeneratorConfig;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EasyExcelExcelGenerator<T extends Object> implements ExcelGenerator<T> {

    /**
     * Excel生成配置
     */
    private ExcelGeneratorConfig config;

    /**
     * 输出文件流
     */
    private OutputStream os;

    /**
     * 当行处理的行号
     */
    private int rowIndex = 0;

    private ExcelWriter excelWriter;

    private WriteSheet writeSheet;

    public EasyExcelExcelGenerator(ExcelGeneratorConfig config, OutputStream os) {
        this.config = config;
        this.os = os;

        this.excelWriter = EasyExcel.write(os).build();
        List<List<String>> head = new ArrayList<>();
        head.add(Arrays.asList("a"));
        head.add(Arrays.asList("b"));
        this.writeSheet = EasyExcel.writerSheet(0).head(head).build();
    }


    @Override
    public void append(int index, List<T> list) {
        List<List<String>> finalList = new ArrayList<>();
        list.forEach((item) -> {
            // TODO 这里先假设输入是MAP
            Map<String, Object> m = (Map<String, Object>) item;
            List<String> row = new ArrayList<>();
            m.forEach((k, v) -> {
                // TODO 这里先忽略k的顺序
                row.add((String) v);
            });
            finalList.add(row);
        });
        this.excelWriter.write(finalList, this.writeSheet);
    }

    @Override
    public void finish() {
        this.excelWriter.finish();
    }

}