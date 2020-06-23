package com.portfolio.handler;

import com.portfolio.listener.HttpSessionListenerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * @author 이승환
 * @since 2019-12-11
 * <p>
 * 인증성공 핸들러
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);  // 요청이 정상적으로 완료되었음을 나타내는 상태 코드 (200).
        response.sendRedirect("/");             // 성공 후 이동할 페이지를 지정
    }
    
    @Bean
    public HttpSessionListener httpSessionListener() {  // 로그인 했을때 세션카운트를 할당한다.
        return new HttpSessionListenerImpl();
    }
    
}

