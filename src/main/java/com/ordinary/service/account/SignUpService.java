package com.ordinary.service.account;

import com.ordinary.repository.dao.UserDao;
import com.ordinary.details.UserDetailsServiceImpl;
import com.ordinary.enums.UserState;
import com.ordinary.repository.mapper.UserMapper;
import com.ordinary.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2020-07-20
 * <p>
 * 회원가입 처리
 */
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
	 * @return Success(성공) 또는 Overlap(중복)
	 */
	public String SignUpIdCheck(UserDao userDao) {
		if (userDetailsService.loadIdBySignUp(userDao.getLoginId()).equals("Overlap")) {
			return "Overlap";
		}
		SaveSignUp(userDao);
		return "Success";
	}

	/**
	 * 회원정보 DATABASE 저장
	 *
	 * @param userDao
	 */
	private void SaveSignUp(UserDao userDao) {
		userDao.setPassword(bCryptPasswordEncoder.encode(userDao.getPassword()));	// 패스워드를 암호화 해준다.
		userDao.setUserType(UserState.STANDBY.name());	// 최초 가입자는 대기상태
		userMapper.SetSignUp(userDao);	// 저장
		emailSendService.sendMail("lshk955@naver.com", userDao.getUserEmail(), userDao.getLoginId() + "님 회원가입이 정상처리 되었습니다.", userDao.getLoginId() + "아이디로 회원가입이 정상 처리되었습니다.");	// 회원가입 완료 이메일 전송
	}

}
