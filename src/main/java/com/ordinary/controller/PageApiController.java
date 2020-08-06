package com.ordinary.controller;

import com.ordinary.enums.SlackChannel;
import com.ordinary.repository.dto.CaptchaDto;
import com.ordinary.repository.dto.SlackBasicDto;
import com.ordinary.service.CaptchaService;
import com.ordinary.service.SlackBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    private final CaptchaService captcha;
    private final SlackBotService slackBotService;

    /**
     * Google reCaptcha
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "token", method = POST)
    public @ResponseBody
    CaptchaDto getToken(@RequestParam("token") String token) {
        return captcha.googleCaptcha(token);
    }

    /**
     * SlackBot(기본 TEXT만 전송)
     *
     * @param dto
     */
    @RequestMapping(value = "slack", method = POST)
    public void basic(@RequestBody SlackBasicDto dto) {
        slackBotService.sendSlack(SlackChannel.TARGET, dto);
    }

    //TODO : 로그인 후 로그인정보 알림
    @RequestMapping(value = "welcome", method = POST)
    public void getWelcome() {

    }

}
