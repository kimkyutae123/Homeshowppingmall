package com.showpping1.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.showpping1.spring.domain.Category;
import com.showpping1.spring.domain.Product;
import com.showpping1.spring.domain.ProductOption;
import com.showpping1.spring.service.CategoryService;
import com.showpping1.spring.service.ProductService;

@Component
@Transactional 
public class HoodieDataInitializer implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    public HoodieDataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [DB 초기화 - 후드 티셔츠] 데이터 삽입 시작...");
        createHoodieDataIfNotExist();
        System.out.println(">>> [DB 초기화 - 후드 티셔츠] 완료.");
    }

    private void createHoodieDataIfNotExist() {
        
        Category topCategory = categoryService.findCategoryByName("상의");
        if (topCategory == null) {
            System.out.println(">>> [DB 초기화 - 후드] 상위 '상의' 카테고리가 없어 생성합니다.");
            topCategory = new Category();
            topCategory.setName("상의");
            topCategory.setParentId(null);
            categoryService.saveCategory(topCategory);
        }
        
        // 2. 후드 티셔츠 카테고리를 찾거나 생성합니다.
        Category hoodieCategory = categoryService.findCategoryByName("후드 티셔츠");
        if (hoodieCategory == null) {
            System.out.println(">>> [DB 초기화 - 후드] '후드 티셔츠' 카테고리가 없어 새로 생성합니다.");
            hoodieCategory = new Category();
            hoodieCategory.setName("후드 티셔츠");
            hoodieCategory.setParentId(topCategory.getId());
            categoryService.saveCategory(hoodieCategory);
        }
        
        // ⭐️⭐️⭐️ 수정된 중복 삽입 방지 로직: 자기 카테고리만 확인 ⭐️⭐️⭐️
        // 후드 티셔츠 카테고리에 상품이 하나라도 있으면 건너뜁니다.
        if (productService.countProductsByCategory(hoodieCategory.getId()) > 0) { // ⭐️ 수정: 카테고리 ID 사용
            System.out.println(">>> [DB 초기화 - 후드 티셔츠] 해당 카테고리 상품이 이미 존재하여 초기화 작업을 건너뜁니다.");
            return; 
        }

        // ⭐️⭐️⭐️ 3. 옵션 세트 정의 (16개) ⭐️⭐️⭐️
        List<ProductOption> allOptions1 = createOptions(150);
        List<ProductOption> allOptions2 = createOptions(150);
        List<ProductOption> allOptions3 = createOptions(150);
        List<ProductOption> allOptions4 = createOptions(150);
        List<ProductOption> allOptions5 = createOptions(150);
        List<ProductOption> allOptions6 = createOptions(150);
        List<ProductOption> allOptions7 = createOptions(150);
        List<ProductOption> allOptions8 = createOptions(150);
        List<ProductOption> allOptions9 = createOptions(150);
        List<ProductOption> allOptions10 = createOptions(150);
        List<ProductOption> allOptions11 = createOptions(150);
        List<ProductOption> allOptions12 = createOptions(150);
        List<ProductOption> allOptions13 = createOptions(150);
        List<ProductOption> allOptions14 = createOptions(150);
        List<ProductOption> allOptions15 = createOptions(150);
        List<ProductOption> allOptions16 = createOptions(150);

        // ⭐️⭐️⭐️ 4. 상품 1~16 정의 ⭐️⭐️⭐️

        // --- 상품 1: 오버핏 코튼 후드티 ---
        Product hoodie1 = new Product();
        hoodie1.setName("오버핏 코튼 후드티 (Black)");
        hoodie1.setPrice(45000);
        hoodie1.setImageUrl("/image/hoodie_1.png");
        hoodie1.setCategory(hoodieCategory);

        // --- 상품 2: 로고 자수 후드집업 ---
        Product hoodie2 = new Product();
        hoodie2.setName("로고 자수 후드집업 (Gray)");
        hoodie2.setPrice(58000);
        hoodie2.setImageUrl("/image/hoodie_2.png");
        hoodie2.setCategory(hoodieCategory);

        // --- 상품 3: 빈티지 그래픽 후드 ---
        Product hoodie3 = new Product();
        hoodie3.setName("빈티지 그래픽 루즈핏 후드 (Cream)");
        hoodie3.setPrice(52000);
        hoodie3.setImageUrl("/image/hoodie_3.png");
        hoodie3.setCategory(hoodieCategory);

        // --- 상품 4: 기모 스탠다드 후드티 ---
        Product hoodie4 = new Product();
        hoodie4.setName("기모 스탠다드 후드티 (Navy)");
        hoodie4.setPrice(49000);
        hoodie4.setImageUrl("/image/hoodie_4.png");
        hoodie4.setCategory(hoodieCategory);

        // --- 상품 5: 테리 소재 오버핏 후드 ---
        Product hoodie5 = new Product();
        hoodie5.setName("프렌치 테리 오버핏 후드 (Khaki)");
        hoodie5.setPrice(55000);
        hoodie5.setImageUrl("/image/hoodie_5.png");
        hoodie5.setCategory(hoodieCategory);

        // --- 상품 6: 하프 집업 후드 ---
        Product hoodie6 = new Product();
        hoodie6.setName("하프 집업 오버사이즈 후드 (Charcoal)");
        hoodie6.setPrice(62000);
        hoodie6.setImageUrl("/image/hoodie_6.png");
        hoodie6.setCategory(hoodieCategory);

        // --- 상품 7: 스트링 디테일 후드티 ---
        Product hoodie7 = new Product();
        hoodie7.setName("스트링 디테일 루즈핏 후드티 (White)");
        hoodie7.setPrice(47000);
        hoodie7.setImageUrl("/image/hoodie_7.png");
        hoodie7.setCategory(hoodieCategory);

        // --- 상품 8: 코듀로이 후드셔츠 ---
        Product hoodie8 = new Product();
        hoodie8.setName("코듀로이 오버핏 후드셔츠 (Brown)");
        hoodie8.setPrice(68000);
        hoodie8.setImageUrl("/image/hoodie_8.png");
        hoodie8.setCategory(hoodieCategory);

        // --- 상품 9: 사이드 지퍼 후드티 ---
        Product hoodie9 = new Product();
        hoodie9.setName("사이드 지퍼 포인트 후드티 (Mint)");
        hoodie9.setPrice(53000);
        hoodie9.setImageUrl("/image/hoodie_9.png");
        hoodie9.setCategory(hoodieCategory);

        // --- 상품 10: 크롭 후드티 ---
        Product hoodie10 = new Product();
        hoodie10.setName("여성 크롭 기모 후드티 (Pink)");
        hoodie10.setPrice(43000);
        hoodie10.setImageUrl("/image/hoodie_10.png");
        hoodie10.setCategory(hoodieCategory);

        // --- 상품 11: 베이직 솔리드 후드집업 ---
        Product hoodie11 = new Product();
        hoodie11.setName("베이직 솔리드 후드집업 (Black)");
        hoodie11.setPrice(57000);
        hoodie11.setImageUrl("/image/hoodie_11.png");
        hoodie11.setCategory(hoodieCategory);

        // --- 상품 12: 레터링 후드티 ---
        Product hoodie12 = new Product();
        hoodie12.setName("영문 레터링 프린팅 후드티 (Yellow)");
        hoodie12.setPrice(50000);
        hoodie12.setImageUrl("/image/hoodie_12.png");
        hoodie12.setCategory(hoodieCategory);

        // --- 상품 13: 워시드 후드티 ---
        Product hoodie13 = new Product();
        hoodie13.setName("워시드 오버핏 후드티 (Purple)");
        hoodie13.setPrice(56000);
        hoodie13.setImageUrl("/image/hoodie_13.png");
        hoodie13.setCategory(hoodieCategory);

        // --- 상품 14: 체크 패턴 후드셔츠 ---
        Product hoodie14 = new Product();
        hoodie14.setName("체크 패턴 오버핏 후드셔츠 (Red)");
        hoodie14.setPrice(65000);
        hoodie14.setImageUrl("/image/hoodie_14.png");
        hoodie14.setCategory(hoodieCategory);

        // --- 상품 15: 배색 후드티 ---
        Product hoodie15 = new Product();
        hoodie15.setName("배색 디자인 루즈핏 후드티 (Green)");
        hoodie15.setPrice(51000);
        hoodie15.setImageUrl("/image/hoodie_15.png");
        hoodie15.setCategory(hoodieCategory);

        // --- 상품 16: 스트릿 후드티 ---
        Product hoodie16 = new Product();
        hoodie16.setName("스트릿 감성 오버핏 후드티 (Orange)");
        hoodie16.setPrice(59000);
        hoodie16.setImageUrl("/image/hoodie_16.png");
        hoodie16.setCategory(hoodieCategory);
        
        // ⭐️⭐️⭐️ 5. 상품과 옵션 연결 및 저장 ⭐️⭐️⭐️
        System.out.println(">>> 후드 티셔츠 상품 16개 저장 시작...");

        productService.saveProduct(hoodie1, allOptions1);
        productService.saveProduct(hoodie2, allOptions2);
        productService.saveProduct(hoodie3, allOptions3);
        productService.saveProduct(hoodie4, allOptions4);
        productService.saveProduct(hoodie5, allOptions5);
        productService.saveProduct(hoodie6, allOptions6);
        productService.saveProduct(hoodie7, allOptions7);
        productService.saveProduct(hoodie8, allOptions8);
        productService.saveProduct(hoodie9, allOptions9);
        productService.saveProduct(hoodie10, allOptions10);
        productService.saveProduct(hoodie11, allOptions11);
        productService.saveProduct(hoodie12, allOptions12);
        productService.saveProduct(hoodie13, allOptions13);
        productService.saveProduct(hoodie14, allOptions14);
        productService.saveProduct(hoodie15, allOptions15);
        productService.saveProduct(hoodie16, allOptions16);

        System.out.println(">>> 후드 티셔츠 상품 16개 저장 완료.");
    }
    
    // 헬퍼 메서드: 옵션 세트를 생성 (코드 중복 방지)
    private List<ProductOption> createOptions(int stockQuantity) {
        ProductOption optionS = new ProductOption();
        optionS.setSizeName("S");
        optionS.setStockQuantity(stockQuantity);
        ProductOption optionM = new ProductOption();
        optionM.setSizeName("M");
        optionM.setStockQuantity(stockQuantity);
        ProductOption optionL = new ProductOption();
        optionL.setSizeName("L");
        optionL.setStockQuantity(stockQuantity);
        return Arrays.asList(optionS, optionM, optionL);
    }
}