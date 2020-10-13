package com.ordinary.repository.dao;

import com.ordinary.enums.AccountLevel;
import com.ordinary.enums.AccountState;
import com.ordinary.enums.AccountType;
import com.ordinary.repository.dto.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author 이승환
 * @since 2020-07-27
 * <p>
 * Input, Output 클래스 모음. domain 클래스와 비슷할 수 있지만, View 와 인터페이스 하기 위한 클래스.
 */
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("UserDao")
public class UserDao {

    private int idx;                    // 키값(TODO : UUID값으로 바꿔 저장할 것.)
    private String type;                // 계정 타입
    private String level;               // 계정 권한
    private String state;               // 계정 상태
    private String email;               // 사용자 이메일
    private String name;                // 사용자 성함
    private String tel;                 // 사용자 전화번호
    private String password;            // 사용자 패스워드
    private String joinDate;            // 가입 일자
    private String deleteDate;          // 탈퇴 일자
    private String loginDate;           // 로그인 일자
    private String logOutDate;          // 로그아웃 일자
    private String ipAddress;           // 접속 아이피

    /**
     * 회원정보 Default값
     *
     * @param userDto
     */
    public static UserDao fromSendUser(UserDto userDto) {

        UserDao userDao = new UserDao();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //userDao.idx = UUID.randomUUID().toString().toUpperCase();
        userDao.type = AccountType.LOCAL.name();        // 가입방식 > 로컬
        userDao.level = AccountLevel.MEMBER.name();     // 유저권한 > 사용자
        userDao.state = AccountState.STANDBY.name();    // 승인상태 > 대기
        userDao.email = userDto.getEmail();
        userDao.name = userDto.getName();
        userDao.tel = userDto.getTel();
        userDao.password = passwordEncoder.encode(userDto.getPassword());     // 패스워드 > encode(암호화)
        userDao.joinDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));  // 2018-07-26 00:42:24
        userDao.deleteDate = null;
        userDao.loginDate = null;
        userDao.logOutDate = null;
        userDao.ipAddress = userDto.getIpAddress();

        return userDao;
    }

    // TODO : doAuthentication
    public void setDoAuthentication() {

    }

    // TODO : getSendSignUp
    public void setSendSignUp() {

    }

    // TODO : successfulAuthentication
    public void setSuccessfulAuthentication() {

    }

}
