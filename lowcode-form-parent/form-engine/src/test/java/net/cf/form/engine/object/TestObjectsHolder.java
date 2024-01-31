package net.cf.form.engine.object;

import net.cf.form.engine.field.XDetailFieldBak;
import net.cf.form.engine.field.XLookupFieldBak;
import net.cf.form.engine.field.XMasterFieldBak;
import net.cf.form.engine.repository.data.DbType;
import net.cf.form.engine.XObjectImpl;
import net.cf.form.engine.field.XFieldImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象构建器
 *
 * @author clouds
 */
@Deprecated
public final class TestObjectsHolder {

    /**
     * 对象映射表
     */
    private static final Map<String, XObject> objectMap = new HashMap<>();

    private static final XObject travelObject;

    private static final XObject travelScheduleObject;

    private static final XObject travelClaimObject;

    private static final XObject staffObject;

    static {
        // 创建出差对象
        travelObject = buildTravelObject();
        // 创建出差行程对象
        travelScheduleObject = buildTravelScheduleObject();
        // 构建出差报销对象
        travelClaimObject = buildTravelClaimObject();
        // 构建员工对象
        staffObject = buildStaffObject();

        List<XObject> objects = Arrays.asList(travelObject, travelScheduleObject, travelClaimObject, staffObject);
        for (XObject object : objects) {
            objectMap.put(object.getName().toUpperCase(), object);
        }
    }


    /**
     * 根据字段类型名称获取字段类型
     *
     * @param objectName
     * @return
     */
    public static XObject getObject(String objectName) {
        return objectMap.get(objectName.toUpperCase());
    }

    public static XObject getTravelObject() {
        return travelObject;
    }

    public static XObject getTravelScheduleObject() {
        return travelScheduleObject;
    }

    public static XObject getTravelClaimObject() {
        return travelClaimObject;
    }

    public static XObject getStaffObject() {
        return staffObject;
    }

    /**
     * 构建出差对象（含子对象行程）
     *
     * @return
     */
    private static XObject buildTravelObject() {
        XObjectImpl object = new XObjectImpl("Travel", "出差申请");
        object.setMultiTenant(true);
        object.setPersistence(true);
        object.setDbType(DbType.MYSQL);
        object.setDataSourceKey("default");
        object.setTableName("travel");

        XFieldImpl field0 = new XFieldImpl(TestFieldTypesHolder.getAutoIdFieldType(), "applyId");
        field0.setTitle("申请单ID");
        field0.setPersistence(true);
        field0.setColumnName("APPLY_ID");

        XFieldImpl field1 = new XFieldImpl(TestFieldTypesHolder.getUserFieldType(), "userId");
        field1.setTitle("申请人ID");
        field1.setPersistence(true);
        field1.setColumnName("USER_ID");

        XFieldImpl field2 = new XFieldImpl(TestFieldTypesHolder.getDateFieldType(), "startDate");
        field2.setTitle("开始日期");
        field2.setPersistence(true);
        field2.setColumnName("START_DATE");
        field2.setAttributeValue("format", "yyyy-MM-dd");

        XFieldImpl field3 = new XFieldImpl(TestFieldTypesHolder.getDateFieldType(), "endDate");
        field3.setTitle("结束日期");
        field3.setPersistence(true);
        field3.setColumnName("END_DATE");
        field3.setAttributeValue("format", "yyyy-MM-dd");

        XFieldImpl field4 = new XFieldImpl(TestFieldTypesHolder.getTextFieldType(), "reason");
        field4.setTitle("出差原因");
        field4.setPersistence(true);
        field4.setColumnName("REASON");
        field4.setAttributeValue("multiLine", true);
        field4.setAttributeValue("maxLength", 1000);

        XFieldImpl field5 = new XFieldImpl(TestFieldTypesHolder.getAttachFieldType(), "attach");
        field5.setTitle("附件");
        field5.setPersistence(true);
        field5.setColumnName("ATTACH");
        field5.setAttributeValue("maxSize", 1);

        XFieldImpl field6 = new XFieldImpl(TestFieldTypesHolder.getOptionsFieldType(), "status");
        field6.setTitle("状态");
        field6.setPersistence(true);
        field6.setColumnName("STATUS");
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("1", "通过");
        statusMap.put("2", "驳回");
        field6.setAttributeValue("options", statusMap);

        XFieldImpl field7 = new XDetailFieldBak("schedules", "TravelSchedule");
        field7.setTitle("行程");
        field7.setPersistence(false);

        XFieldImpl field8 = new XFieldImpl(TestFieldTypesHolder.getSequenceFieldType(), "applyId1");
        field8.setTitle("申请单号");
        field8.setPersistence(true);

        object.addFields(Arrays.asList(field0, field1, field2, field3, field4, field5, field6, field7, field8));
        addCommonFields(object);

        return object;
    }

