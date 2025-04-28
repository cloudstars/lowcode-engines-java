package io.github.cloudstars.lowcode.commons.cache.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.cloudstars.lowcode.commons.cache.repository.StudentCriteria;
import io.github.cloudstars.lowcode.commons.cache.repository.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@CacheConfig(cacheNames = "STUDENT")
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;

    @Caching(
            cacheable = {@Cacheable(key = "#name")}
    )
    public List<Student> queryByName(String name) {
        log.info("根据用户名模糊查询：key = {}", name);
        StudentCriteria criteria = Student.createCriteria();
        criteria.andUserNameLike(name);
        List<Student> students = studentMapper.selectList(criteria);
        log.info("查询后更新缓存，数据 = ", JSONArray.toJSONString(students));
        return students;
    }

    /**
     * Cacheable
     *
     * @param key
     * @return key = "#root.methodName+'['+#key+']'"
     */
    @Cacheable(key = "#key")
    @Override
    public Student get(String key) {
        log.info("查询：key = {}", key);
        Student student = studentMapper.selectByKey(key);
        log.info("查询后更新缓存，数据 = ", JSONObject.toJSONString(student));
        return student;
    }

    @Override
    public void create(Student student) {
        log.info("创建：内容 = ", JSONObject.toJSONString(student));
        this.studentMapper.insert(student);
        log.info("插入不更新缓存, key = {}", student.getKey());
    }

    @CacheEvict(key = "#student.key")
    @Override
    public void modify(Student student) {
        log.info("更新：内容 = ", JSONObject.toJSONString(student));
        studentMapper.updateByKey(student);
        log.info("更新后清除缓存：key = {}", student.getKey());
    }

    @CacheEvict(key = "#key")
    @Override
    public void remove(String key) {
        log.info("删除：key = {}", key);
        studentMapper.deleteByKey(key);
        log.info("删除后更新缓存，key = {}", key);
    }

}