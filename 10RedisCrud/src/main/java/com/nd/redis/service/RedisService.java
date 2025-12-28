package com.nd.redis.service;

import com.nd.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisDao redisDao;

    public void setString(String key, String value) {
       redisDao.setString(key, value);
    }

    public String getString(String key) {
        return redisDao.getString(key);
    }

    public void setHash(String key, String field, String value) {
        redisDao.setHash(key, field, value);
    }

    public String getHash(String key, String field) {
        return redisDao.getHash(key, field);
    }
}
