package com.ordinary.service.account.social;

import com.ordinary.details.UserDetailsServiceImpl;
import com.ordinary.enums.AccountLevel;
import com.ordinary.enums.AccountState;
import com.ordinary.enums.AccountType;
import com.ordinary.repository.dao.UserDao;
import com.ordinary.repository.dto.GoogleUserDetails;
import com.ordinary.repository.mapper.UserMapper;
import com.ordinary.service.account.SignUpService;
import com.ordinary.service.account.UserStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2020-07-26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SocialAuthenticationService {

	private UserMapper userMapper;
	private UserDao userDao;
	private SignUpService signUpService;
	private UserDetailsServiceImpl userDetailsService;
	private UserStateService userStateService;

	public void doAuthentication(GoogleUserDetails googleUserDetails) {
		if(userDetailsService.isEmailCheck(googleUserDetails.getEmail())){
			// TODO : 새 회원인 경우에는 회원가입
			userDao.setType(AccountType.GOOGLE.name());		// 최초 로컬
			userDao.setLevel(AccountLevel.MEMBER.name());	// 최초 사용자
			userDao.setState(AccountState.STANDBY.name());	// 최초 대기
			userDao.setEmail(googleUserDetails.getEmail());
			userDao.setName(googleUserDetails.getName());
			userMapper.inputSignUp(userDao);				// 최종 저장
			userStateService.loginDateUpDate(userDao.getEmail());
		}else {
			// TODO : 기존 회원인 경우에는 데이터베이스에서 조회해서 인증처리
			userStateService.loginDateUpDate(userDao.getEmail());
		}
	}

}
