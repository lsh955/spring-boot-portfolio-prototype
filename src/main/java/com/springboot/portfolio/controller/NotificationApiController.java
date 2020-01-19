package com.springboot.portfolio.controller;

import com.springboot.portfolio.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 이승환
 * @since 2020/01/19
 */
@RestController
public class NotificationApiController {

//    private final NotificationService notificationService;
//
//    public NotificationApiController(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody String token, @LoginUser UserSession userSession) {
//        notificationService.register(userSession.getId(), token);
//        return ResponseEntity.ok().build();
//    }

}