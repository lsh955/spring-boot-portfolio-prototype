package com.ordinary.controller;

import com.ordinary.repository.dao.UserDao;
import com.ordinary.repository.dto.reCaptcha;
import com.ordinary.service.account.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 컨트롤러 클래스 모음.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

	private final SignUpService signUpService;

	/**
	 * 메인 페이지
	 */
	@GetMapping({"/", "/index"})
	public String getIndex(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1) {	// IE환경 검증
			return "browser_issue"; // 브라우저 업그레이드 권장페이지
		} else {
			return "index"; // 메인페이지
		}
	}

	/**
	 * 회원가입 처리
	 */
	@GetMapping("signup")
	public String getSignUpPage(Model model) {
		UserDao userDao = new UserDao();                        // 회원 데이터
		model.addAttribute("userDao", userDao);    // 뷰로 보낼 데이터 값
		return "signup";
	}

	/**
	 * 회원가입 요청
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("sendsignup")
	public String getSendSignUp(@Valid UserDao userDao, HttpServletRequest request) {
		if (signUpService.SignUpIdCheck(userDao).equals("Overlap")) {
			return "signup";	// 회원가입 실패(아이디중복)
		}
		userDao.setUserIpAddress(request.getRemoteAddr());	// 접속 IP Address
		return "index";		// 회원가입 성공
	}

	/**
	 * 중복로그인 페이지
	 */
	@GetMapping("sessionfailed")
	public String sessionfailed() {
		return "sessionfailed";
	}

	/**
	 * 예외 발생 페이지
	 */
	@GetMapping("exception")
	public String getUserPermissionExceptionPage() {
		return "accessdenied";
	}

	/**
	 * 구글 리캡챠
	 *
	 * @param reCaptcha
	 * @return
	 */
	@PostMapping("reCAPTCHA")
	public reCaptcha reCAPTCHA(@RequestParam(name = "g-recaptcha-response") String reCaptcha) {
		String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LfWFs8UAAAAAMng0MZUnuaYH83e5v6Jwv50Ci5T&response=" + reCaptcha;
		RestTemplate restTemplate = new RestTemplate();
		reCaptcha recaptcha = restTemplate.exchange(url + params, HttpMethod.POST, null, reCaptcha.class).getBody();
		assert recaptcha != null;
		if (recaptcha.isSuccess()) {
			log.info("reCAPTCHA 성공");
		} else {
			log.info("reCAPTCHA 실패");
		}
		return recaptcha;
	}

}