package com.icia.shop.service;

import com.icia.shop.dto.CartDTO;
import com.icia.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    // 장바구니 상품 확인
    public int countCart(Long productId, Long memberId) {
        System.out.println("서비스 productId = " + productId);
        return cartRepository.countCart(productId,memberId);
    }

    // 장바구니 상품수량 변경
    public void updateCart(CartDTO cartDTO) {
        cartRepository.updateCart(cartDTO);
    }

    // 장바구니에 추가
    public void insert(CartDTO cartDTO) {
        cartRepository.insert(cartDTO);
        System.out.println("insert 서비스 cartDTO = " + cartDTO);
    }

    // 장바구니 리스트
    public List<CartDTO> listCart(Long memberId) {

        return cartRepository.listCart(memberId);
    }

    // 장바구니 금액 합계
    public int sumMoney(Long memberId) {
        return cartRepository.sumMoney(memberId);
    }

}
