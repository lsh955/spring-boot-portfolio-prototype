package com.springboot.portfolio.filter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 이승환
 * @since 2020-01-10
 * <p>
 * 테스트중....
 */
@Component
public class AjaxSessionTimeoutFilter implements Filter {
    
    /**
     * Default AJAX request Header
     */
    private String ajaxHaeder = "AJAX";
    
    public void destroy() {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        if (isAjaxRequest(req)) {
            try {
                chain.doFilter(req, res);
            } catch (AccessDeniedException e) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            } catch (AuthenticationException e) {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else
            chain.doFilter(req, res);
    }
    
    private boolean isAjaxRequest(HttpServletRequest req) {
        return req.getHeader(ajaxHaeder) != null && req.getHeader(ajaxHaeder).equals(Boolean.TRUE.toString());
    }
    
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    /**
     * Set AJAX Request Header (Default is AJAX)
     *
     * @param ajaxHeader
     */
    public void setAjaxHaeder(String ajaxHeader) {
        this.ajaxHaeder = ajaxHeader;
    }
}