package net.cf.object.engine.oql.testcase.insert;

import net.cf.object.engine.oql.ast.OqlInsertSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

/**
 * 插入查询兴趣爱好表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertSelectRepoTest extends AbstractOqlRepoTest implements InsertSelectTest {

    protected AbstractInsertSelectRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void InsertHobbySelectFromHobby() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_SELECT_FROM_HOBBY);
            OqlInsertSelectStatement oqlStmt = OqlStatementUtils.parseSingleInsertSelectStatement(this.resolver, oqlInfo.oql);
            int[] effectedRows = this.engine.createSelect(oqlStmt);
            assert (effectedRows.length == 1);
        }
    }

    @Override
    public void InsertTravelTripSelectFromTravelTripLiteral() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_TRIP_SELECT_FROM_TRAVEL_TRIP_LITERAL);
            OqlInsertSelectStatement oqlStmt = OqlStatementUtils.parseSingleInsertSelectStatement(this.resolver, oqlInfo.oql);
            int[] effectedRows = this.engine.createSelect(oqlStmt);
            assert (effectedRows.length == 1);
        }
    }

    @Override
    public void InsertTravelTripSelectFromTravelTripFields() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_TRIP_SELECT_FROM_TRAVEL_TRIP_FIELDS);
            OqlInsertSelectStatement oqlStmt = OqlStatementUtils.parseSingleInsertSelectStatement(this.resolver, oqlInfo.oql);
            int[] effectedRows = this.engine.createSelect(oqlStmt);
            assert (effectedRows.length == 1);
        }
    }

}
