package com.ordinary.controller;

import com.ordinary.repository.dto.reCaptcha;
import com.ordinary.service.ReCaptchaService;
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
public class RecaptchaController {

	private final ReCaptchaService recaptchaService;

	@PostMapping("/token")
	public @ResponseBody
	reCaptcha token(@RequestParam("token") String token) {
		log.info("token : " + token);
		return recaptchaService.token(token);
	}

}
