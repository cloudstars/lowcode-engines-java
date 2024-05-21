package net.cf.object.engine.oql.testcase.update.detail;

import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractUpdateTravelDetailRepoTest
        extends AbstractOqlRepoTest
        implements UpdateTravelDetailTest {

    protected AbstractUpdateTravelDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testUpdateTravelAndTripById() {
        String detailOqlSelect = "select * from TravelTrip where travelApplyId = '" + TravelObject.RECORD1 + "'";
        OqlSelectStatement detailOqlSelectStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, detailOqlSelect);
        List<Map<String, Object>> subRecords = this.engine.queryList(detailOqlSelectStmt);
        assert (subRecords.size() == 2);
        Object tripId0 = subRecords.get(0).get("tripId");
        Object tripId1 = subRecords.get(1).get("tripId");

        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID);
            OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            SqlJsonArrayExpr detailValues = (SqlJsonArrayExpr) oqlStmt.getSetItems().get(1).getValue();
            SqlJsonObjectExpr detailValue0 = (SqlJsonObjectExpr) detailValues.getItems().get(0);
            if (tripId0 instanceof Integer) {
                detailValue0.getItems().put("tripId", new SqlIntegerExpr(((Integer) tripId0).intValue()));
            } else {
                detailValue0.getItems().put("tripId", new SqlCharExpr(tripId0.toString()));
            }
            int effectedRows = this.engine.modify(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            subRecords = this.engine.queryList(detailOqlSelectStmt);
            assert (subRecords != null && subRecords.size() == 2); // 新加一条、更新一条、删一条还是2
            boolean tripId0Exist = false; // 第1条子表字段是否存在、
            boolean tripId1Exist = false; // 第2条子表记录是否存在
            for (Map<String, Object> subRecord : subRecords) {
                Object tripId = subRecord.get("tripId");
                if (tripId.equals(tripId0)) {
                    tripId0Exist = true;
                } else if (tripId.equals(tripId1)) {
                    tripId1Exist = true;
                }
            }
            assert (tripId0Exist && !tripId1Exist);
        }
    }

    @Override
    public void testUpdateTravelAndTripByIdVars() {
        String detailOqlSelect = "select * from TravelTrip where travelApplyId = '" + TravelObject.RECORD1 + "'";
        OqlSelectStatement detailOqlSelectStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, detailOqlSelect);
        List<Map<String, Object>> subRecords = this.engine.queryList(detailOqlSelectStmt);
        assert (subRecords.size() == 2);
        Object tripId0 = subRecords.get(0).get("tripId");
        Object tripId1 = subRecords.get(1).get("tripId");

        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_VARS);
            OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            ((Map) (((List) oqlInfo.paramMap.get("trips")).get(0))).put("tripId", tripId0);
            int effectedRows = this.engine.modify(oqlStmt, oqlInfo.paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            subRecords = this.engine.queryList(detailOqlSelectStmt);
            assert (subRecords != null && subRecords.size() == 2); // 新加一条、更新一条、删一条还是2
            boolean tripId0Exist = false; // 第1条子表字段是否存在、
            boolean tripId1Exist = false; // 第2条子表记录是否存在
            for (Map<String, Object> subRecord : subRecords) {
                Object tripId = subRecord.get("tripId");
                if (tripId.equals(tripId0)) {
                    tripId0Exist = true;
                } else if (tripId.equals(tripId1)) {
                    tripId1Exist = true;
                }
            }
            assert (tripId0Exist && !tripId1Exist);
        }
    }

    @Override
    public void testUpdateTravelAndTripByIdWithNull() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_NULL);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        int effectedRows = this.engine.modify(oqlStmt);
        assert (effectedRows == 1);

        this.assertUpdateDetailNullEmpty();
    }

    @Override
    public void testUpdateTravelAndTripByIdWithNullVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_NULL_VARS);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        int effectedRows = this.engine.modify(oqlStmt, oqlInfo.paramMap);
        assert (effectedRows == 1);

        this.assertUpdateDetailNullEmpty();
    }

    @Override
    public void testUpdateTravelAndTripByIdWithEmpty() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_EMPTY);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        int effectedRows = this.engine.modify(oqlStmt);
        assert (effectedRows == 1);

        this.assertUpdateDetailNullEmpty();
    }

    @Override
    public void testUpdateTravelAndTripByIdWithEmptyVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_EMPTY_VARS);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        int effectedRows = this.engine.modify(oqlStmt, oqlInfo.paramMap);
        assert (effectedRows == 1);

        this.assertUpdateDetailNullEmpty();
    }

    /**
     * 断言update子表为null或空数组时，子表被清空
     */
    private void assertUpdateDetailNullEmpty() {
        String detailOqlSelect = "select * from TravelTrip where travelApplyId = '" + TravelObject.RECORD1 + "'";
        OqlSelectStatement detailOqlSelectStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, detailOqlSelect);
        List<Map<String, Object>> subRecords = this.engine.queryList(detailOqlSelectStmt);
        assert (subRecords.size() == 0);
    }


}