    /**
     * 构建出差行程对象
     *
     * @return
     */
    private static XObject buildTravelScheduleObject() {
        XObjectImpl object = new XObjectImpl("TravelSchedule", "出差行程");
        object.setMultiTenant(true);
        object.setPersistence(true);
        object.setDbType(DbType.MYSQL);
        object.setDataSourceKey("default");
        object.setTableName("TRV_SCHE_TB");

        XFieldImpl field0 = new XFieldImpl(TestFieldTypesHolder.getAutoIdFieldType(), "scheduleId");
        field0.setTitle("行程ID");
        field0.setPersistence(true);
        field0.setColumnName("SCHE_ID");

        XFieldImpl field1 = new XMasterFieldBak("apply", "Travel");
        field1.setTitle("申请单ID");
        field1.setPersistence(true);
        field1.setColumnName("APPLY_ID");

        XFieldImpl field2 = new XFieldImpl(TestFieldTypesHolder.getTextFieldType(), "destination");
        field2.setTitle("目的地");
        field2.setPersistence(true);
        field2.setColumnName("DEST");
        field2.setAttributeValue("maxLength", 200);

        XFieldImpl field3 = new XFieldImpl(TestFieldTypesHolder.getTextFieldType(), "todo");
        field3.setTitle("待办事项");
        field3.setPersistence(true);
        field3.setColumnName("TODO");
        field3.setAttributeValue("format", "yyyy-MM-dd");

        XFieldImpl field4 = new XFieldImpl(TestFieldTypesHolder.getOptionsFieldType(), "status");
        field4.setTitle("状态");
        field4.setPersistence(true);
        field4.setColumnName("STATUS");
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("1", "未开始");
        statusMap.put("2", "进行中");
        statusMap.put("3", "已结束");
        field4.setAttributeValue("options", statusMap);

        object.addFields(Arrays.asList(field0, field1, field2, field3, field4));
        addCommonFields(object);

        return object;
    }

    /**
     * 构建报销对象（相关表是出差对象）
     *
     * @return
     */
    private static XObject buildTravelClaimObject() {
        XObjectImpl object = new XObjectImpl("TravelClaim", "出差报销申请");
        object.setMultiTenant(true);
        object.setPersistence(true);
        object.setDbType(DbType.MYSQL);
        object.setDataSourceKey("default");
        object.setTableName("TRV_CLM_TB");

        XFieldImpl field0 = new XFieldImpl(TestFieldTypesHolder.getAutoIdFieldType(), "claimId");
        field0.setTitle("报销ID");
        field0.setPersistence(true);
        field0.setColumnName("CLAIM_ID");

        XFieldImpl field1 = new XLookupFieldBak("schedules", "TravelSchedule");
        field1.setTitle("行程ID列表");
        field1.setPersistence(true);
        field1.setColumnName("SCHE_IDS");

        XFieldImpl field2 = new XFieldImpl(TestFieldTypesHolder.getOptionsFieldType(), "status");
        field2.setTitle("状态");
        field2.setPersistence(true);
        field2.setColumnName("STATUS");
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("1", "未报销");
        statusMap.put("2", "已报销");
        field2.setAttributeValue("options", statusMap);

        object.addFields(Arrays.asList(field0, field1, field2));
        addCommonFields(object);

        return object;
    }

    private static XObject buildStaffObject() {
        XObjectImpl object = new XObjectImpl("Staff", "员工");
        object.setMultiTenant(true);
        object.setPersistence(true);
        object.setDbType(DbType.MYSQL);
        object.setDataSourceKey("default");
        object.setTableName("STA_TB");

        XFieldImpl field0 = new XFieldImpl(TestFieldTypesHolder.getTextFieldType(), "staffId");
        field0.setTitle("员工ID");
        field0.setPersistence(true);
        field0.setColumnName("STA_ID");

        XFieldImpl field1 = new XFieldImpl(TestFieldTypesHolder.getTextFieldType(), "staffName");
        field1.setTitle("员工姓名");
        field1.setPersistence(true);
        field1.setColumnName("STA_NAME");

        XFieldImpl field2 = new XFieldImpl(TestFieldTypesHolder.getOptionsFieldType(), "status");
        field2.setTitle("状态");
        field2.setPersistence(true);
        field2.setColumnName("STATUS");
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("1", "正常");
        statusMap.put("2", "注销");
        field2.setAttributeValue("options", statusMap);

        object.addFields(Arrays.asList(field0, field1, field2));
        addCommonFields(object);

        return object;
    }

    /**
     * 添加公共字段
     *
     * @param object
     */
    private static void addCommonFields(XObjectImpl object) {
        if (object.isMultiTenant()) {
            XFieldImpl field0 = new XFieldImpl(TestFieldTypesHolder.getUserFieldType(), "enterpriseKey");
            field0.setTitle("企业编号");
            field0.setPersistence(true);
            field0.setColumnName("ENTP_KEY");
            field0.setBuiltin(true);

            object.addField(field0);
        }

        XFieldImpl field1 = new XFieldImpl(TestFieldTypesHolder.getUserFieldType(), "createdBy");
        field1.setTitle( "创建人用户号");
        field1.setPersistence(true);
        field1.setColumnName("CREATED_BY");
        field1.setBuiltin(true);

        XFieldImpl field2 = new XFieldImpl(TestFieldTypesHolder.getDateFieldType(), "createdAt");
        field2.setTitle( "创建时间");
        field2.setPersistence(true);
        field2.setColumnName("CREATED_AT");
        field2.setBuiltin(true);

        XFieldImpl field3 = new XFieldImpl(TestFieldTypesHolder.getUserFieldType(), "modifiedBy");
        field3.setTitle( "修改人用户号");
        field3.setPersistence(true);
        field3.setColumnName("MODIFIER_BY");
        field3.setBuiltin(true);

        XFieldImpl field4 = new XFieldImpl(TestFieldTypesHolder.getDateFieldType(), "modifiedAt");
        field4.setTitle( "修改时间");
        field4.setPersistence(true);
        field4.setColumnName("MODIFIED_AT");
        field4.setBuiltin(true);

        object.addFields(Arrays.asList(field1, field2, field3, field4));
    }
}

