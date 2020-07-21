package com.ordinary.service;

import com.ordinary.repository.dto.GoogleCaptcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaService {

	public GoogleCaptcha googleCaptcha(String token) {
		String url = "https://www.google.com/recaptcha/api/siteverify";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", "6LfWFs8UAAAAAMng0MZUnuaYH83e5v6Jwv50Ci5T&response");
		map.add("response", token);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		return restTemplate.postForObject( url, request, GoogleCaptcha.class );
	}

}
