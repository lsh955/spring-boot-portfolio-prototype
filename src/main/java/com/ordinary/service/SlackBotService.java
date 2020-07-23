package com.ordinary.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordinary.enums.SlackChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author 이승환
 * @since 2020-07-22
 * <p>
 * 슬랙 봇 전송 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SlackBotService {

	private final ObjectMapper objectMapper;

	/**
	 * RestTemplate Post 방식으로 요청
	 *
	 * @param target
	 * @param object
	 */
	public void sendSlack(SlackChannel target, Object object) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity(target.getWebHookUrl(), writeValueAsString(object), String.class);
	}

	/**
	 * Slack Web Hook 최종전송
	 *
	 * @param obj
	 * @return
	 */
	private String writeValueAsString(Object obj) {
		try {
			objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("Occur JsonProcessingException: {}", e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
