package com.ordinary.repository.dao;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2020-07-26
 */
@Getter
@Setter
@Component
@Alias("SocialUserDao")
public class SocialUserDao {

	private int id;                 // 키값
	private String userType;		// 사용자 타입
	private String userState;		// 사용자 상태
	private String socialType;		// 소셜 타입

	// oauth2Request
	private String clientId;		// 클라이언트를 구분할 수 있는 아이디
	private Boolean approved;		// 요청승인

	// userAuthentication
	private String sub;
	private String name;			// 성함
	private String given_name;		// 이름
	private String family_name;		// 성
	private String picture;			// 프로필 사진
	private String email;			// 이메일
	private boolean email_verified;	// 이메일 검증여부
	private String locale;			// 국가


}
