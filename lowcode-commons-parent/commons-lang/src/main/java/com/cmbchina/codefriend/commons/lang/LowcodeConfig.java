package com.cmbchina.codefriend.commons.lang;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 添加配置，确保扫描包 com.cmbchina.codefriend
 *
 * @author 80274507
 */
@ComponentScan(basePackages = {"com.cmbchina.codefriend"})
@Configuration
public class LowcodeConfig {
}
