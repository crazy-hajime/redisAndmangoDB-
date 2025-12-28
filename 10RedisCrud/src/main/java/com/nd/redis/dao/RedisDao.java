package com.nd.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate redisTemplate;
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public String getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setHash(String key, String field, String value) {
        redisTemplate.opsForHash().put(key,field,value);
    }

    public String getHash(String key, String field) {
        return (String)redisTemplate.opsForHash().get(key,field);
    }
}
