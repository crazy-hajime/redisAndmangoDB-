package com.nd.mongo.model;

// 嵌套商品项类
public class CartItem {
    private String sku;
    private String name;
    private Double price;
    private Integer num;

    public CartItem(String sku, String name, Double price, Integer num) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    // Getters and Setters
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getNum() { return num; }
    public void setNum(Integer num) { this.num = num; }
}