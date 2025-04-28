package io.github.cloudstars.lowcode.bpm.engine.service;

import io.github.cloudstars.lowcode.bpm.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.vendor.query.ProcessInstanceInfoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 流程引擎实现
 *
 * @author clouds 
 */
public class BpmProcessServiceImpl implements BpmProcessService {

    private static final Logger logger = LoggerFactory.getLogger(BpmProcessServiceImpl.class);

    /**
     * 流程引擎提供方
     */
    private BpmProcessProvider processProvider;

    public BpmProcessServiceImpl(BpmProcessProvider processProvider) {
        this.processProvider = processProvider;
    }

    @Override
    public ProcessInstanceInfoQuery createQuery() {
        return this.processProvider.createProcessQuery();
    }

    @Override
    public String start(String processKey, Map<String, Object> dataMap) {
        String processInstId = this.processProvider.start(processKey, dataMap);
        logger.info("流程启动调用成功，返回流程实例ID：{}", processInstId);
        return processInstId;
    }

    @Override
    public void suspend(String processInstId) {

    }

    @Override
    public void resume(String processInstId) {

    }

    @Override
    public void terminate(String processInstId) {

    }

    @Override
    public void delete(String processInstId) {

    }

}
