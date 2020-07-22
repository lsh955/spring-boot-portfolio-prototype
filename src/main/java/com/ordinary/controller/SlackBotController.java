package com.ordinary.controller;

import com.ordinary.enums.SlackTarget;
import com.ordinary.repository.dto.SlackBotDto;
import com.ordinary.service.SlackBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author 이승환
 * @since 2020-07-22
 * 
 * 슬랙 봇 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("slack")
@RequiredArgsConstructor
public class SlackBotController {

	private final SlackBotService slackBotService;

	@RequestMapping(value = "attachment", method = POST)
	public void attachment(@RequestBody SlackBotDto dto) {
		slackBotService.sendSlack(SlackTarget.CH_BOT, dto);
	}

}
