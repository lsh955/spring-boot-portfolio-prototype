package com.springboot_portfolio.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author 이승환
 * @since 2019/12/08
 */
public class UserGrant implements GrantedAuthority {    // GrantedAuthority : Spring Security의 개념과 구현은 액세스 권한을 부여 / 제어하는​권한을 얻는다.
    
    @Override
    public String getAuthority() {
        return "admin";
    }
    
}
