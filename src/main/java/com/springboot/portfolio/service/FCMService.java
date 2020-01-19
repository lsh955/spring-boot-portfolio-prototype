//package com.springboot.portfolio.service;
//
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.WebpushConfig;
//import com.google.firebase.messaging.WebpushNotification;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import sun.plugin2.message.Message;
//
//import java.util.concurrent.ExecutionException;
//
///**
// * @author 이승환
// * @since 2020/01/19
// */
//@Slf4j
//@Service
//public class FCMService {
//
//    public void send(final NotificationRequest notificationRequest) throws InterruptedException, ExecutionException {
//        Message message = Message.builder()
//                .setToken(notificationRequest.getToken())
//                .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
//                        .setNotification(new WebpushNotification(notificationRequest.getTitle(),
//                                notificationRequest.getMessage()))
//                        .build())
//                .build();
//
//        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
//        log.info("Sent message: " + response);
//    }
//
//}