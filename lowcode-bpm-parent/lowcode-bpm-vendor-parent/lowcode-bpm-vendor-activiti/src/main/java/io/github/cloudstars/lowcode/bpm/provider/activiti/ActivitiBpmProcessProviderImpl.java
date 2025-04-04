package io.github.cloudstars.lowcode.bpm.provider.activiti;

import io.github.cloudstars.lowcode.bpm.engine.vendor.BpmProcessProvider;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 基于Activiti的BPM流程提供实现
 *
 * @author clouds
 */
public class ActivitiBpmProcessProviderImpl implements BpmProcessProvider {

    private final static Logger logger = LoggerFactory.getLogger(ActivitiBpmProcessProviderImpl.class);

    @Resource
    private RuntimeService runtimeService;

    @Override
    public String start(String processKey, Map<String, Object> dataMap) {
        ProcessInstance processInst = this.runtimeService.startProcessInstanceByKey(processKey, dataMap);
        String processInstId = processInst.getProcessInstanceId();
        logger.info("Activiti流程启动成功，实例ID：{}", processInstId);
        return processInstId;
    }

}
