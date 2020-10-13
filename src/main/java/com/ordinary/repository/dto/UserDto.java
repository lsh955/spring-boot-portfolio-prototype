package com.ordinary.repository.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2020-07-27
 * <p>
 * Input, Output 클래스 모음. domain 클래스와 비슷할 수 있지만, View 와 인터페이스 하기 위한 클래스.
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class UserDto {

    private int idx;                    // 키값(TODO : UUID값으로 바꿔 저장할 것.)
    private String type;                // 계정 타입
    private String level;               // 계정 권한
    private String state;               // 계정 상태
    private String email;               // 사용자 이메일
    private String name;                // 사용자 성함
    private String tel;                 // 사용자 전화번호
    private String password;            // 사용자 패스워드
    private String passwordReConfirm;   // 사용자 패스워드 재 확인
    private String joinDate;            // 가입 일자
    private String deleteDate;          // 탈퇴 일자
    private String loginDate;           // 로그인 일자
    private String logOutDate;          // 로그아웃 일자
    private String ipAddress;           // 접속 아이피

    public void fromIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
