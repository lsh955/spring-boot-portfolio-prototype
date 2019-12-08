package com.springboot_portfolio.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author 이승환
 * @since 2019/12/08
 */
public class UserGrant implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "ADMIN";
    }

}
