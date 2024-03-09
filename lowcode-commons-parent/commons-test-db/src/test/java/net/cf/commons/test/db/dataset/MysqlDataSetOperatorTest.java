package net.cf.commons.test.db.dataset;

import com.alibaba.fastjson.JSONArray;
import net.cf.commons.test.db.CommonsTestDbApplication;
import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ActiveProfiles("mysql")
@SpringBootTest(classes = CommonsTestDbApplication.class)
public class MysqlDataSetOperatorTest {

    @Resource
    private MysqlDataSetOperator dataSetOperator;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testLoadTableA() {
        String filePath = "dataset/tableA.json";
        IDataSet dataSet = JsonDataSetLoader.loadFromClassPath(new String[]{filePath});
        this.dataSetOperator.tearDown(dataSet);
        this.dataSetOperator.setUp(dataSet);

        // 查询DB的数据与预期的数据对比
        List<Map<String, Object>> dataList = this.namedParameterJdbcTemplate.queryForList("select * from A", new HashMap<>());
        Map<String, Object> loadedData = JsonTestUtils.loadMapFromClasspath(filePath);
        JSONArray loadedDataValues = (JSONArray) loadedData.get("valuesList");
        assert (DataCompareTestUtils.isAssignableFromList(loadedDataValues, dataList));
    }

}
