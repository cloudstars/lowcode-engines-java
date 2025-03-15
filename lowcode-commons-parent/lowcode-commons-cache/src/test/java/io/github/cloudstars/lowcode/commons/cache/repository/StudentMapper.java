package io.github.cloudstars.lowcode.commons.cache.repository;

import io.github.cloudstars.lowcode.commons.cache.service.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> selectList(StudentCriteria criteria);

    Student selectByKey(String key);

    int insert(Student student);

    int updateByKey(Student student);

    int deleteByKey(String key);

}
