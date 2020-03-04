package com.springboot.portfolio.enums;

/**
 * @author 이승환
 * @since 2020-01-10
 * <p>
 * security에서 역할관리
 * <p>
 * enum(열거형)?
 * - 클래스처럼 보이게 하는 상수
 * - 서로 관련있는 상수들끼리 모아 상수들을 대표할 수 있는 이름으로 타입을 정의하는 것
 * - Enum 클래스 형을 기반으로 한 클래스형 선언
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
