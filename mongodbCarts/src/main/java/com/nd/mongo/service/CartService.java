package com.nd.mongo.service;

import com.nd.mongo.dao.CartRepository;
import com.nd.mongo.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartService {
   @Autowired
   private CartRepository cartRepository;
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart get(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void delete(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    public void update(Long userId, Cart cart) {
        Cart cart1 = cartRepository.findByUserId(userId);
        if (cart1 != null) {
            cart1.setItems(cart.getItems());
            cart1.setCreateTime(new Date());
            cartRepository.save(cart1);
        }

    }
}
