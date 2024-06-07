package com.springproject.goodz.product.dto;

import java.util.List;
import lombok.Data;

@Data
public class UpdateProductRequest {
    private int pNo;
    private String productName;
    private int initialPrice;
    private String bName;
    private String category;
    private List<ProductOption> options;
    private List<Integer> addedStockQuantities;
}