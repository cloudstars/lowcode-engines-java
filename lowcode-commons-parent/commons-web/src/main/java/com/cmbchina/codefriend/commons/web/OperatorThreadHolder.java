package com.cmbchina.codefriend.commons.web;

import com.cmbchina.codefriend.commons.lang.Operator;

/**
 * 操作人信息线程执有器
 *
 * @author 80274507
 */
public final class OperatorThreadHolder {

    private final static InheritableThreadLocal<Operator> operatorThreadLocal = new InheritableThreadLocal<>();

    public static Operator get() {
        return new Operator("testEnterpriseKey", "testUserKey", "testUserName");
    }
}
