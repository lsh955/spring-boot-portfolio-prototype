package com.springboot_portfolio.Handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 이승환
 * @since 2019-12-11
 */
@Configuration
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);  // 요청이 정상적으로 완료되었음을 나타내는 상태 코드 (200).
        response.sendRedirect("/home");         // 성공 후 이동할 페이지를 지정
    }
    
}
