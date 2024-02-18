package net.cf.form.engine.object;

/**
 * 数据类型
 *
 * @author clouds
 */
public enum DataType {

    String("字符串"),
    Integer("整数"),
    Decimal("小数"),
    Boolean("布尔"),
    Date("日期"),
    Time("时间"),
    Object("对象");

    private String desc;

    DataType(String desc) {
        this.desc = desc;
    }

    public java.lang.String getDesc() {
        return desc;
    }
}
