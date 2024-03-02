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
    protected static final Map<String, OqlInfo> oqlInfos = new HashMap<>();

    protected AbstractOqlTest(String oqlFilePath) {
        this.initSqlMap(oqlFilePath);
    }

    /**
     * 从类路径下加载OQL文件并初始化 OQL 映射
     *
     * @param oqlFilePath
     */
    protected final void initSqlMap(String oqlFilePath) {
        String oqlInfosStr = FileTestUtils.loadTextFromClasspath(oqlFilePath);
        JSONObject oqlInfosJson = JSONObject.parseObject(oqlInfosStr);
        JSONArray oqlArray = oqlInfosJson.getJSONArray("oqls");
        for (int i = 0, l = oqlArray.size(); i < l; i++) {
            JSONObject oqlJson = oqlArray.getJSONObject(i);
            OqlInfo oqlInfo = new OqlInfo();
            oqlInfo.code = oqlJson.getString("code");
            oqlInfo.oql = oqlJson.getString("oql");
            oqlInfo.sql = oqlJson.getString("sql");
            this.oqlInfos.put(oqlInfo.code, oqlInfo);
        }
    }

    protected final class OqlInfo {
        public String code;
        public String oql;
        public String sql;
    }

}
