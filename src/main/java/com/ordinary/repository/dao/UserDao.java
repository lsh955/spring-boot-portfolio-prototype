package com.ordinary.repository.dao;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * Input, Output 클래스 모음. domain 클래스와 비슷할 수 있지만, View 와 인터페이스 하기 위한 클래스.
 */
@Getter
@Setter
@Component
@Alias("UserDao")
public class UserDao {

	private int id;                 			// 키값
	private String userType;        			// 권한정보
	private String loginId;         			// 사용자 아이디
	private String userName;        			// 사용자 성함
	private String userEmail;       			// 사용자 이메일
	private String userTel;         			// 사용자 전화번호
	private String password;        			// 패스워드
	private String passwordConfirm; 			// 패스워드 확인
	private String userFirstDate;   			// 회원가입 일자
	private String userLoginDate;   			// 로그인 일자
	private String userIpAddress = "0.0.0.0";   // 사용자 로그인아이디

//	private int IDX;			// 키값
//	private String TYPE;		// 계정 타입
//	private String LEVEL;		// 계정 권한
//	private String STATE;		// 계정 상태
//	private String EMAIL;		// 사용자 이메일
//	private String PASSWORD;	// 사용자 패스워드
//	private String NAME;		// 사용자 성함
//	private String TEL;			// 사용자 전화번호
//	private String JoinDate;	// 가입 일자
//	private String DeleteDate;	// 탈퇴 일자
//	private String LoginDate;	// 로그인 일자
//	private String LogOutDate;	// 로그아웃 일자
//	private String IpAddress;	// 접속 아이피

}
