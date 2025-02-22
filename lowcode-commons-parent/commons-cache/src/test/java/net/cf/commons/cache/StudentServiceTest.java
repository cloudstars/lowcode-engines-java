package net.cf.commons.cache;

import net.cf.commons.cache.service.Student;
import net.cf.commons.cache.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheTestApplication.class)
public class StudentServiceTest {

    @Resource
    private StudentService service;

    @Resource
    private CacheHelper cacheHelper;

    @Test
    public void testGet() {
        String key = "zs";

        // 先删除缓存
        this.cacheHelper.deleteKey(CacheResourceTypeEnum.STUDENT, key);
        // 缓存中不存在
        Assert.assertFalse(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));

        // 查询后缓存中存在
        Student stu = this.service.get("zs");
        Assert.assertTrue(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));

        assert (stu != null);
        Assert.assertEquals(key, stu.getKey());
        Assert.assertEquals("张三", stu.getName());
        Assert.assertEquals(10, stu.getAge());
    }

    @Test
    public void testQueryFormName() {
        String key = "张三";
        // 先删除缓存
        this.cacheHelper.deleteKey(CacheResourceTypeEnum.STUDENT, key);
        // 缓存中不存在
        Assert.assertFalse(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));

        // 查询后缓存中存在
        List<Student> students = this.service.queryByName(key);
        Assert.assertTrue(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));

        assert (students.size() == 3);
    }

    @Test
    public void testInsert() {
        String key = "test";

        // 先删除缓存
        this.cacheHelper.deleteKey(CacheResourceTypeEnum.STUDENT, key);
        // 缓存中不存在
        Assert.assertFalse(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));

        Student student = new Student();
        student.setKey(key);
        student.setName("测试用户");
        student.setAge(20);
        this.service.create(student);

        // 插入后缓存也不存在
        Assert.assertFalse(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));
    }

    @Test
    public void testModify() {
        String key = "zs";

        // 查询后断言缓存中存在
        this.service.get(key);
        Assert.assertTrue(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));

        // 断言修改后缓存不存在
        Student student = new Student();
        student.setKey(key);
        student.setName("修改用户名");
        student.setAge(20);
        this.service.modify(student);
        Assert.assertFalse(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));
    }

    @Test
    public void testRemove() {
        String key = "zs";

        // 查询后断言缓存中存在
        this.service.get(key);
        Assert.assertTrue(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));

        // 断言修改后缓存不存在
        this.service.remove(key);
        Assert.assertFalse(this.cacheHelper.existsKey(CacheResourceTypeEnum.STUDENT, key));
    }

}
