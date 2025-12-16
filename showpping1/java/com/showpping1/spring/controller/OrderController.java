package com.showpping1.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.showpping1.spring.domain.CartItem;
import com.showpping1.spring.domain.User;
import com.showpping1.spring.service.CartService;
import com.showpping1.spring.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order") // 기본 경로를 /order로 설정 (더 명확함)
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
        
    }

    /**
     * 결제/주문 페이지를 로드합니다.
     * 경로: GET /order/checkout
     */
    @GetMapping("/checkout")
    public String showCheckoutPage(HttpSession session, Model model) {
        
        User loggedInUser = (User) session.getAttribute("user");

        // 1. 로그인 확인 (필수)
        if (loggedInUser == null) {
            session.setAttribute("redirectTo", "/order/checkout"); 
            return "redirect:/login"; 
        }
        
        Long userId = loggedInUser.getId();

        // 2. 장바구니 항목 조회
        List<CartItem> cartItems = cartService.findCartItems(userId);
        
        if (cartItems.isEmpty()) {
            // 장바구니가 비었으면 메시지와 함께 장바구니 페이지로 리다이렉트
            model.addAttribute("error", "장바구니가 비어 있습니다.");
            return "redirect:/cart"; 
        }
        
        // 3. 결제 금액 계산 (CartUtils를 사용하여 서버에서 최종 계산)
        // 주의: 이 계산은 CartUtils가 서버에 존재해야 합니다!
        long subtotal = com.showpping1.spring.util.CartUtils.calculateSubtotal(cartItems);
        int shippingFee = com.showpping1.spring.util.CartUtils.getShippingFee(cartItems);
        long totalAmount = subtotal + shippingFee;

        // 4. 모델에 데이터 추가
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("shippingFee", shippingFee);
        
        // 5. checkout.html 템플릿 반환
        return "checkout"; 
    }
    @PostMapping("/submit")
    public String submitOrder(
            @RequestParam("paymentMethod") String paymentMethod,
            HttpSession session,
            RedirectAttributes rttr) 
    {
        User loggedInUser = (User) session.getAttribute("user");
        
        
        // 1. 로그인 확인 (세션이 끊겼을 경우 대비)
        if (loggedInUser == null) {
            return "redirect:/login"; // 로그인 페이지로 돌려보냄
        }
        
        Long userId = loggedInUser.getId();
        
        List<CartItem> cartItems = cartService.findCartItems(userId);
        if(cartItems.isEmpty()) {
        	rttr.addFlashAttribute("error", "장바구니가 비어 있습니다");
        	return "redirect:/cart";
        }
        try {
        	System.out.println("결제 진행 - 결제 수단: " +paymentMethod);
        	
        	orderService.processPaymentAndClearCart(userId, cartItems);
        	
        	rttr.addFlashAttribute("successMessage", "결제가 완료되었습니다! 제고가 차감됩니다");
        } catch(Exception e) {
        	System.err.println("결제 처리 중 오류 발생: " + e.getMessage());
            rttr.addFlashAttribute("errorMessage", "결제 처리 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cart";
        }
        
        return "redirect:/";
        
    }
}