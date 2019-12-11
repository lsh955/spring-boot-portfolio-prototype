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
        modelAndView.addObject("user", user);  // 뷰로 보낼 데이터 값
        modelAndView.setViewName("registration");           // "setViewName"뷰 이름 설정
        return modelAndView;
    }
    
    /**
     * @param bindingResult
     * 모델의 바인딩 작업 중에 발생한 타입 변환 오류정보와 검증 작업에서 발생한 검증 오류 정보가 모두 저장된다.
     * 오류 정보는 보통 컨트롤러에 의해 폼을 다시 띄울 때 활용된다.
     * 폼을 출력할 때 BindingResult에 담긴 오류 정보를 활용해서 에러 메시지를 생성할 수 있다.
     */
    @PostMapping("registration")                                                // POST으로 파라미터를 전달받는다.
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();                         // "ModelAndView"객체는 Model과 View가 모두리턴
        User userExists = userService.findUserByLoginId(user.getLoginId());     // User지정된 로그인 해당 ID, null값을 반환한다.
        if (userExists != null) {
            // 필드에 대한 에러코드를 추가 에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
            bindingResult.rejectValue("loginId", "error.loginId", "이미 등록 된 사용자가 있습니다");
        }
        if (bindingResult.hasErrors()) {
            // 에러가 발생할경우 setViewName에 지정된 뷰로 이동한다.
            modelAndView.setViewName("registration");       // "setViewName"뷰 이름 설정
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "사용자가 성공적으로 등록되었습니다");      // 뷰로 보낼 데이터 값
            modelAndView.addObject("user", new User());                                                     // 뷰로 보낼 데이터 값
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
        modelAndView.addObject("adminMessage", "관리자 역할을 가진 사용자의 사용 가능한 콘텐츠");                              // 뷰로 보낼 데이터 값
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