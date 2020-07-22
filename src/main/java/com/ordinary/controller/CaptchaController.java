package com.ordinary.controller;

import com.ordinary.repository.dto.CaptchaDto;
import com.ordinary.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 이승환
 * @since 2020-07-21
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class CaptchaController {

	private final CaptchaService captcha;

	/**
	 * 구글 리캡챠
	 *
	 * @param token
	 * @return
	 */
	@PostMapping("/token")
	public @ResponseBody
	CaptchaDto getToken(@RequestParam("token") String token) {
		return captcha.googleCaptcha(token);
	}

}
