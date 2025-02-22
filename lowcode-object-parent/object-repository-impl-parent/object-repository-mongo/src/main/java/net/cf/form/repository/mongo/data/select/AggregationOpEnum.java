package net.cf.form.repository.mongo.data.select;

import java.util.LinkedList;

/**
 * mongo aggregation operation 类型
 */
public enum AggregationOpEnum {
    MATCH,
    LOOKUP,
    ADD_FIELD,
    GROUP_BY,
    ORDER,
    LIMIT,
    DISTINCT,
    PROJECT;

    /**
     * @return
     */
    public static LinkedList<AggregationOpEnum> getDefaultOperations() {
        LinkedList operations = new LinkedList();
        operations.add(MATCH);
        operations.add(ADD_FIELD);
        operations.add(LOOKUP);
        operations.add(GROUP_BY);
        operations.add(ORDER);
        operations.add(DISTINCT);
        operations.add(LIMIT);
        operations.add(PROJECT);
        return operations;
    }

    /**
     * 移动一个符号到目标后
     *
     * @param operations
     * @param target
     * @param source
     */
    public static void moveFirstAfter(LinkedList<AggregationOpEnum> operations, AggregationOpEnum target, AggregationOpEnum source) {
        if (!operations.contains(target)) {
            return;
        }
        int sourceIndex = operations.indexOf(source);
        if (sourceIndex >= 0) {
            operations.remove(sourceIndex);
        }
        int targetIndex = operations.indexOf(target);


        if (targetIndex == operations.size() - 1) {
            operations.add(source);
        } else {
            operations.add(targetIndex + 1, source);
        }
    }

    /**
     * 移动一个符号到目标前
     *
     * @param operations
     * @param target
     * @param source
     */
    public static void moveFirstBefore(LinkedList<AggregationOpEnum> operations, AggregationOpEnum target, AggregationOpEnum source) {
        if (!operations.contains(target)) {
            return;
        }
        int sourceIndex = operations.indexOf(source);
        if (sourceIndex >= 0) {
            operations.remove(sourceIndex);
        }
        int targetIndex = operations.indexOf(target);
        operations.add(targetIndex, source);
    }

}
