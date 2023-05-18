package com.icia.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class ProductFileDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private Long productId;
}
