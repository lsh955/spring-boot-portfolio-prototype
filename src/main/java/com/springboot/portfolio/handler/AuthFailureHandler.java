package com.springboot.portfolio.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 이승환
 * @since 2019-12-11
 * <p>
 * 인증실패 핸들러
 */
@Slf4j
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        
        request.setAttribute(loginId, loginId);
        request.setAttribute(password, password);
        
        if (exception instanceof BadCredentialsException) {                         // 비밀번호가 일치하지 않은 경우
            log.info("비밀번호가 일치하지 않습니다.");
        } else if (exception instanceof InternalAuthenticationServiceException) {   // 존재 하지 않는 아이디일 경우
            log.info("존재하지 않는 아이디 입니다.");
        } else if (exception instanceof LockedException) {                          // 인증 거부 : 잠긴 계정일 경우
            log.info("관리자에 의해 계정이 잠겨 있습니다.");
        } else if (exception instanceof DisabledException) {                        // 인증 거부 : 계정 비활성화
            log.info("관리자에 의해 계정이 비활성화 되어 있습니다.");
        } else if (exception instanceof AccountExpiredException) {                  // 인증 거부 : 계정 유효 기간 만료
            log.info("계정 유효기간이 만료 되었습니다.");
        } else if (exception instanceof CredentialsExpiredException) {              // 인증 거부 : 비밀번호 유효 기간 만료
            log.info("계정 비밀번호 유효기간이 만료 되었습니다.");
        }
        
        response.sendRedirect("/?error=true"); // 실패 후 이동할 페이지를 지정
    }
    
}