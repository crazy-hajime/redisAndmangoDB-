package com.nd.mongo.dao;


//mongodb 内置两种操作方式：1.基于注解的方式 2.基于接口的方式

// 基于接口的方式：创建一个接口，继承MongoRepository接口，例如：public interface CartRepository extends MongoRepository<Cart, String> {
// 可以帮我们完成数据库操作简单的增删查改
// 进行复杂的操作使用  MongoTemplate


import com.nd.mongo.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {


    Cart findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
