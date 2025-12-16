package com.showpping1.spring.controller;

import com.showpping1.spring.domain.Product;
import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.service.ProductService;
import com.showpping1.spring.service.ProductOptionService; // ⭐️ 옵션 조회를 위해 추가

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // URL 경로의 변수를 받기 위해 추가

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductOptionService productOptionService; // ⭐️ 옵션 서비스 주입

    // ⭐️ 수동 생성자 (Lombok 오류 우회 및 Service 주입)
    public ProductController(ProductService productService, ProductOptionService productOptionService) {
        this.productService = productService;
        this.productOptionService = productOptionService;
    }

    /**
     * 상품 상세 페이지 요청을 처리합니다.
     * 경로: /product/{productId}
     */
    @GetMapping("/product/{productId}")
    public String getProductDetail(
        @PathVariable("productId") Long productId, 
        Model model) 
    {
        // ⭐️⭐️⭐️ 1. 조회수 증가 로직 추가 ⭐️⭐️⭐️
        try {
            productService.incrementViewCount(productId);
        } catch (IllegalArgumentException e) {
            // 상품 ID가 유효하지 않으면 404 페이지로 리다이렉트하거나 오류 처리
            return "redirect:/error/404"; 
        }
        
        // 2. 상품 기본 정보 조회 (증가된 viewCount를 포함함)
        Product product = productService.findProductById(productId);
        
        // 3. 해당 상품에 연결된 모든 옵션 목록 조회
        List<ProductOption> options = productOptionService.findOptionsByProductId(productId);
        
        // 4. 모델에 데이터 전달
        model.addAttribute("product", product);
        model.addAttribute("options", options);
        
        return "product_detail";    
    }
    
}