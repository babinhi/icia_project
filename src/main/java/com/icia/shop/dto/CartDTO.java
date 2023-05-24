package com.icia.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CartDTO {
    private int cartId;

    private String memberId;

    private int productId;

    private int productCnt;

    //book

    private String productTitle;

    private int productPrice;

//    private double productDiscount;

    // 추가
    private int salePrice;

    private int totalPrice;
}
