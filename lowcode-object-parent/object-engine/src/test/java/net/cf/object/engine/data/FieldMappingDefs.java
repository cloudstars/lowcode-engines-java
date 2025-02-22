package net.cf.object.engine.data;

import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.ValueTypeImpl;

import java.util.Arrays;
import java.util.List;

/**
 * 字段映射定义
 *
 * @author clouds
 */
public final class FieldMappingDefs {

    private FieldMappingDefs() {
    }

    public static List<FieldMapping> getDefList1() {
        FieldMapping valueDef1 = new FieldMapping("f1", DataType.NUMBER, true);
        FieldMapping valueDef2 = new FieldMapping("f2", DataType.OBJECT, true);
        FieldMapping valueDef3 = new FieldMapping("f3", DataType.DATE);
        FieldMapping valueDef4 = new FieldMapping("f4", DataType.STRING);
        FieldMapping valueDef5 = new FieldMapping("f5", DataType.NUMBER);
        FieldMapping valueDef6 = new FieldMapping("f6", DataType.DATE);
        List<FieldMapping> valueDefs = Arrays.asList(valueDef1, valueDef2, valueDef3, valueDef4, valueDef5, valueDef6);
        return valueDefs;
    }


    public static List<FieldMapping> getDefList2() {
        FieldMapping valueDef1 = new FieldMapping("f1");
        valueDef1.setValueType(new ValueTypeImpl(DataType.OBJECT, true));
        valueDef1.addSubFields(Arrays.asList(
                new FieldMapping("f11", "f1.f11", DataType.STRING),
                new FieldMapping("f12", "f1.f12", DataType.NUMBER),
                new FieldMapping("f13", "f1.f13", DataType.NUMBER))
        );
        FieldMapping valueDef2 = new FieldMapping("f2");
        valueDef2.setValueType(new ValueTypeImpl(DataType.OBJECT, true));
        FieldMapping valueDef3 = new FieldMapping("f3", DataType.OBJECT);
        valueDef3.addSubFields(Arrays.asList(
                new FieldMapping("f31", "f3.f31", DataType.STRING),
                new FieldMapping("f32", "f3.f32", DataType.NUMBER),
                new FieldMapping("f33", "f3.f33", DataType.NUMBER))
        );
        FieldMapping valueDef4 = new FieldMapping("f4", DataType.STRING);
        FieldMapping valueDef5 = new FieldMapping("f5", DataType.NUMBER);
        FieldMapping valueDef6 = new FieldMapping("f6", DataType.DATE);
        List<FieldMapping> valueDefs = Arrays.asList(valueDef1, valueDef2, valueDef3, valueDef4, valueDef5, valueDef6);

        return valueDefs;
    }

    public static List<FieldMapping> getDefList3() {
        FieldMapping valueDef31 = new FieldMapping("f31", DataType.OBJECT, true);
        valueDef31.addSubFields(Arrays.asList(
                new FieldMapping("f311", "f31.f311", DataType.STRING),
                new FieldMapping("f312", "f31.f312", DataType.NUMBER, true),
                new FieldMapping("f313", "f31.f313", DataType.NUMBER))
        );
        List<FieldMapping> valueDefs = Arrays.asList(valueDef31);

        return valueDefs;
    }

    public static List<FieldMapping> getDefList4() {
        FieldMapping valueDef3 = new FieldMapping("f3", DataType.OBJECT);
        FieldMapping valueDef31 = new FieldMapping("f31", "f3.f31", DataType.OBJECT, true);
        valueDef31.addSubFields(Arrays.asList(
                new FieldMapping("f311", "f3.f31.f311", DataType.STRING),
                new FieldMapping("f312", "f3.f31.f312", DataType.NUMBER, true),
                new FieldMapping("f313", "f3.f31.f313", DataType.NUMBER))
        );
        valueDef3.addSubFields(Arrays.asList(valueDef31));
        List<FieldMapping> valueDefs = Arrays.asList(valueDef3);

        return valueDefs;
    }


