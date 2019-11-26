package com.springboot_portfolio.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // 세션이 생성될때
        HttpSessionListener.super.sessionCreated(event);
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // 세션이 없어질때
        HttpSession session = event.getSession();
    }

}
