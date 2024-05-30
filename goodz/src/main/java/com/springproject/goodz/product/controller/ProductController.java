package com.springproject.goodz.product.controller;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;  // 올바른 Value 애노테이션 import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.goodz.product.dto.Product;
import com.springproject.goodz.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Value("${upload.path}")        // application.properties 에 설정한 업로드 경로 가져옴
    private String uploadPath;
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("")
    public String index() {
        return "/product/index";
    }

    @GetMapping("/product/size_table")
    public String productSizeInfoPage() {
        return "/fragments/product/size_table";
    }

    @GetMapping("/detail")
    public String productDetailPage() {
        return "/product/detail";
    }

    @GetMapping("/top")
    public String top(Model model) throws Exception {

        List<Product> topList = productService.top();

        // 이미지 경로를 설정하는 부분을 추가
        for (Product product : topList) {
            String firstImageUrl = product.getFirstImageUrl();
            if (firstImageUrl != null) {
                // product.setImageUrl("/upload/" + URLEncoder.encode(firstImageUrl, "UTF-8")); // URL 인코딩 적용
                product.setImageUrl(URLEncoder.encode(firstImageUrl, "UTF-8")); // URL 인코딩 적용
            }
        }

        model.addAttribute("topList", topList);

        return "/product/top";
    }

    @GetMapping("/pants")
    public String productPantsPage() {
        return "/product/pants";
    }

    @GetMapping("/shoes")
    public String productShoesPage() {
        return "/product/shoes";
    }

    @GetMapping("/accessory")
    public String productAccessoryPage() {
        return "/product/accessory";
    }
}
