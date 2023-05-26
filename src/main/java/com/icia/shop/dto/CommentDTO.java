package com.icia.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class CommentDTO {
    private Long id;
    private Long productId;
    private Long memberId;
    private String commentWriter;
    private String commentContents;
    private Timestamp commentCreatedDate;
}
