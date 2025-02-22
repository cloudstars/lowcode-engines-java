package net.cf.object.engine.oql.testcase.update;

import net.cf.object.engine.object.TravelObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新出差记录本表（带子属性）的测试接口
 *
 * @author clouds
 */
public interface UpdateTravelSelfPropertiesTest {

    String OQL_FILE_PATH = "oql/update/UpdateTravelSelfProperties.json";

    String OQL_UPDATE_TRAVEL_MODIFIER_BY_ID = "UpdateTravelModifierById";

    String OQL_UPDATE_TRAVEL_MODIFIER_BY_ID_VARS = "UpdateTravelModifierByIdVars";

    String OQL_UPDATE_TRAVEL_WITH_ATTACHES_BY_ID_VARS = "UpdateTravelWithAttachesByIdVars";

    void testUpdateTravelModifierById();

    void testUpdateTravelModifierByIdVars();

    void testUpdateTravelWithAttachesByIdVars();

    default Map<String, Object> getEditParamMap() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        Map<String, Object> modifier = new HashMap<>();
        modifier.put("name", "更新人姓名");
        modifier.put("key", "更新人编号");
        paramMap.put("modifier", modifier);
        return paramMap;
    }

}