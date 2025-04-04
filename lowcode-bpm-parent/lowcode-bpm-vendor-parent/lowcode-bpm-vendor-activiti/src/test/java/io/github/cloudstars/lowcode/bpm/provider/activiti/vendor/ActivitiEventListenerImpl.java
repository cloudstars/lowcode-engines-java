package io.github.cloudstars.lowcode.bpm.provider.activiti.vendor;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

import javax.annotation.Resource;

public class ActivitiEventListenerImpl implements ActivitiEventListener {

    @Resource
    private RuntimeService runtimeService;

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        switch (activitiEvent.getType()) {
            case PROCESS_COMPLETED:
                System.out.println("流程结束了：" + activitiEvent.getProcessInstanceId());

                try {
                    // 执行同步操作
                    apiSyncMock();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }

                // 执行异步操作
                apiAsyncMock();

                break;
            default:
                break;
        }
    }

    void apiSyncMock() {
        System.out.println("同步操作");
        throw new RuntimeException("同步业务出错啦！");
    }

    void apiAsyncMock() {
        System.out.println("异步操作");
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
