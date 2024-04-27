package net.cf.object.engine.oql.testcase.update.detail;

import net.cf.object.engine.object.TestConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新出差记录本表、行程子表的案例
 *
 * @author clouds
 */
public interface UpdateTravelDetailTest {

    String OQL_FILE_PATH = "oql/update/detail/UpdateTravelAndTrip.json";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_VARS = "UpdateTravelAndTripByIdVars";

    void testUpdateTravelAndTripByIdVars();

    /**
     * 获取更新时的测试数据
     *
     * @return
     */
    default Map<String, Object> getEditParamMap(String editTravelId, String editTripId) {
        // 添加一条新数据（无主键字段）
        Map<String, Object> newTrip0 = new HashMap<>();
        newTrip0.put("fromAddr", "苏州");
        newTrip0.put("toAddr", "天津");
        newTrip0.put("creator", TestConstants.CREATOR);
        newTrip0.put("modifier", TestConstants.CREATOR);

        // 更新一条旧数据（带主键字段）
        Map<String, Object> editTrip0 = new HashMap<>();
        editTrip0.put("tripId", editTripId);
        editTrip0.put("fromAddr", "连云港");
        editTrip0.put("toAddr", "济南");
        newTrip0.put("creator", TestConstants.CREATOR);
        editTrip0.put("modifier", TestConstants.MODIFIER);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", editTravelId);
        paramMap.put("applyName", "新的APPLY_NAME");
        List<Map<String, Object>> editTrips = new ArrayList<>();
        editTrips.add(newTrip0);
        editTrips.add(editTrip0);
        paramMap.put("trips", editTrips);

        return paramMap;
    }

}