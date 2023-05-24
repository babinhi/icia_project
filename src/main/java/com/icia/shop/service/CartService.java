package com.icia.shop.service;

import com.icia.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class CartService {
    @Autowired
    private CartRepository cartRepository;
}
