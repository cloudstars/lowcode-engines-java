package io.github.cloudstars.lowcode.commons.data.value.custom;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 选项数据格式测试类
 */
@RunWith(JUnit4.class)
public class OptionsValueTypeTest {

    /*@Test
    public void testDefault() {
        // 定义一个默认的选项数据格
        OptionsValueType type = new OptionsValueType();

        Assert.assertEquals(DataType.TEXT, type.getDataType());
        Assert.assertFalse(type.isArray());
        Assert.assertNull(type.getProperties());
    }

    @Test
    public void testCustom() {
        // 定义了一个labelField为lb，valueField为va，并且冗余存储的数据类型是数字的选项数据格式
        OptionsValueType type = new OptionsValueType("lb", "va", OptionsValueType.OptionDataType.NUMBER);
        type.setArray(true); // 多选

        Assert.assertEquals(DataType.OBJECT, type.getDataType());
        Assert.assertTrue(type.isArray());
        List<ObjectValueType.ObjectProperty> props = type.getProperties();
        Assert.assertEquals(2, props.size());
        ObjectValueType.ObjectProperty labelProp = props.get(0);
        ObjectValueType.ObjectProperty valueProp = props.get(1);
        Assert.assertEquals("lb", labelProp.getName());
        Assert.assertEquals("va", valueProp.getName());
        Assert.assertEquals(DataType.NUMBER, labelProp.getValueType().getDataType());
        Assert.assertFalse(valueProp.getValueType().isArray());
        Assert.assertEquals(DataType.NUMBER, valueProp.getValueType().getDataType());
        Assert.assertFalse(valueProp.getValueType().isArray());
    }*/

}
