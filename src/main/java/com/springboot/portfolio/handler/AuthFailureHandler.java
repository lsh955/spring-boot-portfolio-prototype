package com.springboot.portfolio.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 이승환
 * @since 2019-12-11
 * <p>
 * 다른 객체들이 보낸 메시지를 받고 이를 처리하는 객체
 */
@Configuration
public class AuthFailureHandler implements AuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        request.setAttribute("loginId", request.getParameter("loginId"));
        request.setAttribute("password", request.getParameter("password"));
        response.sendRedirect("/login?error=true"); // 실패 후 이동할 페이지를 지정
    }
    
}
