
package io.github.cloudstars.lowcode.bpm.commons.config.gateway;

/**
 * BPM网关类型
 *
 * @author clouds
 */
public enum GatewayType {

    AND, /* 并行执行的网关，一个网关下有多个分支，所有的分支均会执行 */
    OR, /* 排它执行的网关，一个网关下有多个分支，只有第一次满足条件的分支才执行 */
    XOR; /* 包容执行的网关，一个网关下有多个分支，只要分支的条件满足就执行 */

}
