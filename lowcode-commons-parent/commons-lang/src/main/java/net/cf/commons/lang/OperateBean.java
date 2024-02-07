package net.cf.commons.lang;

import java.util.Date;

/**
 * 操作信息Bean
 *
 * @author clouds
 */
public class OperateBean {

    /**
     * 操作人
     */
    private Operator operator;

    /**
     * 操作时间
     */
    private Date time;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
