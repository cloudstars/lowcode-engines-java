package net.cf.object.engine.oql.testcase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.object.engine.oql.parser.XObjectResolver;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    protected XObjectResolver resolver;

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
            oqlInfo.name = oqlJson.getString("name");
            oqlInfo.oql = oqlJson.getString("oql");
            oqlInfo.sql = oqlJson.getString("sql");
            oqlInfo.detailSql = oqlJson.getString("detailSql");
            oqlInfo.detailUpdateInsertSql = oqlJson.getString("detailUpdateInsertSql");
            oqlInfo.detailUpdateUpdateSql = oqlJson.getString("detailUpdateUpdateSql");
            oqlInfo.detailUpdateDeleteSql = oqlJson.getString("detailUpdateDeleteSql");
            String paramMap = oqlJson.getString("paramMap");
            if (paramMap != null) {
                oqlInfo.paramMap = JSONObject.parseObject(paramMap, HashMap.class);
            }
            String paramMaps = oqlJson.getString("paramMaps");
            if (paramMaps != null) {
                oqlInfo.paramMaps = new ArrayList<>();
                JSONArray array = JSONArray.parseArray(paramMaps);
                for (int j = 0, s = array.size(); j < s; j++) {
                    oqlInfo.paramMaps.add(array.getJSONObject(j));
                }
            }
            String resultMap = oqlJson.getString("resultMap");
            if (resultMap != null) {
                oqlInfo.resultMap = JSONObject.parseObject(resultMap, HashMap.class);
            }
            String resultMaps = oqlJson.getString("resultMaps");
            if (resultMaps != null) {
                oqlInfo.resultMaps = new ArrayList<>();
                JSONArray array = JSONArray.parseArray(resultMaps);
                for (int j = 0, s = array.size(); j < s; j++) {
                    oqlInfo.resultMaps.add(array.getJSONObject(j));
                }
            }

            this.oqlInfos.put(oqlInfo.name, oqlInfo);
        }
    }

    protected final class OqlInfo {
        public String name;
        public String oql;
        public String sql;
        public String detailSql;

        public Map<String, Object> paramMap;

        public List<Map<String, Object>> paramMaps;

        public Map<String, Object> resultMap;

        public List<Map<String, Object>> resultMaps;

        // 以下3个字段更新子表专用
        public String detailUpdateUpdateSql;
        public String detailUpdateInsertSql;
        public String detailUpdateDeleteSql;
    }

}
