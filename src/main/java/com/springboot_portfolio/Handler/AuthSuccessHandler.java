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
 *
 * 다른 객체들이 보낸 메시지를 받고 이를 처리하는 객체
 */
@Configuration
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    private static final String Unknown = "unknown";
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);  // 요청이 정상적으로 완료되었음을 나타내는 상태 코드 (200).
        response.sendRedirect("/home");         // 성공 후 이동할 페이지를 지정
    }
    
    /**
     * 클라이언트 IP정보
     */
    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
    
        if (ip == null || ip.length() == 0 || Unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || Unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || Unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || Unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
}
