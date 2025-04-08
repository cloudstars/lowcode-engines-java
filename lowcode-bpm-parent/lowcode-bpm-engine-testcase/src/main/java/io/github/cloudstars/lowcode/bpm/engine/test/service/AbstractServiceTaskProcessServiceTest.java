package io.github.cloudstars.lowcode.bpm.engine.test.service;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;
import io.github.cloudstars.lowcode.bpm.engine.service.task.BpmTaskService;
import io.github.cloudstars.lowcode.bpm.engine.test.ProcessClassPaths;
import io.github.cloudstars.lowcode.bpm.engine.test.ProcessConfigLoader;
import io.github.cloudstars.lowcode.bpm.vendor.query.ProcessInstanceInfo;
import io.github.cloudstars.lowcode.bpm.vendor.query.ProcessInstanceInfoQuery;
import org.junit.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽象的流程服务测试类，定义了测试的规范（输入/输出）
 *
 * @author clouds
 */
public abstract class AbstractServiceTaskProcessServiceTest {

    @Resource
    private BpmDeployService deployService;

    @Resource
    private BpmProcessService processService;

    @Resource
    private BpmTaskService taskService;

    /**
     * 测试启动流程定义（简单不含分支的流程）
     */
    protected void testStartSimple1() {
        ProcessConfig config = ProcessConfigLoader.loadFromClassPath(ProcessClassPaths.SERVICE_SIMPLE1);
        String processKey = config.getKey();

        // 先部署再启动流程
        Date current = new Date();
        this.deployService.deploy(config);
        this.processService.start(processKey, new HashMap<>());

        ProcessInstanceInfoQuery query = this.processService.createQuery();
        List<ProcessInstanceInfo> procInstances = query.list();
        List<ProcessInstanceInfo> thisProcInstances = procInstances.stream().filter(
                (pi) -> pi.getStartTime().after(current)
        ).collect(Collectors.toList());
        Assert.assertEquals(0, thisProcInstances.size());

        // TODO 查询历史流程，历史流程应该有1条
    }

}
