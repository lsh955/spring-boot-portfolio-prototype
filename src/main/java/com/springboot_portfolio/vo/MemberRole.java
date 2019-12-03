package com.springboot_portfolio.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 이승환
 * @since 2019-11-28
 */
@AllArgsConstructor
public enum MemberRole {
    
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");
    
    private String value;

    public String getValue() {
        return value;
    }

}
