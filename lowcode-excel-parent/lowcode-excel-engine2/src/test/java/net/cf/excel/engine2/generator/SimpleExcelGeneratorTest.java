package net.cf.excel.engine2.generator;

import net.cf.excel.engine2.ExcelEngine;
import net.cf.excel.engine2.ExcelEngineTestApplication;
import net.cf.excel.provider.config.ExcelColumnConfig;
import net.cf.excel.provider.config.ExcelGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单的Excel生成测试类
 *
 * @author clouds
 */
@ActiveProfiles("fastexcel")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExcelEngineTestApplication.class)
public class SimpleExcelGeneratorTest {

    @Resource
    private ExcelEngine engine;

    @Test
    public void test() {
        ExcelGeneratorConfig<Map> config = new ExcelGeneratorConfig<>();
        List<ExcelColumnConfig> columnConfigs = new ArrayList<>();
        {
            ExcelColumnConfig columnConfig1 = new ExcelColumnConfig();
            columnConfig1.setName("a");
            columnConfig1.setTitle("a标题");
            ExcelColumnConfig columnConfig2 = new ExcelColumnConfig();
            columnConfig2.setName("b");
            columnConfig2.setTitle("b标题");
            columnConfigs.add(columnConfig1);
            columnConfigs.add(columnConfig2);
            config.setColumns(columnConfigs);
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("src/test/resources/generator/simple/1.xlsx");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.engine.generate(config, fos, (index) -> {
            System.out.println("加载批次：" + index + "的数据！");
            if (index > 10) {
                System.out.println("本批次数据不存在！");
                // 这里假设第11批次数据为空，返回null
                return null;
            }

            List<Map<String, Object>> dataList = new ArrayList<>();
            int total = index < 10 ? 200 : 11;
            for (int i = 0; i < total; i++) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("a", "a" + i);
                dataMap.put("b", "b" + i);
                dataList.add(dataMap);
            }

            System.out.println("本批次共加载" + total + "条数据！");

            return (List) dataList;
        });

        try {
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
