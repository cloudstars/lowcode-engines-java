package net.cf.form.engine.repository.mysql;

import net.cf.form.engine.repository.data.*;
import net.cf.form.engine.repository.data.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试对象保持器
 *
 * @author clouds
 */
public final class TestDataObjectsHolder {

    /**
     * 对象映射表
     */
    private static final Map<String, DataObject> objectMap = new HashMap<>();

    private static final DataObject travelObject;

    private static final DataObject travelScheduleObject;

    private static final DataObject travelClaimObject;

    private static final DataObject staffObject;

    static {
        // 创建出差对象
        travelObject = buildTravelObject();
        // 创建出差行程对象
        travelScheduleObject = buildTravelScheduleObject();
        // 构建出差报销对象
        travelClaimObject = buildTravelClaimObject();
        // 构建员工对象
        staffObject = buildStaffObject();

        List<DataObject> objects = Arrays.asList(travelObject, travelScheduleObject, travelClaimObject, staffObject);
        for (DataObject object : objects) {
            objectMap.put(object.getName().toUpperCase(), object);
        }
    }


    /**
     * 根据对象名称获取对象
     *
     * @param objectName
     * @return
     */
    public static DataObject getObject(String objectName) {
        return objectMap.get(objectName.toUpperCase());
    }

    public static DataObject getTravelObject() {
        return travelObject;
    }

    public static DataObject getTravelScheduleObject() {
        return travelScheduleObject;
    }

    public static DataObject getTravelClaimObject() {
        return travelClaimObject;
    }

    public static DataObject getStaffObject() {
        return staffObject;
    }


    /**
     * 构建出差对象
     *
     * @return
     */
    private static DataObject buildTravelObject() {
        DataObject object = new DataObject("Travel", "OQL.TRV_TB");
        DataField field0 = new DataField("applyId", "APPLY_ID", new DataType(net.cf.form.engine.repository.data.value.DataType.INTEGER));
        field0.setAutoGenerate(true);

        DataField field1 = new DataField("userId", "USER_ID", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field2 = new DataField("startDate", "START_DATE", new DataType(net.cf.form.engine.repository.data.value.DataType.DATETIME));
        DataField field3 = new DataField("endDate", "END_DATE", new DataType(net.cf.form.engine.repository.data.value.DataType.DATETIME));
        DataField field4 = new DataField("reason", "REASON", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field5 = new DataField("attach", "ATTACH", new DataType(net.cf.form.engine.repository.data.value.DataType.OBJECT));
        DataField field6 = new DataField("status", "STATUS", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field7 = new DetailDataField("schedules", "TravelSchedule");

        object.addFields(Arrays.asList(field0, field1, field2, field3, field4, field5, field6, field7));
        addCommonFields(object);

        return object;
    }

    /**
     * 构建行程对象
     *
     * @return
     */
    private static DataObject buildTravelScheduleObject() {
        DataObject object = new DataObject("TravelSchedule", "OQL.TRV_SCHE_TB");
        DataField field0 = new DataField("scheduleId", "SCHE_ID", new DataType(net.cf.form.engine.repository.data.value.DataType.INTEGER));
        field0.setAutoGenerate(true);

        // 建立主子关联
        DataField field1 = new MasterDataField("travel", "APPLY_ID", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT), "Travel");
        DataField field2 = new DataField("startDate", "START_DATE", new DataType(net.cf.form.engine.repository.data.value.DataType.DATETIME));
        DataField field3 = new DataField("endDate", "END_DATE", new DataType(net.cf.form.engine.repository.data.value.DataType.DATETIME));
        DataField field4 = new DataField("destination", "DEST",  new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field5 = new DataField("todo", "TODO", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field6 = new DataField("status", "STATUS", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));

        object.addFields(Arrays.asList(field0, field1, field2, field3, field4, field5, field6));
        addCommonFields(object);

        return object;
    }

    /**
     * 构建行程对象
     *
     * @return
     */
    private static DataObject buildTravelClaimObject() {
        DataObject object = new DataObject("TravelClaim", "OQL.TRV_CLM_TB");
        DataField field0 = new DataField("claimId", "CLAIM_ID", new DataType(net.cf.form.engine.repository.data.value.DataType.INTEGER));
        field0.setAutoGenerate(true);

        // 建立关联关系
        DataField field1 = new LookupDataField("staff", "STA_ID", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT), "Staff");
        DataField field2 = new LookupDataField("schedules", "SCHE_IDS", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT, true), "TravelSchedule");
        DataField field3 = new DataField("status", "STATUS", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));

        object.addFields(Arrays.asList(field0, field1, field2, field3));
        addCommonFields(object);

        return object;
    }


    /**
     * 构建员工对象
     *
     * @return
     */
    private static DataObject buildStaffObject() {
        DataObject object = new DataObject("Staff", "OQL.STA_TB");
        DataField field0 = new DataField("staffId", "STA_ID", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        field0.setPrimary(true);
        DataField field1 = new DataField("staffName", "STA_NAME", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field2 = new DataField("status", "STATUS", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));

        object.addFields(Arrays.asList(field0, field1, field2));
        addCommonFields(object);

        return object;
    }

    /**
     * 添加公共字段
     *
     * @param object
     */
    private static void addCommonFields(DataObject object) {
        DataField field0 = new DataField("enterpriseKey", "ENTP_KEY", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field1 = new DataField("createdBy", "CREATED_BY", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field2 = new DataField("createdAt", "CREATED_AT", new DataType(net.cf.form.engine.repository.data.value.DataType.DATETIME));
        DataField field3 = new DataField("modifiedBy", "MODIFIED_BY", new DataType(net.cf.form.engine.repository.data.value.DataType.TEXT));
        DataField field4 = new DataField("modifiedAt", "MODIFIED_AT", new DataType(net.cf.form.engine.repository.data.value.DataType.DATETIME));
        object.addFields(Arrays.asList(field0, field1, field2, field3, field4));
    }
}
