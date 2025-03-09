package io.github.cloudstars.lowcode.commons.lang.type;

import io.github.cloudstars.lowcode.commons.lang.DataType;
import io.github.cloudstars.lowcode.commons.lang.value.ObjectValueType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * 文件数据格式测试类
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class FileValueTypeTest {

    @Test
    public void testDefault() {
        FileValueType type = new FileValueType();
        Assert.assertEquals(DataType.OBJECT, type.getDataType());
        Assert.assertFalse(type.isArray());

        List<ObjectValueType.ObjectProperty> props = type.getProperties();
        Assert.assertEquals(2, props.size());
        ObjectValueType.ObjectProperty keyProp = props.get(0);
        ObjectValueType.ObjectProperty nameProp = props.get(1);
        Assert.assertEquals("key", keyProp.getName());
        Assert.assertEquals("name", nameProp.getName());
        Assert.assertEquals(DataType.TEXT, keyProp.getValueType().getDataType());
        Assert.assertFalse(nameProp.getValueType().isArray());
        Assert.assertEquals(DataType.TEXT, nameProp.getValueType().getDataType());
        Assert.assertFalse(nameProp.getValueType().isArray());
    }

    @Test
    public void testCustom() {
        FileValueType type = new FileValueType(true); // 多选
        Assert.assertEquals(DataType.OBJECT, type.getDataType());
        Assert.assertTrue(type.isArray());

        List<ObjectValueType.ObjectProperty> props = type.getProperties();
        Assert.assertEquals(2, props.size());
        ObjectValueType.ObjectProperty keyProp = props.get(0);
        ObjectValueType.ObjectProperty nameProp = props.get(1);
        Assert.assertEquals("key", keyProp.getName());
        Assert.assertEquals("name", nameProp.getName());
        Assert.assertEquals(DataType.TEXT, keyProp.getValueType().getDataType());
        Assert.assertFalse(nameProp.getValueType().isArray());
        Assert.assertEquals(DataType.TEXT, nameProp.getValueType().getDataType());
        Assert.assertFalse(nameProp.getValueType().isArray());
    }

}
