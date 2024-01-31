package net.cf.form.engine.repository.testcases.statement.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 *
 * @author clouds
 */
public final class DateUtils {

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    /**
     * 对日期对象作格式化成"yyyy-MM-dd hh:mm:ss.SSS"这种DB识别的格式
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format.format(date);
    }

    /**
     * 对日期对象作格式化成"yyyy-MM-dd hh:mm:ss"这种DB识别的格式
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return dateTimeFormat.format(date);
    }

    /**
     * 对日期对象作格式化成"yyyy-MM-dd"这种DB识别的格式
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }
}
