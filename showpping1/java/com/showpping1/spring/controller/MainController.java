package com.showpping1.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.showpping1.spring.domain.Product;
import com.showpping1.spring.service.ProductService;

@Controller
// ⭐️ Lombok 오류 우회를 위해 수동 생성자 사용
public class MainController {

    private final ProductService productService;
    // CategoryService는 테스트에만 사용되었으므로, 주입 필드 제거 가능
    // private final CategoryService categoryService; 

    // ⭐️ 수동 생성자 유지: ProductService만 필요하다고 가정
    public MainController(ProductService productService) {
        this.productService = productService;
        // this.categoryService = categoryService; // 제거
    }
    
    /**
     * 메인 페이지 (index.html)를 로드하고 상품 데이터를 전달합니다.
     * 경로: /
     */
    @GetMapping("/")
    public String index(Model model) {
        // 1. 랭킹 상품 조회 (조회수 기준 상위 10개)
        List<Product> ranking = productService.findTop10ByViewCount(); 
        
        // 2. 추천 상품 조회 (최신 상품 5개 등)
        List<Product> recommendations = productService.findLatestProducts(5); 
        
        model.addAttribute("realtimeRanking", ranking); // ⭐️ 이 이름으로 HTML에 전달
        model.addAttribute("recommendProducts", recommendations); // ⭐️ 이 이름으로 HTML에 전달
        
        return "index";
    }
    

    // ⭐️⭐️⭐️ 테스트용 API (/test/create-dummy) 메서드 전체 삭제 ⭐️⭐️⭐️
}