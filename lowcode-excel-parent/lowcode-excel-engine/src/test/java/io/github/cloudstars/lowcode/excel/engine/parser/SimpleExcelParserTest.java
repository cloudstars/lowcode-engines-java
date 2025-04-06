package io.github.cloudstars.lowcode.excel.engine.parser;

import io.github.cloudstars.lowcode.excel.engine.ExcelEngine;
import io.github.cloudstars.lowcode.excel.engine.ExcelEngineTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 简单的Excel解析测试类
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("poi")
@SpringBootTest(classes = ExcelEngineTestApplication.class)
public class SimpleExcelParserTest {

    @Resource
    private ExcelEngine engine;

    @Test
    public void test() {
        this.engine.parse(null, null, (index, list) -> {
            System.out.println("返回批次：" + index + "的数据！");
            List<Map<String, Object>> dataList = (List) list;
            for (int i = 0; i < dataList.size(); i++) {
                dataList.get(i).forEach((k, v) -> {
                    System.out.println("k = " + k + ", v = " + v);
                });
                System.out.println();
            }
        });
    }

}
