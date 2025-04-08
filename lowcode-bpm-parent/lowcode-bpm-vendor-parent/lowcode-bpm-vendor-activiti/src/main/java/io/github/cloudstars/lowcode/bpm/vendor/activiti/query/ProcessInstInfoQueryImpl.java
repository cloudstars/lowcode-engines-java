package io.github.cloudstars.lowcode.bpm.vendor.activiti.query;

import io.github.cloudstars.lowcode.bpm.vendor.query.ProcessInstanceInfo;
import io.github.cloudstars.lowcode.bpm.vendor.query.ProcessInstanceInfoQuery;
import io.github.cloudstars.lowcode.bpm.vendor.query.QueryProperty;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 实程实例信息查询实现
 *
 * @author clouds
 */
public class ProcessInstInfoQueryImpl implements ProcessInstanceInfoQuery<ProcessInstanceInfoQuery<?, ?>, ProcessInstanceInfo> {

    /**
     * Activiti的任务服务
     */
    private RuntimeService runtimeService;

    private HistoryService historyService;

    private ProcessInstanceQuery procInstQuery;

    public ProcessInstInfoQueryImpl(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
        this.procInstQuery = runtimeService.createProcessInstanceQuery();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public ProcessInstanceInfo singleResult() {
        return null;
    }

    @Override
    public List<ProcessInstanceInfo> list() {
        List<ProcessInstance> procInstances = this.procInstQuery.list();
        return procInstances.stream().map((pi -> toProcessInstanceInfo(pi))).collect(Collectors.toList());
    }

    @Override
    public ProcessInstanceInfoQuery<?, ?> asc() {
        return null;
    }

    @Override
    public ProcessInstanceInfoQuery<?, ?> desc() {
        return null;
    }

    @Override
    public ProcessInstanceInfoQuery<?, ?> orderBy(QueryProperty var1) {
        return null;
    }

    @Override
    public List<ProcessInstanceInfo> listPage(int var1, int var2) {
        return null;
    }


    private ProcessInstanceInfo toProcessInstanceInfo(ProcessInstance procInst) {
        ProcessInstanceInfo procInstInfo = new ProcessInstanceInfo();
        procInstInfo.setId(procInst.getId());
        procInstInfo.setName(procInst.getName());
        procInstInfo.setDescription(procInst.getDescription());
        procInstInfo.setStarter(procInst.getStartUserId());
        procInstInfo.setStartTime(procInst.getStartTime());
        procInstInfo.setProcessDefinitionKey(procInst.getProcessDefinitionKey());
        procInstInfo.setBusinessKey(procInst.getBusinessKey());
        return procInstInfo;
    }

}
