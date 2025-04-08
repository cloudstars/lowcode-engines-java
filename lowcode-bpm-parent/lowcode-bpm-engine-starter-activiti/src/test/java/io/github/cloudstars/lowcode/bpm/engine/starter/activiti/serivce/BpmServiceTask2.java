package io.github.cloudstars.lowcode.bpm.engine.starter.activiti.serivce;

import io.github.cloudstars.lowcode.bpm.vendor.service.BpmServiceTask;
import io.github.cloudstars.lowcode.bpm.vendor.service.BpmDelegateExecution;

public class BpmServiceTask2 implements BpmServiceTask {

    @Override
    public void executeTask(BpmDelegateExecution execution) {
        System.out.println("ServiceTask2 is running...");
    }

}
