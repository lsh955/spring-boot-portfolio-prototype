package com.ordinary.service;

import com.ordinary.repository.dto.reCaptcha;
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
public class ReCaptchaService {

	public reCaptcha token(String token) {
		String url = "https://www.google.com/recaptcha/api/siteverify";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", "6LfWFs8UAAAAAMng0MZUnuaYH83e5v6Jwv50Ci5T&response");
		map.add("response", token);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		reCaptcha response = restTemplate.postForObject( url, request, reCaptcha.class );

		return response;
	}

}
