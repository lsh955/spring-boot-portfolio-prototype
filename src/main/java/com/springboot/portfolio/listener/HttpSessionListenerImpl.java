package com.springboot.portfolio.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 이승환
 * @since 2019-12-17
 * <p>
 * 세션에 대한 이벤트가 발생하였을때 호출되어 처리하는 역할
 */
@Slf4j
@WebListener
@Component
public class HttpSessionListenerImpl implements HttpSessionListener {
    
    private int userCount;  // 로그인된 사용자 수 카운트(나중에 지울거임)
    
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {     // 세션이 생성되었을 때 호출
        httpSessionEvent.getSession().setMaxInactiveInterval(60 * 60);  // 세션만료 60분

        ++userCount;                                                    // 나중에 지울거임
        log.info("생성된 SESSION ID : " + httpSessionEvent.getSession().getId());    // 나중에 지울거임
        log.info("로그인된 사용자 수 : " + userCount + "명");                // 나중에 지울거임
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {   // 세션이 제거되었을 때 호출
        --userCount;                                                    // 나중에 지울거임
        log.info("제거된 SESSION ID : " + httpSessionEvent.getSession().getId());    // 나중에 지울거임
        log.info("로그인된 사용자 수 : " + userCount + "명");                // 나중에 지울거임
    }
    
}
