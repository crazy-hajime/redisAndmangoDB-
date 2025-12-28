package com.nd.redis.controller;

//dao层 模型层  数据访问层 操作数据库  crud 分页
//service层 业务层  封装业务逻辑 列如： 提交订单：1.保存订单信息，2.扣减库存，3.增减销量 4，删除购物车
//controller层 控制层  SpringMvc 做web接口开发，请求，响应  封装了servlet


import com.nd.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController   // @Controller + @ResponseBody //接受请求，返回json数据   {"name":"张三"}
@RequestMapping("/redis")   //请求映射路径
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    //redis操作字符串 String
    @GetMapping("/string")
    public String redisString(String key, String value){
        HashMap<String, String> map = new HashMap<>();
        map.put("name","张三");
        map.put("age","18");
        map.put("sex","男");
        map.put("address","上海");
        //String 命令
        redisTemplate.opsForValue().multiSet(map);
        redisTemplate.opsForValue().set("name","张三",10, TimeUnit.SECONDS);
        //setnx key value
        redisTemplate.opsForValue().setIfAbsent("test","test",10,TimeUnit.SECONDS);
        redisTemplate.opsForValue().append("name","张三");
        redisTemplate.opsForValue().size("name");


        //set key value
        redisService.setString(key,value);


        //get key
        return "success:"+key+"="+redisService.getString(key);
    }

    @GetMapping("/hash")
    public String redisHash(String key,String field, String value){
        //set key value
        redisService.setHash(key,field,value);
        redisTemplate.opsForHash();

        redisTemplate.opsForGeo().add("city",new Point(116.404,39.915),"北京");
        redisTemplate.opsForGeo().position("city","北京");
        //get key
        return "success:"+key+"="+ field +":"+redisService.getHash(key,field);
    }
}
