package com.springboot.portfolio.controller;

import com.springboot.portfolio.details.UserDetailsImpl;
import com.springboot.portfolio.dto.User;
import com.springboot.portfolio.dto.reCAPTCHA;
import com.springboot.portfolio.service.EmailSendService;
import com.springboot.portfolio.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 컨트롤러 클래스 모음.
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // 사용자 액세스를위한 서비스 개체

    @Autowired
    private EmailSendService emailSendService;

    private int getLoginJsonCount;

    /**
     * 메인
     */
    @GetMapping("/")
    public String getIndex() {

//        TODO 로그인할 때 DB에 사용자의 아이피를 지속 업데이트 한다.
//        String ipAddress = request.getHeader("X-Forwarded-For");
//        if (ipAddress == null) {
//            ipAddress = request.getRemoteAddr();
//        }
//        System.out.println("현재 로그인된 아이피 : " + ipAddress);
//        user.setUserIpAddress(ipAddress);    // 로그인 할때 접속한 아이피를 저장한다.

        return "index";
    }

    /**
     * 로그인 되고있는 사용자 정보를 세션으로 불러오는 역할
     * JSON으로 뿌려 클라이언트 AJAX로 뿌린다.
     */
//    @ResponseBody
//    @GetMapping("/jsonloginselect")
//    public List<User> jsonReturnSample(User user){
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         System.out.print(authentication.getName());
//         return userDetailsService.loginSelect();
//    }

    /**
     * 회원가입 처리
     */
    @GetMapping("signup")
    public String getSignUpPage(Model model) {
        User user = new User();                             // 회원 데이터
        model.addAttribute("user", user);      // 뷰로 보낼 데이터 값
        return "signup";
    }

    /**
     * 관리자 페이지
     */
    @GetMapping("manager")
    public String getAdminPage() {
        return "manager";
    }

    /**
     * 회원가입 정보를 보내는 처리
     *
     * @param bindingResult 모델의 바인딩 작업 중에 발생한 타입 변환 오류정보와 검증 작업에서 발생한 검증 오류 정보가 모두 저장된다.
     *                      오류 정보는 보통 컨트롤러에 의해 폼을 다시 띄울 때 활용된다.
     *                      폼을 출력할 때 BindingResult에 담긴 오류 정보를 활용해서 에러 메시지를 생성할 수 있다.
     */
    @PostMapping("signup")
    public String createNewUser(Model model, @Valid User user, BindingResult bindingResult, HttpServletRequest req) {
        User userExists = userDetailsService.findUserByLoginId(user.getLoginId());     // User지정된 로그인 해당 ID, null값을 반환한다.
        if (userExists != null) {
            // 필드에 대한 에러코드를 추가 에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
            bindingResult.rejectValue("loginId", "error.loginId", "이미 등록 된 사용자가 있습니다");
        }
        if (bindingResult.hasErrors()) {
            // 에러가 발생할경우 setViewName에 지정된 뷰로 이동한다.
            return "signup";
        } else {
            // 회원가입이 정상적으로 등록될 경우
            userDetailsService.saveUser(user, req);
            model.addAttribute("successMessage", "사용자가 성공적으로 등록되었습니다");      // 뷰로 보낼 데이터 값
            model.addAttribute("user", new User());                                                     // 뷰로 보낼 데이터 값
        }
        return "signup";
    }

    /**
     * 인증 후 권한이 있는 처리
     */
    @GetMapping("home")
    public String home(Model model) {

        /**
         * @param Authentication
         * 현재 요청에 연결된 Authentication을 얻으려면 SecurityContextHolder.getContext(). getAuthentication()으로 얻는다.
         * @param SecurityContextHolder
         * SecurityContextHolder.getContext()는 현재 요청에 연결된 SecurityContext를 반환한다.
         */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.info("auth.getName : " + auth.getName());
        log.info("auth.getAuthorities : " + auth.getAuthorities());
        log.info("auth.getDetails : " + auth.getDetails());

        /**
         * @param UserPrincipal
         * 객체에 저장 된 정보를 사용하여 인증 및 권한부여를 수행.
         * Authentication 객체의 getPrincipal() 메서드를 실행하게 되면, UserDetails를 구현한 사용자 객체를 Return 한다.
         */
        UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();

        log.info("getId : " + userPrincipal.getId());
        log.info("getUsername : " + userPrincipal.getUsername());
        log.info("getAuthorities : " + userPrincipal.getAuthorities());
        log.info("getRoles : " + userPrincipal.getRoles());
        log.info("isAccountNonExpired : " + userPrincipal.isAccountNonExpired());
        log.info("isAccountNonLocked : " + userPrincipal.isAccountNonLocked());
        log.info("isCredentialsNonExpired : " + userPrincipal.isCredentialsNonExpired());
        log.info("isEnabled : " + userPrincipal.isEnabled());


        model.addAttribute("userName", "환영합니다. " + userPrincipal.getUsername() + " (" + userPrincipal.getId() + ")");   // 뷰로 보낼 데이터 값
        model.addAttribute("contentsMessage", "권한을 가진 사용 가능한 콘텐츠");                                          // 뷰로 보낼 데이터 값
        return "home";
    }

    /**
     * 예외가 발행했을 경우
     */
    @GetMapping("exception")
    public String getUserPermissionExceptionPage() {
        return "accessdenied";
    }

    /**
     * 중복로그인이 감지되면 보여주는 페이지
     */
    @GetMapping("sessionfailed")
    public String sessionfailed() {
        return "sessionfailed";
    }

    @PostMapping("reCAPTCHA")
    public reCAPTCHA reCAPTCHA(@RequestParam(name = "g-recaptcha-response") String recaptchaResponse, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LfWFs8UAAAAAMng0MZUnuaYH83e5v6Jwv50Ci5T&response=" + recaptchaResponse;

        RestTemplate restTemplate = new RestTemplate();

        reCAPTCHA recaptcha = restTemplate.exchange(url + params, HttpMethod.POST, null, reCAPTCHA.class).getBody();

        if (recaptcha.isSuccess()) {
            System.out.println("reCAPTCHA 성공");
        } else {
            System.out.println("reCAPTCHA 실패");
        }
        return recaptcha;
    }

    @RequestMapping("/getLoginJson")
    public @ResponseBody
    Map<String, Object> getLoginJson() {
        Map<String, Object> jsonObject = new HashMap<String, Object>();
        Map<String, Object> jsonSubObject = null;
        ArrayList<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
        try {
            // TODO : 현재 같은 브라우저에서 계속 접속했을 때 안뜬다. 즉, 1회성 코드
            // 인증된 사용자의 세션정보 알고 정보를 가져오기 위해 SecurityContextHolder를 이용한다.
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();
            if (userPrincipal.getId() != null) {
                getLoginJsonCount++;
                if (getLoginJsonCount < 2) {
                    System.out.println(getLoginJsonCount);
                    jsonSubObject = new HashMap<String, Object>();
                    jsonSubObject.put("Id", userPrincipal.getId());
                    jsonSubObject.put("UserType", userPrincipal.getUserType());
                    jsonSubObject.put("Username", userPrincipal.getUsername()); // 이것만 살려두고 추후에 다 삭제할것.
                    jsonSubObject.put("LoginId", userPrincipal.getLoginId());
                    jsonSubObject.put("UserEmail", userPrincipal.getUserEmail());
                    jsonSubObject.put("UserFirstDate", userPrincipal.getUserFirstDate());
                    jsonSubObject.put("UserLoginDate", userPrincipal.getUserLoginDate());
                    jsonSubObject.put("UserIpAddress", userPrincipal.getUserIpAddress());
                    jsonSubObject.put("isAccountNonExpired", userPrincipal.isAccountNonExpired());
                    jsonSubObject.put("isAccountNonLocked", userPrincipal.isAccountNonLocked());
                    jsonSubObject.put("isCredentialsNonExpired", userPrincipal.isCredentialsNonExpired());
                    jsonSubObject.put("isEnabled", userPrincipal.isEnabled());
                    jsonList.add(jsonSubObject);

                    jsonObject.put("success", true);
                    jsonObject.put("total_count", 12);
                    jsonObject.put("LoginJsonCount", getLoginJsonCount);
                    jsonObject.put("result_list", jsonList);
                }
            }
        } catch (Exception e) {

        }
        return jsonObject;
    }


}