package com.springboot.portfolio.dao;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 유저권한부여 관리(액세스 권한을 부여/제어하는 권한을 얻는다.)
 */
public class UserGrant implements GrantedAuthority {
    
    @Override
    public String getAuthority() {
        return "admin";
    }
    
}
