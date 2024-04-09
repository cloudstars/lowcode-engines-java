package net.cf.object.engine.oql.testcase.insert.detail;

import net.cf.object.engine.object.TestConstants;

import java.util.*;

/**
 * 插入出差记录，并且插入行程子表
 *
 * @author clouds
 */
public interface InsertTravelDetailTest {

    String OQL_FILE_PATH = "oql/insert/InsertTravelAndTrip.json";

    String OQL_INSERT_TRAVEL_AND_TRIPS_VARS = "InsertTravelAndTripVars";

    void testIInsertTravelAndTripVars();

    default Map<String, Object> getCreateParamMap() {
        // 行程数据
        List<Map<String, Object>> trips = new ArrayList<>();
        Map<String, Object> trip1 = new HashMap<>();
        trip1.put("fromAddr", "杭州");
        trip1.put("toAddr", "上海");
        trip1.put("creator", TestConstants.CREATOR);
        Map<String, Object> trip2 = new HashMap<>();
        trip2.put("fromAddr", "上海");
        trip2.put("toAddr", "杭州");
        trip2.put("creator", TestConstants.CREATOR);
        trips.add(trip1);
        trips.add(trip2);

        // 出差主数据
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", "test----------");
        paramMap.put("applyName", "测试申请单的名称");
        paramMap.put("trips", Arrays.asList(trip1, trip2));
        return paramMap;
    }

}


