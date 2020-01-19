package com.springboot.portfolio.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 이승환
 * @since 2020/01/19
 */
@Service
public class NotificationService {

    // ...

    private final Map<Long, String> tokenMap = new HashMap<>();

    // ...

    public void register(final Long userId, final String token) {
        tokenMap.put(userId, token);
    }
}