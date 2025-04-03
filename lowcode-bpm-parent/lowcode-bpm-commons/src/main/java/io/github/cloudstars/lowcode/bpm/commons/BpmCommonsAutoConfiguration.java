package io.github.cloudstars.lowcode.bpm.commons;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClassFactory;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteNodeConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class BpmCommonsAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的节点配置
        NodeConfigClassFactory.register(StartNodeConfig.class);
        NodeConfigClassFactory.register(EndNodeConfig.class);
        NodeConfigClassFactory.register(UserApproveNodeConfig.class);
        NodeConfigClassFactory.register(UserWriteNodeConfig.class);
        NodeConfigClassFactory.register(GatewayNodeConfig.class);
        NodeConfigClassFactory.register(GatewayBranchNodeConfig.class);
        NodeConfigClassFactory.register(BranchNodeConfig.class);
    }

}
