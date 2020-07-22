package com.ordinary.service;

import com.ordinary.repository.dto.CaptchaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author 이승환
 * @since 2020-07-21
 *
 * https://www.google.com/recaptcha/intro/v3.html
 */
@Service
@RequiredArgsConstructor
public class CaptchaService {

	/**
	 * Google Captcha 요청
	 *
	 * @param token
	 * @return
	 */
	public CaptchaDto googleCaptcha(String token) {
		// API요청
		String url = "https://www.google.com/recaptcha/api/siteverify";

		HttpHeaders headers = new HttpHeaders();
		// Content-Type : application/x-www-form-urlencoded
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// secret과 token값을 Post로 전송하기 위해 MultiValueMap으로 묶어서 처리
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", "6LfWFs8UAAAAAMng0MZUnuaYH83e5v6Jwv50Ci5T");
		map.add("response", token);

		// RestTemplate Post방식으로 토큰값, 비밀키값을 전송한다.
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		// RestTemplate Post 전송
		return restTemplate.postForObject( url, request, CaptchaDto.class );
	}

}
