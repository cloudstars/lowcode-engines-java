package net.cf.form.repository.mongo.data;

public enum PositionEnum {
    /**
     * expr所处的位置
     * default 默认，除下列之外的位置
     * param 参数
     * where where位置
     * having having位置
     * value insert和update位置
     * join join位置
     */
    DEFAULT,
    PARAM,
    WHERE,
    HAVING,
    VALUE,
    JOIN;
}
