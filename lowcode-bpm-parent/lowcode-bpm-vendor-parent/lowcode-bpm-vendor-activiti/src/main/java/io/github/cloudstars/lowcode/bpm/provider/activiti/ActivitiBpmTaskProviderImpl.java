package io.github.cloudstars.lowcode.bpm.provider.activiti;

import io.github.cloudstars.lowcode.bpm.engine.vendor.BpmTaskProvider;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 基于Activiti的BPM任务提供实现
 *
 * @author clouds
 */
public class ActivitiBpmTaskProviderImpl implements BpmTaskProvider {

    private final static Logger logger = LoggerFactory.getLogger(ActivitiBpmTaskProviderImpl.class);

    @Resource
    private RuntimeService runtimeService;


}
