package com.example.frontPJ.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //번호

    @Column
    @Lob
    private String image; //상품이미지파일

    @Column
    private String productName; //상품명

    @Column
    private String description; //상품설명

    @Column
    private Integer price; //상품 정가

    @Column
    private Integer dc; //상품 할인율

    @Column
    private String productType; //실온 냉동 냉장 구분

    @Column
    private Integer bestItem; // 베스트상품 표시 유무 0은 베스트상품x , 1은 베스트상품

    @Column
    private LocalDateTime registerTime; //상품 등록시간

    @Column
    private LocalDateTime modifyTime; //상품 수정시간

}
