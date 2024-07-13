package net.cf.commons.cache;

/**
 * 缓存资源类型枚举类
 *
 * @author clouds
 */
public enum CacheResourceTypeEnum {

    STUDENT("STUDENT", 120);

    CacheResourceTypeEnum(String name, long defaultDurationSeconds) {
        this.name = name;
        this.defaultDurationSeconds = defaultDurationSeconds;
    }

    public String getName() {
        return name;
    }

    public long getDefaultDurationSeconds() {
        return defaultDurationSeconds;
    }

    /**
     * 缓存的名称（会用于缓存KEY的前缀）
     */
    private String name;

    /**
     * 缓存默认过期时间
     */
    private long defaultDurationSeconds;

}
