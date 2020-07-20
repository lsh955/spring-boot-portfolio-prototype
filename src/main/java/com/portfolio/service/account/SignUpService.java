package com.portfolio.service.account;

import com.portfolio.dao.UserDao;
import com.portfolio.details.UserDetailsServiceImpl;
import com.portfolio.enums.UserState;
import com.portfolio.mapper.UserMapper;
import com.portfolio.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2020-07-20
 * <p>
 * 회원가입 처리
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {

	private final UserMapper userMapper;
	private final UserDetailsServiceImpl userDetailsService;
	private final EmailSendService emailSendService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 회원가입전 아이디 중복확인
	 *
	 * @param userDao
	 */
	public String SignUpIdCheck(UserDao userDao) {

		log.info("회원가입전 아이디 중복확인 >> 진입");

		if (userDetailsService.loadIdBySignUp(userDao.getLoginId()).equals("Success")) {
			SaveSignUp(userDao);
			log.info("SignUpIdCheck >> Success");
			log.info("사용자가 성공적으로 등록되었습니다");
			return "Success";
		} else {
			log.info("SignUpIdCheck >> Overlap");
			log.info("이미 등록 된 사용자가 있습니다");
			return "Overlap";
		}

	}

	/**
	 * 회원정보 DATABASE 저장
	 *
	 * @param userDao
	 */
	private void SaveSignUp(UserDao userDao) {

		log.info("회원정보 저장 >> 진입");

		userDao.setPassword(bCryptPasswordEncoder.encode(userDao.getPassword()));    // 패스워드를 암호화 해준다.
		userDao.setUserType(UserState.STANDBY.name());        // 최초 가입자는 대기상태
		emailSendService.saveUserEmail(userDao);    // 회원가입 완료 이메일 전송

		userMapper.SetSignUp(userDao);        // 저장

	}

}
