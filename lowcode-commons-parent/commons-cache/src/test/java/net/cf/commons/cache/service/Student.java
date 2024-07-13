package net.cf.commons.cache.service;

import net.cf.commons.cache.repository.Criteria;
import net.cf.commons.cache.repository.StudentCriteria;

/**
 * 学生实体类
 */
public class Student implements Criteria {

    /**
     * 学生标识
     */
    private String key;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生年龄
     */
    private int age;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static StudentCriteria createCriteria() {
        return new StudentCriteria();
    }

}
