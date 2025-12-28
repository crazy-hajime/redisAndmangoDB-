package com.nd.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

//@Document(collection = "cart") 创建集合cart
@Document(collection = "cart")
public class Cart {
    // 主键 id
    @Id
    private String id;

    private Long userId;

    private List<CartItem> items;

    private Date createTime;

    // Constructors, Getters, and Setters
    public Cart() {}

    public Cart(Long userId, List<CartItem> items) {
        this.userId = userId;
        this.items = items;
        this.createTime = new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
