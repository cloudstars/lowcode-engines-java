package io.github.cloudstars.lowcode.bpm.vendor.activiti;

import io.github.cloudstars.lowcode.bpm.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.query.ProcessInstInfoQueryImpl;
import io.github.cloudstars.lowcode.bpm.vendor.query.ProcessInstanceInfoQuery;
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

    private static final Logger logger = LoggerFactory.getLogger(ActivitiBpmProcessProviderImpl.class);

    @Resource
    private RuntimeService runtimeService;

    @Override
    public ProcessInstanceInfoQuery createProcessQuery() {
        return new ProcessInstInfoQueryImpl(this.runtimeService);
    }

    @Override
    public String start(String processKey, Map<String, Object> dataMap) {
        ProcessInstance processInst = this.runtimeService.startProcessInstanceByKey(processKey, dataMap);
        String processInstId = processInst.getProcessInstanceId();
        logger.info("Activiti流程启动成功，实例ID：{}", processInstId);
        return processInstId;
    }

}
