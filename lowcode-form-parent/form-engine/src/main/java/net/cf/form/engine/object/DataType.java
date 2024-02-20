package net.cf.form.engine.object;

/**
 * 数据类型
 *
 * @author clouds
 */
public enum DataType {

    STRING("字符串"),
    INTEGER("整数"),
    DECIMAL("小数"),
    BOOLEAN("布尔"),
    DATE("日期"),
    TIME("时间"),
    OBJECT("对象");

    private String desc;

    DataType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
