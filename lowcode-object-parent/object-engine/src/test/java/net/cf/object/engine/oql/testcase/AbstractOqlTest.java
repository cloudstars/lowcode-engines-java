package net.cf.object.engine.oql.testcase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileTestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象的OQL语句测试类，用于加载测试的OQL语句定义
 *
 * @author clouds
 */
public abstract class AbstractOqlTest {

    /**
     *  待测试的OQL语句的映射
     */
    protected static final Map<String, String> oqlMap = new HashMap<>();

    protected AbstractOqlTest(String oqlFilePath) {
        this.initSqlMap(oqlFilePath);
    }

    /**
     * 从类路径下加载OQL文件并初始化 OQL 映射
     *
     * @param oqlFilePath
     */
    protected final void initSqlMap(String oqlFilePath) {
        String oqlInfos = FileTestUtils.loadTextFromClasspath(oqlFilePath);
        JSONObject oqlInfosJson = JSONObject.parseObject(oqlInfos);
        JSONArray oqls = oqlInfosJson.getJSONArray("oqls");
        for (int i = 0, l = oqls.size(); i < l; i++) {
            JSONObject oql = oqls.getJSONObject(i);
            String oqlCode = oql.getString("code");
            String oqlText = oql.getString("oql");
            this.oqlMap.put(oqlCode, oqlText);
        }
    }

}
