package com.springboot.portfolio.enums;

/**
 * @author 이승환
 * @since 2020-01-10
 */
public enum UserType {
    
    ADMIN("최고관리자"),
    MEMBER("일반사용자"),
    WAITING("승인대기"),
    SECESSION("회원탈퇴");

    private String value;
    
    UserType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
}
