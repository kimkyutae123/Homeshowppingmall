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
public class SweatshirtDataInitializer implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;

    public SweatshirtDataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>> [DB 초기화 - 맨투맨] 데이터 삽입 시작...");
        createSweatshirtDataIfNotExist();
        System.out.println(">>> [DB 초기화 - 맨투맨] 완료.");
    }

    private void createSweatshirtDataIfNotExist() {
        
        // 1. 상위 '상의' 카테고리를 찾거나 생성합니다.
        Category topCategory = categoryService.findCategoryByName("상의");
        if (topCategory == null) {
            System.out.println(">>> [DB 초기화 - 맨투맨] 상위 '상의' 카테고리가 없어 새로 생성합니다.");
            topCategory = new Category();
            topCategory.setName("상의");
            topCategory.setParentId(null);
            categoryService.saveCategory(topCategory);
        }
        
        // 2. 맨투맨/스웨트셔츠 카테고리를 찾거나 생성합니다.
        Category sweatshirtCategory = categoryService.findCategoryByName("맨투맨/스웨트셔츠");
        if (sweatshirtCategory == null) {
            System.out.println(">>> [DB 초기화 - 맨투맨] '맨투맨/스웨트셔츠' 카테고리를 생성합니다.");
            sweatshirtCategory = new Category();
            sweatshirtCategory.setName("맨투맨/스웨트셔츠");
            sweatshirtCategory.setParentId(topCategory.getId()); // 상위 카테고리에 연결
            categoryService.saveCategory(sweatshirtCategory);
        }

        // ⭐️⭐️⭐️ 수정된 중복 삽입 방지 로직: 자기 카테고리만 확인 ⭐️⭐️⭐️
        // 맨투맨 카테고리에 상품이 하나라도 있으면 건너뜁니다.
        if (productService.countProductsByCategory(sweatshirtCategory.getId()) > 0) { // ⭐️ 수정: 카테고리 ID 사용
            System.out.println(">>> [DB 초기화 - 맨투맨] 해당 카테고리 상품이 이미 존재하여 초기화 작업을 건너뜁니다.");
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

        // --- 상품 1: 기본 기모 맨투맨 ---
        Product sweatshirt1 = new Product();
        sweatshirt1.setName("오버사이즈 크루 스웨트셔츠 - 그린");
        sweatshirt1.setPrice(79000);
        sweatshirt1.setImageUrl("/image/sweatshirt_1.png"); 
        sweatshirt1.setCategory(sweatshirtCategory);

        // --- 상품 2: 스탠다드 로고 맨투맨 ---
        Product sweatshirt2 = new Product();
        sweatshirt2.setName("ARCH OG SWEATSHIRT black");
        sweatshirt2.setPrice(39500);
        sweatshirt2.setImageUrl("/image/sweatshirt_2.png"); 
        sweatshirt2.setCategory(sweatshirtCategory);

        // --- 상품 3: 유니버시티 스웨트셔츠 ---
        Product sweatshirt3 = new Product();
        sweatshirt3.setName("플로킹 로고 그래픽 맨투맨 (S25FMFHT64) Dark Charcoal");
        sweatshirt3.setPrice(84550);
        sweatshirt3.setImageUrl("/image/sweatshirt_3.png"); 
        sweatshirt3.setCategory(sweatshirtCategory);

        // --- 상품 4: 90's 크루넥 ---
        Product sweatshirt4 = new Product();
        sweatshirt4.setName("더블 다이아 기모 맨투맨 차콜 UQ423CFSO3");
        sweatshirt4.setPrice(109000);
        sweatshirt4.setImageUrl("/image/sweatshirt_4.png"); 
        sweatshirt4.setCategory(sweatshirtCategory);

        // --- 상품 5: V넥 배색 맨투맨 ---
        Product sweatshirt5 = new Product();
        sweatshirt5.setName("NM5MR54J 화이트라벨 다이나모 크루넥 BLACK");
        sweatshirt5.setPrice(89680);
        sweatshirt5.setImageUrl("/image/sweatshirt_5.png"); 
        sweatshirt5.setCategory(sweatshirtCategory);

        // --- 상품 6: 하프 집업 맨투맨 ---
        Product sweatshirt6 = new Product();
        sweatshirt6.setName("솔리드 럭비 스웻 셔츠");
        sweatshirt6.setPrice(44100);
        sweatshirt6.setImageUrl("/image/sweatshirt_6.png"); 
        sweatshirt6.setCategory(sweatshirtCategory);

        // --- 상품 7: 그래픽 포인트 스웨트셔츠 ---
        Product sweatshirt7 = new Product();
        sweatshirt7.setName("LEOPARD LABEL SWEATSHIRT BROWN (PKHAWSS006_BR)");
        sweatshirt7.setPrice(40950);
        sweatshirt7.setImageUrl("/image/sweatshirt_7.png"); 
        sweatshirt7.setCategory(sweatshirtCategory);

        // --- 상품 8: 테리 소재 맨투맨 ---
        Product sweatshirt8 = new Product();
        sweatshirt8.setName("TEAM ARCH COLLEGE SWEATSHIRT heather gray");
        sweatshirt8.setPrice(55300);
        sweatshirt8.setImageUrl("/image/sweatshirt_8.png"); 
        sweatshirt8.setCategory(sweatshirtCategory);

        // --- 상품 9: 빈티지 워싱 맨투맨 ---
        Product sweatshirt9 = new Product();
        sweatshirt9.setName("에든버러 풋볼 스웻셔츠 블랙 SETS012BLACK");
        sweatshirt9.setPrice(31400);
        sweatshirt9.setImageUrl("/image/sweatshirt_9.png"); 
        sweatshirt9.setCategory(sweatshirtCategory);

        // --- 상품 10: 시그니처 맨투맨 ---
        Product sweatshirt10 = new Product();
        sweatshirt10.setName("어센틱 릴렉스드 브루클린 맨투맨 스웨트 오트밀");
        sweatshirt10.setPrice(32300);
        sweatshirt10.setImageUrl("/image/sweatshirt_10.png"); 
        sweatshirt10.setCategory(sweatshirtCategory);

        // --- 상품 11: 톤온톤 맨투맨 ---
        Product sweatshirt11 = new Product();
        sweatshirt11.setName("(기모추가) 레더 패치 데님 카라 럭비 맨투맨_네이비");
        sweatshirt11.setPrice(37800);
        sweatshirt11.setImageUrl("/image/sweatshirt_11.png"); 
        sweatshirt11.setCategory(sweatshirtCategory);

        // --- 상품 12: 레터링 스웨트셔츠 ---
        Product sweatshirt12 = new Product();
        sweatshirt12.setName("피그먼트 헤비 스웨트 맨투맨_");
        sweatshirt12.setPrice(59900);
        sweatshirt12.setImageUrl("/image/sweatshirt_12.png"); 
        sweatshirt12.setCategory(sweatshirtCategory);

        // --- 상품 13: 피그먼트 다잉 맨투맨 ---
        Product sweatshirt13 = new Product();
        sweatshirt13.setName("93 Vintage Sweatshirt - Grey");
        sweatshirt13.setPrice(88200);
        sweatshirt13.setImageUrl("/image/sweatshirt_13.png"); 
        sweatshirt13.setCategory(sweatshirtCategory);

        // --- 상품 14: 라글란 루즈핏 맨투맨 ---
        Product sweatshirt14 = new Product();
        sweatshirt14.setName("아일렛 브이넥 스웻 셔츠 블랙");
        sweatshirt14.setPrice(42600);
        sweatshirt14.setImageUrl("/image/sweatshirt_14.png"); 
        sweatshirt14.setCategory(sweatshirtCategory);

        // --- 상품 15: 포켓 디테일 맨투맨 ---
        Product sweatshirt15 = new Product();
        sweatshirt15.setName("커시브 1984 자수 오버핏 맨투맨 GMT-150");
        sweatshirt15.setPrice(25800);
        sweatshirt15.setImageUrl("/image/sweatshirt_15.png"); 
        sweatshirt15.setCategory(sweatshirtCategory);

        // --- 상품 16: 베이직 솔리드 맨투맨 ---
        Product sweatshirt16 = new Product();
        sweatshirt16.setName("BASIC APPLIQUE HALF ZIP SWEATSHIRT NAVY");
        sweatshirt16.setPrice(59200);
        sweatshirt16.setImageUrl("/image/sweatshirt_16.png"); 
        sweatshirt16.setCategory(sweatshirtCategory);
        
        // ⭐️⭐️⭐️ 5. 상품과 옵션 연결 및 저장 ⭐️⭐️⭐️
        System.out.println(">>> 맨투맨 상품 16개 저장 시작...");

        productService.saveProduct(sweatshirt1, allOptions1);
        productService.saveProduct(sweatshirt2, allOptions2);
        productService.saveProduct(sweatshirt3, allOptions3);
        productService.saveProduct(sweatshirt4, allOptions4);
        productService.saveProduct(sweatshirt5, allOptions5);
        productService.saveProduct(sweatshirt6, allOptions6);
        productService.saveProduct(sweatshirt7, allOptions7);
        productService.saveProduct(sweatshirt8, allOptions8);
        productService.saveProduct(sweatshirt9, allOptions9);
        productService.saveProduct(sweatshirt10, allOptions10);
        productService.saveProduct(sweatshirt11, allOptions11);
        productService.saveProduct(sweatshirt12, allOptions12);
        productService.saveProduct(sweatshirt13, allOptions13);
        productService.saveProduct(sweatshirt14, allOptions14);
        productService.saveProduct(sweatshirt15, allOptions15);
        productService.saveProduct(sweatshirt16, allOptions16);

        System.out.println(">>> 맨투맨 상품 16개 저장 완료.");
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