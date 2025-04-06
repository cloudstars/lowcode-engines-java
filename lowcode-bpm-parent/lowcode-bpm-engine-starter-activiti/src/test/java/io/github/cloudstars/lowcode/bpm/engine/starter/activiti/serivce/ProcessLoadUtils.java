package io.github.cloudstars.lowcode.bpm.engine.starter.activiti.serivce;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.ProcessBuildBpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.activiti.bpmn.model.Process;

/**
 * 流程加载工具
 *
 * @author clouds
 */
public final class ProcessLoadUtils {

    private ProcessLoadUtils() {
    }

    /**
     * 从classpath加载流程配置并生成流程定义
     *
     * @param classpath
     * @return 流程定义
     */
    public static Process loadFromClassPath(String classpath) {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath(classpath);
        ProcessConfig processConfig = new ProcessConfig(configJson);
        Process process = new Process();
        ProcessBuildBpmNodeVisitor visitor = new ProcessBuildBpmNodeVisitor(process);
        processConfig.getMainBranch().accept(visitor);
        return process;
    }
}
