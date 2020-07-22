package com.ordinary.controller;

import com.ordinary.enums.SlackChannel;
import com.ordinary.repository.dto.SlackBasicDto;
import com.ordinary.service.SlackBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author 이승환
 * @since 2020-07-20
 */
@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PageApiController {

	private final SlackBotService slackBotService;

	/**
	 * SlackBot - 기본 TEXT 전송형식
	 *
	 * @param dto
	 */
	@RequestMapping(value = "slack", method = POST)
	public void basic(@RequestBody SlackBasicDto dto) {
		slackBotService.sendSlack(SlackChannel.TARGET_CH, dto);
	}


	//TODO : 로그인 후 웰컴 메시지 띄우기

}
