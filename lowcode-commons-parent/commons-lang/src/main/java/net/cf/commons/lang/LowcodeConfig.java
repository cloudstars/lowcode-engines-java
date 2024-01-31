package net.cf.commons.lang;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 添加配置，确保扫描包 net.cf
 *
 * @author clouds
 */
@ComponentScan(basePackages = {"net.cf"})
@Configuration
public class LowcodeConfig {
}
