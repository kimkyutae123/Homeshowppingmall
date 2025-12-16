package com.showpping1.spring.controller;

import com.showpping1.spring.domain.Product;
import com.showpping1.spring.service.ProductService; 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoelistController {

    private final ProductService productService;
    
    // 수동 생성자 추가 (Lombok 오류 우회 및 Service 주입)
    public ShoelistController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 신발 목록 페이지 (첫 로딩) 요청을 처리하고, 페이징된 상품 목록을 전달합니다.
     * 경로: /shoelist
     */
    @GetMapping("/shoelist")
    public String getShoeListPage(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "16") int size, 
        @RequestParam(value = "sort", defaultValue = "id") String sortField,
        @RequestParam(value = "category", required = false) String category, 
        Model model) 
    {
        Sort sort = Sort.by(Sort.Direction.DESC, sortField); 
        Pageable pageable = PageRequest.of(page, size, sort);
        
        String actualCategoryName = category;
        if (actualCategoryName == null || actualCategoryName.isEmpty()) {
            actualCategoryName = "신발"; 
        }
        
        Page<Product> products = productService.findProducts(actualCategoryName, pageable); 
        
        // ⭐️⭐️⭐️ 누락된 부분: Thymeleaf 템플릿에 products 객체를 전달합니다. ⭐️⭐️⭐️
        model.addAttribute("products", products);
        
        return "shoelist"; 
    }
    
    /**
     * AJAX 요청 처리: 무한 스크롤 시 다음 페이지의 상품 그리드 (프래그먼트)를 반환합니다.
     * 경로: /shoelist/ajax
     */
    @GetMapping("/shoelist/ajax")
    public String getShoeListAjax(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "16") int size, 
        @RequestParam(value = "sort", defaultValue = "id") String sortField,
        @RequestParam(value = "category", required = false) String category, 
        Model model) 
    {
        Sort sort = Sort.by(Sort.Direction.DESC, sortField); 
        Pageable pageable = PageRequest.of(page, size, sort);
        
        String actualCategoryName = category;
        if (actualCategoryName == null || actualCategoryName.isEmpty()) {
            actualCategoryName = "신발"; 
        }
        
        Page<Product> products = productService.findProducts(actualCategoryName, pageable);
        
        // ⭐️⭐️⭐️ 누락된 부분: AJAX 프래그먼트에 products 객체를 전달합니다. ⭐️⭐️⭐️
        model.addAttribute("products", products);

        return "product-items :: productGrid"; 
    }
}