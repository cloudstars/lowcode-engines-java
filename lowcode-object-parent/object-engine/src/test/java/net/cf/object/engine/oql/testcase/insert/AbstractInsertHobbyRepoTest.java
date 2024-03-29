package net.cf.object.engine.oql.testcase.insert;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 插入兴趣爱好表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertHobbyRepoTest extends AbstractOqlRepoTest implements InsertHobbyTest {

    protected AbstractInsertHobbyRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Hobby.json"};
    }

    @Override
    public void testInsertHobby() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_HOBBY);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engine.create(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select code, name, descr from Hobby where code = #{code}";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("code", "DJ");
            Map<String, Object> data = this.engine.queryOne(selectOqlStmt, paramMap);
            assert (data != null);
            Object dbRecordId = data.get("code");
            assert (dbRecordId != null && "DJ".equals(dbRecordId.toString()));
        }
    }

}
