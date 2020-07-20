package com.portfolio.controller;

import com.portfolio.dao.UserDao;
import com.portfolio.dto.reCaptcha;
import com.portfolio.service.account.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserDao userDao;
	private final SignUpService signUpService;

	/**
	 * 메인
	 */
	@GetMapping({"/", "/index"})
	public String getIndex(HttpServletRequest request) {

		String header = request.getHeader("User-Agent");

		// IE환경 검증
		if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1) {
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

		UserDao userDao = new UserDao();						// 회원 데이터
		model.addAttribute("userDao", userDao);	// 뷰로 보낼 데이터 값
		return "signup";

	}

	/**
	 * 회원가입 요청
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("setsignup")
	public String getSetSignUp(Model model, @Valid UserDao userDao, BindingResult bindingResult, HttpServletRequest request) {

		String ipAddress = request.getRemoteAddr();
		log.info(">> Result : IP Address : " + ipAddress);

		model.addAttribute("user", new UserDao());
		userDao.setUserIpAddress(ipAddress);

		if(signUpService.SignUpIdCheck(userDao).equals("Success")) {
			return "index";			// 회원가입 성공
		}else {
			return "signup";		// 아이디 중복
		}

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
	public reCaptcha reCAPTCHA(@RequestParam(name = "g-recaptcha-response") String recaptchaResponse, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LfWFs8UAAAAAMng0MZUnuaYH83e5v6Jwv50Ci5T&response=" + recaptchaResponse;

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