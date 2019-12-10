package com.springboot_portfolio.controller;

import com.springboot_portfolio.domain.UserPrincipal;
import com.springboot_portfolio.service.UserService;
import com.springboot_portfolio.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;                        // 서비스레이어에서 세분화된 비즈니스 로직
    
    // 메인화면
    @GetMapping(value = {"/"})                              // GET으로 파라미터를 전달받는다.
    public ModelAndView getindex() {
        ModelAndView modelAndView = new ModelAndView();     // "ModelAndView"객체는 Model과 View가 모두리턴
        modelAndView.setViewName("index");                  // "setViewName"뷰 이름 설정
        return modelAndView;
    }
    
    // 로그인
    @GetMapping(value = {"login"})                          // GET으로 파라미터를 전달받는다.
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();     // "ModelAndView"객체는 Model과 View가 모두리턴
        modelAndView.setViewName("index");                  // "setViewName"뷰 이름 설정
        return modelAndView;
    }
    
    @GetMapping("registration")                             // GET으로 파라미터를 전달받는다.
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();     // "ModelAndView"객체는 Model과 View가 모두리턴
        User user = new User();                             // 회원 데이터
        modelAndView.addObject("user", user);   // 뷰로 보낼 데이터 값
        modelAndView.setViewName("registration");           // "setViewName"뷰 이름 설정
        return modelAndView;
    }
    
    @PostMapping("registration")                            // POST으로 파라미터를 전달받는다.
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();     // "ModelAndView"객체는 Model과 View가 모두리턴
        User userExists = userService.findUserByLoginId(user.getLoginId());
        if (userExists != null) {
            bindingResult.rejectValue("loginId", "error.loginId", "There is already a user registered with the loginId provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");       // "setViewName"뷰 이름 설정
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");      // 뷰로 보낼 데이터 값
            modelAndView.addObject("user", new User());                                                         // 뷰로 보낼 데이터 값
            modelAndView.setViewName("registration");       // "setViewName"뷰 이름 설정
        }
        return modelAndView;
    }
    
    @GetMapping("home")                                     // GET으로 파라미터를 전달받는다.
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();     // "ModelAndView"객체는 Model과 View가 모두리턴
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        System.out.println(userPrincipal.toString());       // 데이터를 찍어본다.

        modelAndView.addObject("userName", "환영합니다. " + userPrincipal.getName() + " (" + userPrincipal.getId() + ")");   // 뷰로 보낼 데이터 값
        modelAndView.addObject("adminMessage", "관리자 역할을 가진 사용자의 사용 가능한 콘텐츠");                                    // 뷰로 보낼 데이터 값
        modelAndView.setViewName("home");                   // "setViewName"뷰 이름 설정
        return modelAndView;
    }
    
    @GetMapping("exception")                                // GET으로 파라미터를 전달받는다.
    public ModelAndView getUserPermissionExceptionPage() {
        ModelAndView modelAndView = new ModelAndView();     // "ModelAndView"객체는 Model과 View가 모두리턴
        modelAndView.setViewName("access-denied");          // "setViewName"뷰 이름 설정
        return modelAndView;
    }
    
}
