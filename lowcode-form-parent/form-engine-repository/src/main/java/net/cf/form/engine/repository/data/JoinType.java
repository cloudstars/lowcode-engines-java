package net.cf.form.engine.repository.data;

/**
 * 联结的类型
 *
 * @author clouds
 */
@Deprecated
public enum JoinType {

    DETAIL("主表关联子表查询"),
    MASTER("子表关联主表查询"),
    LOOKUP("本表关联另一个表查询");

    private String desc;

    JoinType(String desc) {
        this.desc = desc;
    }
}
