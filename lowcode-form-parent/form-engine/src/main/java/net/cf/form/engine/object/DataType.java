package net.cf.form.engine.object;

/**
 * 数据类型
 *
 * @author clouds
 */
public enum DataType {

    /**
     * 字符串
     */
    STRING,
    /**
     * 数字，含整数和小字
     */
    NUMBER,
    /**
     * 布尔
     */
    BOOLEAN,
    /**
     * 日期，含时间戳、日期、时间
     */
    DATE,
    /**
     * 上面的4种类型为基础类型时，Object为非基础类型
     */
    OBJECT;

}
