package net.cf.commons.cache.repository;

/**
 * 学生查询 Bean
 */
public class StudentCriteria implements Criteria {

    private String name;

    /**
     * 添加名称查询条件
     *
     * @param name
     * @return
     */
    public StudentCriteria andUserNameLike(String name) {
        this.setName(name);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
