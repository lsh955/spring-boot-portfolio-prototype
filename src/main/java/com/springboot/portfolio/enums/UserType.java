package com.springboot.portfolio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@AllArgsConstructor
public enum UserType {

    DELETE("탈퇴"),
    STANDBY("대기"),
    MEMBER("사용자"),
    ADMIN("관리자");

    private String values;

}