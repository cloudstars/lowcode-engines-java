package io.github.cloudstars.lowcode.commons.cache.service;

import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {

    /**
     * 根据名称查询学生列表
     *
     * @param userName
     * @return
     */
    List<Student> queryByName(String userName);

    /**
     * 查询学生
     *
     * @param id
     * @return
     */
    Student get(String id);

    /**
     * 创建学生
     *
     * @param student
     */
    void create(Student student);

    /**
     * 更新学生
     *
     * @param student
     * @return
     */
    void modify(Student student);

    /**
     * 删除学生
     *
     * @param key
     */
    void remove(String key);
}
