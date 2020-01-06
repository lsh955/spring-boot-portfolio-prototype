package com.springboot.portfolio.controller;

import com.springboot.portfolio.dao.UserPrincipal;
import com.springboot.portfolio.dto.User;
import com.springboot.portfolio.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 컨트롤러 클래스 모음.
 */
@Controller
public class UserController {
    
    @Autowired
    private UserDetailsServiceImpl userService;                        // 사용자 액세스를위한 서비스 개체
    
    /**
     * ModelAndView
     * 메인연결
     */
    @GetMapping("/")                              // GET으로 파라미터를 전달받는다.
    public String getindex(Model model) {
        model.addAttribute("LoginMessage", "아이디와 비밀번호 그리고 자동등록방지를 체크해주세요.");      // 뷰로 보낼 데이터 값
        return "index";
    }
    
    /**
     * ModelAndView
     * 로그인 입력 처리
     */
    @GetMapping("login")
    public String getLoginPage(Model model) {
        return "index";
    }
    
    /**
     * ModelAndView
     * 회원가입 처리
     */
    @GetMapping("registration")
    public String getRegistrationPage(Model model) {
        User user = new User();                             // 회원 데이터
        model.addAttribute("user", user);      // 뷰로 보낼 데이터 값
        return "registration";
    }
    
    /**
     * ModelAndView
     * 회원가입 정보를 보내는 처리
     *
     * @param bindingResult 모델의 바인딩 작업 중에 발생한 타입 변환 오류정보와 검증 작업에서 발생한 검증 오류 정보가 모두 저장된다.
     *                      오류 정보는 보통 컨트롤러에 의해 폼을 다시 띄울 때 활용된다.
     *                      폼을 출력할 때 BindingResult에 담긴 오류 정보를 활용해서 에러 메시지를 생성할 수 있다.
     */
    @PostMapping("registration")                                                // POST으로 파라미터를 전달받는다.
    public String createNewUser(Model model, @Valid User user, BindingResult bindingResult) {
        User userExists = userService.findUserByLoginId(user.getLoginId());     // User지정된 로그인 해당 ID, null값을 반환한다.
        if (userExists != null) {
            // 필드에 대한 에러코드를 추가 에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
            bindingResult.rejectValue("loginId", "error.loginId", "이미 등록 된 사용자가 있습니다");
        }
        if (bindingResult.hasErrors()) {
            // 에러가 발생할경우 setViewName에 지정된 뷰로 이동한다.
            return "registration";
        } else {
            // 회원가입이 정상적으로 등록될 경우
            userService.saveUser(user);
            model.addAttribute("successMessage", "사용자가 성공적으로 등록되었습니다");      // 뷰로 보낼 데이터 값
            model.addAttribute("user", new User());                                                     // 뷰로 보낼 데이터 값
        }
        return "registration";
    }
    
    /**
     * ModelAndView
     * 인증 후 권한이 있는 처리
     */
    @GetMapping("home")                                     // GET으로 파라미터를 전달받는다.
    public String home(Model model) {
        
        /**
         * @param Authentication
         * 현재 요청에 연결된 Authentication을 얻으려면 SecurityContextHolder.getContext(). getAuthentication()으로 얻는다.
         * @param SecurityContextHolder
         * SecurityContextHolder.getContext()는 현재 요청에 연결된 SecurityContext를 반환한다.
         */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        /**
         * @param UserPrincipal
         * 객체에 저장 된 정보를 사용하여 인증 및 권한부여를 수행.
         * Authentication 객체의 getPrincipal() 메서드를 실행하게 되면, UserDetails를 구현한 사용자 객체를 Return 한다.
         */
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        
        model.addAttribute("userName", "환영합니다. " + userPrincipal.getName() + " (" + userPrincipal.getId() + ")");   // 뷰로 보낼 데이터 값
        model.addAttribute("adminMessage", "관리자 역할을 가진 사용자의 사용 가능한 콘텐츠");                              // 뷰로 보낼 데이터 값
        return "home";
    }
    
    /**
     * ModelAndView
     * 예외가 발행했을 경우
     */
    @GetMapping("exception")                                // GET으로 파라미터를 전달받는다.
    public String getUserPermissionExceptionPage() {
        return "access-denied";
    }
    
}