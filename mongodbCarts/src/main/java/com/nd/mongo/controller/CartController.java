package com.nd.mongo.controller;

import com.nd.mongo.model.Cart;
import com.nd.mongo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/add")
    public void add(@RequestBody Cart cart) {
        cartService.save(cart);
    }
    @GetMapping("/all")
    public List<Cart> getAll() {
        return cartService.getAll();
    }
    @GetMapping("/get/{userId}")
    public Cart get(@PathVariable Long userId) {
        return cartService.get(userId);
    }
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable Long userId) {
        cartService.delete(userId);
    }
    @PutMapping("/update/{userId}")
    public void update(@PathVariable Long userId, @RequestBody Cart cart) {
        cartService.update(userId, cart);
    }


}
