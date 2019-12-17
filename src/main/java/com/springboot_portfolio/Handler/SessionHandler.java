package com.springboot_portfolio.Handler;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 이승환
 * @since 2019-12-17
 */
public class SessionHandler implements HttpSessionListener {
    
    private int userCount;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {   // 세션이 생성되었을 때 호출
        se.getSession().setMaxInactiveInterval(60*60);  // 세션만료 60분
        
        ++userCount;
        System.out.printf("생성된 SESSIONID %s \n",  se.getSession().getId());
        System.out.printf("로그인된 사용자 수 : %d \n", userCount);
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) { // 세션이 제거되었을 때 호출
        --userCount;
        System.out.printf("제거된 SESSIONID %s \n",  se.getSession().getId());
        System.out.printf("로그인된 사용자 수 : %d \n", userCount);
    }
}
