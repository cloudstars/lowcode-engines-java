package net.cf.commons.test.util;

@FunctionalInterface
public interface ObjectComparator {

    /**
     * 比较两个对象的值是否相等，前提是两个对象都非空
     *
     * @param source
     * @param target
     * @return
     */
    boolean equalsNullSafe(Object source, Object target);

}
