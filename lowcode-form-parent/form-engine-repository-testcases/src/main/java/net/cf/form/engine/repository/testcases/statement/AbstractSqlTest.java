package net.cf.form.engine.repository.testcases.statement;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.cf.commons.util.FileUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象的SQL语句测试类，所有SQL语句测试类的基类
 *
 * @author clouds
 */
public abstract class AbstractSqlTest {

    /**
     *  待测试的SQL语句的映射
     */
    protected static final Map<String, String> sqlMap = new HashMap<>();

    /**
     * 从类路径下加载SQL文件并初始化 SQL 映射
     *
     * @param sqlFilePath
     */
    protected static final void initSqls(String sqlFilePath) {
        String sqlInfos = FileUtils.loadTextFromClasspath(sqlFilePath);
        JSONObject sqlInfosJson = JSONObject.parseObject(sqlInfos);
        JSONArray sqls = sqlInfosJson.getJSONArray("sqlList");
        for (int i = 0, l = sqls.size(); i < l; i++) {
            JSONObject sql = sqls.getJSONObject(i);
            String sqlName = sql.getString("name");
            String sqlText = sql.getString("sql");
            sqlMap.put(sqlName, sqlText);
        }
    }

}
