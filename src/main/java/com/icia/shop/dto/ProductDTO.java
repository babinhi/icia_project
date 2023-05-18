package com.icia.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@ToString

public class ProductDTO {
    private Long id;
    private String productTitle;
    private String productWriter;
    private String productContents;
    private Long memberId;
    private Timestamp productCreatedDate;
    private int productHits;
    private int fileAttached;
    private List<MultipartFile> productFile;
}
