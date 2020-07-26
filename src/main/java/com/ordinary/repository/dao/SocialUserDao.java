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
	private String userType;

	// oauth2Request
	private String clientId;
	private Boolean approved;

	// userAuthentication
	private String sub;
	private String name;
	private String given_name;
	private String family_name;
	private String picture;
	private String email;
	private boolean email_verified;
	private String locale;


}
