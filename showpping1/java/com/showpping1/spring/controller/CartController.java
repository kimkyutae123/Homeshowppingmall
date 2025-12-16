package com.showpping1.spring.controller;

import java.security.Principal; // 로그인 사용자 정보
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.showpping1.spring.domain.CartItem;
import com.showpping1.spring.domain.User;
import com.showpping1.spring.dto.CartItemRequest;
import com.showpping1.spring.dto.CartUpdateDto;
import com.showpping1.spring.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart") 
public class CartController {

    private final CartService cartService;
    
    // ⭐️ CartService 주입을 위한 생성자
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ----------------------------------------------------
    // 1. 장바구니 페이지 로드 (GET /cart)
    // ----------------------------------------------------
    @GetMapping
    public String getCartPage(HttpSession session, Model model) {
        
        // ⭐️ Principal 대신 HttpSession에서 "user" 객체를 확인 ⭐️
        User loggedInUser = (User) session.getAttribute("user");
        
        if (loggedInUser == null) {
            session.setAttribute("redirectTo", "/cart"); 
            return "redirect:/login"; 
        }
        
        Long userId = loggedInUser.getId(); 

        List<CartItem> cartItems = cartService.findCartItems(userId); 
        model.addAttribute("cartItems", cartItems);
        
        return "cart"; 
    }

    // ----------------------------------------------------
    // 2. 장바구니에 항목 추가 (POST /cart/add)
    // ----------------------------------------------------
    @PostMapping("/add")
    @ResponseBody 
    public ResponseEntity<Map<String, Object>> addItemToCart(
            @RequestBody CartItemRequest request,
            // Principal 대신 HttpSession을 사용하거나, 두 가지 모두 사용하는 것이 안전합니다.
            HttpSession session) // ⭐️ Principal 대신 HttpSession을 인수로 받거나 추가 ⭐️
    {
        // ⭐️ 세션에서 로그인 사용자 객체 확인
        User loggedInUser = (User) session.getAttribute("user");
        
        if (loggedInUser == null) {
            // ⭐️ 로그인 안 된 경우: 401 상태 코드 반환 (JS가 401을 처리하도록 유도)
            Map<String, Object> error = new HashMap<>();
            error.put("status", "unauthorized");
            error.put("message", "로그인이 필요합니다.");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED); // ⭐️ 401 반환!
        }

        try {
            // ⭐️ 로그인된 사용자 ID를 세션에서 가져온 User 객체에서 추출
            Long userId = loggedInUser.getId(); 

            // Service에 로직 위임
            cartService.addItem(userId, request.getOptionId(), request.getQuantity());

            // 200 OK 응답 반환
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "장바구니에 상품이 담겼습니다.");
            
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "장바구니 추가 중 오류 발생: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); 
        }
    }

    // ----------------------------------------------------
    // 3. 장바구니 항목 수량 변경 (POST /cart/update)
    // ----------------------------------------------------
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateCartItemQuantity(
            @RequestBody CartUpdateDto updateDto) 
    {
        try {
            // Service에 수량 변경 로직 위임
            cartService.updateQuantity(updateDto.getCartItemId(), updateDto.getQuantity());
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "수량이 성공적으로 변경되었습니다.");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "수량 변경 중 오류 발생: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); 
        }
    }
    
    // ----------------------------------------------------
    // 4. 장바구니 항목 삭제 (POST /cart/delete/{itemId})
    // ----------------------------------------------------
    @PostMapping("/delete/{cartItemId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteCartItem(
            @PathVariable("cartItemId") Long cartItemId) 
    {
        try {
            // Service에 삭제 로직 위임
            cartService.removeItem(cartItemId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "장바구니 항목이 삭제되었습니다.");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "장바구니 삭제 중 오류 발생: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); 
        }
    }
}