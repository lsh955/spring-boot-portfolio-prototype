package com.springboot.portfolio.handler;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.logging.Logger;

/**
 * @author 이승환
 * @since 2019-12-17
 *
 * 다른 객체들이 보낸 메시지를 받고 이를 처리하는 객체
 */
@WebListener
public class SessionHandler implements HttpSessionListener {

    private final static Logger LOG = Logger.getGlobal();

    private int userCount;                              // 로그인된 사용자 수 카운트
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {   // 세션이 생성되었을 때 호출
        se.getSession().setMaxInactiveInterval(60*60);  // 세션만료 60분
        
        ++userCount;
        LOG.info("생성된 SESSION ID : " + se.getSession().getId());
        LOG.info("로그인된 사용자 수 : " +  userCount + "명");
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) { // 세션이 제거되었을 때 호출
        --userCount;
        LOG.info("제거된 SESSION ID : " + se.getSession().getId());
        LOG.info("로그인된 사용자 수 : " + userCount + "명");
    }
    
}
