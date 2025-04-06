package io.github.cloudstars.lowcode.bpm.engine.starter.activiti.serivce;

import io.github.cloudstars.lowcode.bpm.vendor.service.DelegateExecution;
import io.github.cloudstars.lowcode.bpm.vendor.service.BpmServiceTask;

public class BpmServiceTask1 implements BpmServiceTask {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("ServiceTask1 is running...");
    }

}
