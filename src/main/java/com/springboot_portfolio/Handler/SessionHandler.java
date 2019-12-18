package com.springboot_portfolio.Handler;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 이승환
 * @since 2019-12-17
 *
 * 다른 객체들이 보낸 메시지를 받고 이를 처리하는 객체
 */
@WebListener
public class SessionHandler implements HttpSessionListener {
    
    private int userCount;                              // 로그인된 사용자 수 카운트
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {   // 세션이 생성되었을 때 호출
        se.getSession().setMaxInactiveInterval(60*60);  // 세션만료 60분
        
        ++userCount;
        System.out.println("생성된 SESSION ID : " + se.getSession().getId());
        System.out.println("로그인된 사용자 수 : " +  userCount + "명");
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) { // 세션이 제거되었을 때 호출
        --userCount;
        System.out.println("제거된 SESSION ID : " + se.getSession().getId());
        System.out.println("로그인된 사용자 수 : " + userCount + "명");
    }
    
}
