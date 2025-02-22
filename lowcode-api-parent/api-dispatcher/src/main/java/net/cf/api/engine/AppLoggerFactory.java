package net.cf.api.engine;

import org.slf4j.Logger;

/**
 * 日志工厂
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 16:36
 */
public interface AppLoggerFactory {
    /**
     * 获取logger对象
     * @return logger对象
     */
    Logger getLogger();
}
