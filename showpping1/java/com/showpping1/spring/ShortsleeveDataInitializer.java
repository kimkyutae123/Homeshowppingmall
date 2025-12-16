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
public class ShortsleeveDataInitializer implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ShortsleeveDataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [DB 초기화 - 반소매 티셔츠] 데이터 삽입 시작...");
        createShortsleeveDataIfNotExist();
        System.out.println(">>> [DB 초기화 - 반소매 티셔츠] 완료.");
    }

    private void createShortsleeveDataIfNotExist() {
        
        // 1. 상위 '상의' 카테고리를 찾거나 생성합니다.
        Category topCategory = categoryService.findCategoryByName("상의");
        if (topCategory == null) {
            System.out.println(">>> [DB 초기화 - 반소매] 상위 '상의' 카테고리가 없어 생성합니다.");
            topCategory = new Category();
            topCategory.setName("상의");
            topCategory.setParentId(null);
            categoryService.saveCategory(topCategory);
        }
        
        // 2. 반소매 티셔츠 카테고리를 찾거나 생성합니다.
        Category shortsleeveCategory = categoryService.findCategoryByName("반소매 티셔츠");
        if (shortsleeveCategory == null) {
            System.out.println(">>> [DB 초기화 - 반소매] '반소매 티셔츠' 카테고리가 없어 새로 생성합니다.");
            shortsleeveCategory = new Category();
            shortsleeveCategory.setName("반소매 티셔츠");
            shortsleeveCategory.setParentId(topCategory.getId());
            categoryService.saveCategory(shortsleeveCategory);
        }

        // ⭐️⭐️⭐️ 수정된 중복 삽입 방지 로직: 자기 카테고리만 확인 ⭐️⭐️⭐️
        // 반소매 티셔츠 카테고리에 상품이 하나라도 있으면 건너뜁니다.
        if (productService.countProductsByCategory(shortsleeveCategory.getId()) > 0) { // ⭐️ 수정: 카테고리 ID 사용
            System.out.println(">>> [DB 초기화 - 반소매 티셔츠] 해당 카테고리 상품이 이미 존재하여 초기화 작업을 건너뜁니다.");
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

        // --- 상품 1: 베이직 오버핏 반팔티 ---
        Product shortsleeve1 = new Product();
        shortsleeve1.setName("드셀리스 반소매 티셔츠");
        shortsleeve1.setPrice(28900);
        shortsleeve1.setImageUrl("/image/shortsleeve_1.png");
        shortsleeve1.setCategory(shortsleeveCategory);

        // --- 상품 2: 스트라이프 반팔티 ---
        Product shortsleeve2 = new Product();
        shortsleeve2.setName("오버핏 1:1 스트라이프 반팔티");
        shortsleeve2.setPrice(19900);
        shortsleeve2.setImageUrl("/image/shortsleeve_2.png");
        shortsleeve2.setCategory(shortsleeveCategory);

        // --- 상품 3: 그래픽 프린팅 반팔티 ---
        Product shortsleeve3 = new Product();
        shortsleeve3.setName("크래그 SL 코튼 셔츠 SS 남성");
        shortsleeve3.setPrice(105000);
        shortsleeve3.setImageUrl("/image/shortsleeve_3.png");
        shortsleeve3.setCategory(shortsleeveCategory);

        // --- 상품 4: 피그먼트 워싱 반팔티 ---
        Product shortsleeve4 = new Product();
        shortsleeve4.setName("코소 비치 아트웍 티셔츠 (WHITE)");
        shortsleeve4.setPrice(29260);
        shortsleeve4.setImageUrl("/image/shortsleeve_4.png");
        shortsleeve4.setCategory(shortsleeveCategory);

        // --- 상품 5: 브이넥 반팔티 ---
        Product shortsleeve5 = new Product();
        shortsleeve5.setName("VLAD 피그먼트 슬래쉬 반팔 티셔츠_차콜");
        shortsleeve5.setPrice(24900);
        shortsleeve5.setImageUrl("/image/shortsleeve_5.png");
        shortsleeve5.setCategory(shortsleeveCategory);

        // --- 상품 6: 포켓 디테일 반팔티 ---
        Product shortsleeve6 = new Product();
        shortsleeve6.setName("[반팔티셔츠 햄스터밈] 남여공용 오버핏");
        shortsleeve6.setPrice(17900);
        shortsleeve6.setImageUrl("/image/shortsleeve_6.png");
        shortsleeve6.setCategory(shortsleeveCategory);

        // --- 상품 7: 린넨 블렌드 반팔티 ---
        Product shortsleeve7 = new Product();
        shortsleeve7.setName("Rabbit Ruched Top [White]");
        shortsleeve7.setPrice(47600);
        shortsleeve7.setImageUrl("/image/shortsleeve_7.png");
        shortsleeve7.setCategory(shortsleeveCategory);

        // --- 상품 8: 폴로 카라 반팔티 ---
        Product shortsleeve8 = new Product();
        shortsleeve8.setName("치즈 베리 피그먼트 반팔티 스모크블랙");
        shortsleeve8.setPrice(26000);
        shortsleeve8.setImageUrl("/image/shortsleeve_8.png");
        shortsleeve8.setCategory(shortsleeveCategory);

        // --- 상품 9: 스포츠 드라이 반팔티 ---
        Product shortsleeve9 = new Product();
        shortsleeve9.setName("Bella wrap off");
        shortsleeve9.setPrice(30500);
        shortsleeve9.setImageUrl("/image/shortsleeve_9.png");
        shortsleeve9.setCategory(shortsleeveCategory);

        // --- 상품 10: 크롭 반팔티 ---
        Product shortsleeve10 = new Product();
        shortsleeve10.setName("노팅힐 페미닌 원숄더 반팔");
        shortsleeve10.setPrice(27900);
        shortsleeve10.setImageUrl("/image/shortsleeve_10.png");
        shortsleeve10.setCategory(shortsleeveCategory);

        // --- 상품 11: 면 스판 반팔티 ---
        Product shortsleeve11 = new Product();
        shortsleeve11.setName("배색 라운드넥 레터링 반팔 티셔츠");
        shortsleeve11.setPrice(35900);
        shortsleeve11.setImageUrl("/image/shortsleeve_11.png");
        shortsleeve11.setCategory(shortsleeveCategory);

        // --- 상품 12: 레터링 로고 반팔티 ---
        Product shortsleeve12 = new Product();
        shortsleeve12.setName("Precious 레글런 반팔티 브라운");
        shortsleeve12.setPrice(23660);
        shortsleeve12.setImageUrl("/image/shortsleeve_12.png");
        shortsleeve12.setCategory(shortsleeveCategory);

        // --- 상품 13: 멜란지 반팔티 ---
        Product shortsleeve13 = new Product();
        shortsleeve13.setName("FOREST WARS T-SHIRTS BLACK");
        shortsleeve13.setPrice(19350);
        shortsleeve13.setImageUrl("/image/shortsleeve_13.png");
        shortsleeve13.setCategory(shortsleeveCategory);

        // --- 상품 14: 오가닉 코튼 반팔티 ---
        Product shortsleeve14 = new Product();
        shortsleeve14.setName("헤븐 갤럭시 반팔 티셔츠-피그먼트 핑크");
        shortsleeve14.setPrice(32900);
        shortsleeve14.setImageUrl("/image/shortsleeve_14.png");
        shortsleeve14.setCategory(shortsleeveCategory);

        // --- 상품 15: 루즈핏 U넥 반팔티 ---
        Product shortsleeve15 = new Product();
        shortsleeve15.setName("노스텔지아 피그먼트 티셔츠_차콜");
        shortsleeve15.setPrice(27400);
        shortsleeve15.setImageUrl("/image/shortsleeve_15.png");
        shortsleeve15.setCategory(shortsleeveCategory);

        // --- 상품 16: 워크웨어 반팔티 ---
        Product shortsleeve16 = new Product();
        shortsleeve16.setName("Mm Symbol Logo T-Shirts (CHARCOAL / PINK");
        shortsleeve16.setPrice(34300);
        shortsleeve16.setImageUrl("/image/shortsleeve_16.png");
        shortsleeve16.setCategory(shortsleeveCategory);
        
        // ⭐️⭐️⭐️ 5. 상품과 옵션 연결 및 저장 ⭐️⭐️⭐️
        System.out.println(">>> 반소매 티셔츠 상품 16개 저장 시작...");

        productService.saveProduct(shortsleeve1, allOptions1);
        productService.saveProduct(shortsleeve2, allOptions2);
        productService.saveProduct(shortsleeve3, allOptions3);
        productService.saveProduct(shortsleeve4, allOptions4);
        productService.saveProduct(shortsleeve5, allOptions5);
        productService.saveProduct(shortsleeve6, allOptions6);
        productService.saveProduct(shortsleeve7, allOptions7);
        productService.saveProduct(shortsleeve8, allOptions8);
        productService.saveProduct(shortsleeve9, allOptions9);
        productService.saveProduct(shortsleeve10, allOptions10);
        productService.saveProduct(shortsleeve11, allOptions11);
        productService.saveProduct(shortsleeve12, allOptions12);
        productService.saveProduct(shortsleeve13, allOptions13);
        productService.saveProduct(shortsleeve14, allOptions14);
        productService.saveProduct(shortsleeve15, allOptions15);
        productService.saveProduct(shortsleeve16, allOptions16);

        System.out.println(">>> 반소매 티셔츠 상품 16개 저장 완료.");
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