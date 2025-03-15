package io.github.cloudstars.lowcode.commons.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.json.JsonObjectMapper;

import javax.annotation.Resource;

@Component
public class CacheHelper {

    @Resource
    private JsonObjectMapper jsonObjectMapper;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    /**
     * 是否存在某个缓存
     *
     * @param resource
     * @param bizKey
     * @return
     */
    public boolean existsKey(CacheResourceTypeEnum resource, String bizKey) {
        String cacheKey = this.getCacheKey(resource, bizKey);
        return this.redisTemplate.hasKey(cacheKey);
    }

    /**
     * 查询并对字符串格式返回
     *
     * @param resource
     * @param bizKey
     * @return
     */
    public String getValue(CacheResourceTypeEnum resource, String bizKey) {
        String cacheKey = this.getCacheKey(resource, bizKey);
        return this.redisTemplate.opsForValue().get(cacheKey);
    }

    /**
     * 查询并以对象格式返加顺
     *
     * @param resource
     * @param bizKey
     * @param tClass
     * @return
     * @param <T>
     */
    public <T extends Object> T getObjectValue(CacheResourceTypeEnum resource, String bizKey, Class<T> tClass) {
        String value = this.getValue(resource, bizKey);
        return this.jsonObjectMapper.fromJson(value, tClass);
    }

    /**
     * 添加一个缓存值
     *
     * @param resource
     * @param bizKey
     */
    public void putValue(CacheResourceTypeEnum resource, String bizKey, String value) {
        String cacheKey = this.getCacheKey(resource, bizKey);
        this.redisTemplate.opsForValue().set(cacheKey, value);
    }

    /**
     * 添加一个缓存值
     *
     * @param resource
     * @param bizKey
     */
    public <T extends Object> void putObjectValue(CacheResourceTypeEnum resource, String bizKey, T objectValue) {
        String cacheKey = this.getCacheKey(resource, bizKey);
        String value = this.jsonObjectMapper.toJson(objectValue);
        this.redisTemplate.opsForValue().set(cacheKey, value);
    }


    /**
     * 删除一个缓存
     *
     * @param resource
     * @param bizKey
     */
    public void deleteKey(CacheResourceTypeEnum resource, String bizKey) {
        String cacheKey = this.getCacheKey(resource, bizKey);
        this.redisTemplate.delete(cacheKey);
    }

    /**
     * 获取缓存的编号
     *
     * @param resource
     * @param bizKey
     * @return
     */
    private String getCacheKey(CacheResourceTypeEnum resource, String bizKey) {
        // 用::隔开保持与spring-cache一致
        return resource.getName() + "::" + bizKey;
    }
}
