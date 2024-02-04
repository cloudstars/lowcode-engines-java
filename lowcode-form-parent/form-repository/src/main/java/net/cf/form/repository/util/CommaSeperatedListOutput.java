package net.cf.form.repository.util;

import java.io.IOException;
import java.util.List;

/**
 * 以逗号隔开输出一个列表
 *
 * @author clouds
 */
public final class CommaSeperatedListOutput {

    /**
     * 输出一个列表
     *
     * @param items
     * @param output
     */
    public static <T> void output(Appendable appender, List<T> items, OutputFunction<T> output) {
        int i = 0;
        for (T item : items) {
            if (i++ > 0) {
                // 打印一个逗号
                try {
                    appender.append(", ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            output.print(item);
        }
    }
}
