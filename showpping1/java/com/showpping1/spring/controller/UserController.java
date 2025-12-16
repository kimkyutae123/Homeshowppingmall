package com.showpping1.spring.controller;

import com.showpping1.spring.domain.User;
import com.showpping1.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ------------------- 회원가입 -------------------

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User()); // Thymeleaf 폼 바인딩을 위한 빈 객체
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            userService.registerUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "register"; // 실패 시 회원가입 페이지에 에러 메시지와 함께 남음
        }
        // 성공 시 로그인 페이지로 이동
        return "redirect:/login"; 
    }

    // ------------------- 로그인 -------------------

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request,
            Model model) {

        User authenticatedUser = userService.authenticate(username, password)
            .orElse(null);

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);
            
            // ⭐️ 수정: 세션에 저장된 'redirectTo' URL 확인 후 이동 ⭐️
            String redirectTo = (String) session.getAttribute("redirectTo");
            
            // 사용 후 세션에서 제거 (중요!)
            session.removeAttribute("redirectTo"); 

            if (redirectTo != null && !redirectTo.isEmpty()) {
                // 저장된 URL이 있으면 그곳으로 이동 (예: /cart)
                return "redirect:" + redirectTo; 
            } else {
                // 저장된 URL이 없으면 메인 페이지로 이동
                return "redirect:/"; 
            }

        } else {
            // 인증 실패
            model.addAttribute("error", "사용자 ID 또는 비밀번호가 일치하지 않습니다.");
            return "login"; 
        }
    }

    // ------------------- 로그아웃 -------------------

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션 무효화
        request.getSession().invalidate();
        return "redirect:/"; // 메인 페이지로 리다이렉트
    }
}