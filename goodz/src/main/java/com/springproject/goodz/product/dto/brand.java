package com.springproject.goodz.product.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Brand {
    
    private int bNo;                // 브랜드 번호
    private String bName;           // 브랜드 명
    private String imageUrl;        // 브랜드 로고 이미지 경로

    
    private MultipartFile logoFile; // 첨부파일
}
