package com.example.frontPJ.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemDto {
    private String image; //상품이미지파일
    private String productName; //상품명

    private String description; //상품설명

    private Integer price; //상품 정가

    private Integer dc; //상품 할인율

    private Integer dcPrice; //할인 적용가

    private String productType; //실온 냉동 냉장 구분

    private Integer bestItem; // 베스트상품 표시 유무 0은 베스트상품x , 1은 베스트상품

}
