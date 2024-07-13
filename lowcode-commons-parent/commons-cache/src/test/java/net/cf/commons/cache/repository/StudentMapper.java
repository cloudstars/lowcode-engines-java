package net.cf.commons.cache.repository;

import net.cf.commons.cache.service.Student;
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