    public static List<FieldMapping> getDefList5() {
        FieldMapping valueDef1 = new FieldMapping("f1");
        valueDef1.setValueType(new ValueTypeImpl(DataType.OBJECT, true));
        valueDef1.addSubFields(Arrays.asList(
                new FieldMapping("f11", "f1.f11", DataType.STRING),
                new FieldMapping("f12", "f1.f12", DataType.NUMBER),
                new FieldMapping("f13", "f1.f13", DataType.NUMBER))
        );
        FieldMapping valueDef2 = new FieldMapping("f2");
        valueDef2.setValueType(new ValueTypeImpl(DataType.OBJECT, true));
        valueDef2.addSubFields(Arrays.asList(
                new FieldMapping("f21", "f2.f21", DataType.STRING),
                new FieldMapping("f22", "f2.f22", DataType.NUMBER),
                new FieldMapping("f23", "f2.f23", DataType.NUMBER))
        );
        FieldMapping valueDef3 = new FieldMapping("f3", DataType.OBJECT);
        FieldMapping valueDef31 = new FieldMapping("f31", "f3.f31", DataType.OBJECT, true);
        valueDef31.addSubFields(Arrays.asList(
                new FieldMapping("f311", "f3.f31.f311", DataType.STRING),
                new FieldMapping("f312", "f3.f31.f312", DataType.NUMBER, true),
                new FieldMapping("f313", "f3.f31.f313", DataType.NUMBER))
        );
        FieldMapping valueDef32 = new FieldMapping("f32", "f3.f32", DataType.OBJECT);
        valueDef32.addSubFields(Arrays.asList(
                new FieldMapping("f321", "f3.f32.f321", DataType.STRING),
                new FieldMapping("f322", "f3.f32.f322", DataType.NUMBER, true),
                new FieldMapping("f323", "f3.f32.f323", DataType.NUMBER))
        );
        FieldMapping valueDef33 = new FieldMapping("f33", "f3.f33", DataType.NUMBER);
        valueDef3.addSubFields(Arrays.asList(valueDef31, valueDef32, valueDef33));
        FieldMapping valueDef4 = new FieldMapping("f4", DataType.STRING);
        FieldMapping valueDef5 = new FieldMapping("f5", DataType.NUMBER);
        FieldMapping valueDef6 = new FieldMapping("f6", DataType.DATE);
        List<FieldMapping> valueDefs = Arrays.asList(valueDef1, valueDef2, valueDef3, valueDef4, valueDef5, valueDef6);

        return valueDefs;
    }

    public static List<FieldMapping> getDefList6() {
        FieldMapping valueDef1 = new FieldMapping("f1", DataType.STRING);
        FieldMapping valueDef2 = new FieldMapping("f2", DataType.STRING);
        FieldMapping valueDef3 = new FieldMapping("f3", DataType.OBJECT, true);
        FieldMapping valueDef31 = new FieldMapping("f31", "f3.f31", DataType.OBJECT, true);
        valueDef31.addSubFields(Arrays.asList(
                new FieldMapping("f311", "f3.f31.f311", DataType.STRING),
                new FieldMapping("f312", "f3.f31.f312", DataType.NUMBER, true),
                new FieldMapping("f313", "f3.f31.f313", DataType.NUMBER))
        );
        FieldMapping valueDef32 = new FieldMapping("f32", "f3.f32", DataType.OBJECT);
        valueDef32.addSubFields(Arrays.asList(
                new FieldMapping("f321", "f3.f32.f321", DataType.STRING),
                new FieldMapping("f322", "f3.f32.f322", DataType.NUMBER),
                new FieldMapping("f323", "f3.f32.f323", DataType.NUMBER))
        );
        FieldMapping valueDef33 = new FieldMapping("f33", "f3.f33", DataType.NUMBER);
        valueDef3.addSubFields(Arrays.asList(valueDef31, valueDef32, valueDef33));
        List<FieldMapping> valueDefs = Arrays.asList(valueDef1, valueDef2, valueDef3);

        return valueDefs;
    }

}
