package com.showpping1.spring.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.showpping1.spring.domain.Product;
import com.showpping1.spring.service.ProductService;

// ⭐️ @RequiredArgsConstructor 대신 수동 생성자를 사용합니다.
@Controller
public class ShirtController {

    // ⭐️ Service 계층을 주입받기 위해 final 필드 선언
    private final ProductService productService;
    
    // ⭐️ 수동 생성자 추가 (Lombok 오류 우회)
    public ShirtController(ProductService productService) {
        this.productService = productService;
    }

    // 셔츠 목록 페이지 요청을 처리합니다.
    @GetMapping("/shirtlist")
    public String getShirtListPage(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "16") int size, 
        @RequestParam(value = "sort", defaultValue = "id") String sortField,
        @RequestParam(value = "category", required = false) String category, // URL에서 카테고리 파라미터가 있을 수 있음
        Model model) 
    {
        // 1. Pageable 객체 생성 (유지)
        Sort sort = Sort.by(Sort.Direction.DESC, sortField); 
        Pageable pageable = PageRequest.of(page, size, sort);
        
        // 2. ⭐️ [핵심 수정] 기본 카테고리 설정: 카테고리 파라미터가 없으면 "상의"를 기본값으로 사용
        String actualCategoryName = category;
        if (actualCategoryName == null || actualCategoryName.isEmpty()) {
            // "상의" 카테고리 이름을 기본값으로 설정하여 상의 상품만 보이도록 강제 필터링합니다.
            actualCategoryName = "상의"; 
        }
        
        // 3. 서비스 호출
        Page<Product> products = productService.findProducts(actualCategoryName, pageable); 
        
        // 4. 모델에 추가 (누락되지 않도록 주의)
        model.addAttribute("products", products);
        
        return "shirtlist"; 
    }
   
    // ⭐️⭐️⭐️ 더미 데이터 생성 메서드 (createDummyShirts)는 이제 필요 없으므로 삭제합니다. ⭐️⭐️⭐️
}