package net.cf.form.engine.object;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 测试对象
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
@Deprecated
public class ObjectTest {

    @Test
    public void testTravelObject() {
        XObject object = TestObjectsHolder.getTravelObject();
        Assert.assertTrue(object != null && "Travel".equals(object.getName()));
        Assert.assertTrue(object.getFields().size() == 13);
    }

    @Test
    public void testTravelScheduleObject() {
        XObject object = TestObjectsHolder.getTravelScheduleObject();
        Assert.assertTrue(object != null && "TravelSchedule".equals(object.getName()));
        Assert.assertTrue(object.getFields().size() == 10);
    }

    @Test
    public void testTravelClaimObject() {
        XObject object = TestObjectsHolder.getTravelClaimObject();
        Assert.assertTrue(object != null && "TravelClaim".equals(object.getName()));
        Assert.assertTrue(object.getFields().size() == 8);
    }

    @Test
    public void testTravelStaffObject() {
        XObject object = TestObjectsHolder.getStaffObject();
        Assert.assertTrue(object != null && "Staff".equals(object.getName()));
        Assert.assertTrue(object.getFields().size() == 8);
    }
}
