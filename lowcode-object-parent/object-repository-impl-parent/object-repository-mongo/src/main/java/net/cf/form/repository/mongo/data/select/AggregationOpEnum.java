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
        operations.add(LIMIT);
        operations.add(PROJECT);
        return operations;
    }
}
