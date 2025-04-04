package io.github.cloudstars.lowcode.commons.test.db.dataset.mongo;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.client.MongoCollection;
import io.github.cloudstars.lowcode.commons.test.db.dataset.IDataSet;
import io.github.cloudstars.lowcode.commons.test.db.dataset.JsonDataSetLoader;
import io.github.cloudstars.lowcode.commons.test.db.dataset.MongoDataSetOperator;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.bson.Document;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringRunner.class)
@ActiveProfiles("mongo")
@SpringBootTest(classes = CommonsTestMongoApplication.class)
@Ignore
public class MongoDataSetOperatorTest {

    @Resource
    private MongoDataSetOperator dataSetOperator;

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoLoad() {
        assert (mongoTemplate != null);
    }

    @Test
    public void testLoadTableA() {
        String filePath = "dataset/tableA.json";
        IDataSet dataSet = JsonDataSetLoader.loadFromClassPath(new String[]{filePath});
        this.dataSetOperator.tearDown(dataSet);
        this.dataSetOperator.setUp(dataSet);

        // 查询DB的数据与预期的数据对比
        MongoCollection<Document> dataList = this.mongoTemplate.getCollection("A");
        long size = dataList.countDocuments();
        Map<String, Object> loadedData = JsonTestUtils.loadMapFromClasspath(filePath);
        JSONArray loadedDataValues = (JSONArray) loadedData.get("valuesList");
        assert (size == loadedDataValues.size());
    }
}
