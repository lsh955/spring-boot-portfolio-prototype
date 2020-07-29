package com.ordinary.service.account.local;

import com.ordinary.enums.AccountLevel;
import com.ordinary.enums.AccountType;
import com.ordinary.repository.dao.UserDao;
import com.ordinary.details.UserDetailsServiceImpl;
import com.ordinary.enums.AccountState;
import com.ordinary.repository.mapper.UserMapper;
import com.ordinary.service.EmailSendService;
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
public class SignUpUserService {

	private final UserMapper userMapper;
	private final DateStateService dateStateService;
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
		if (userDetailsService.loadIdBySignUp(userDao.getEmail()).equals("Overlap")) {
			return "Overlap";
		}
		SignUpSave(userDao);
		return "Success";
	}

	/**
	 * 회원정보 DATABASE 저장
	 *
	 * @param userDao
	 */
	private void SignUpSave(UserDao userDao) {
		userDao.setPassword(bCryptPasswordEncoder.encode(userDao.getPassword()));    // 패스워드를 암호화 해준다.
		userDao.setType(AccountType.LOCAL.name());
		userDao.setState(AccountState.STANDBY.name());	// 최초 가입자는 대기상태
		userMapper.setSignUp(userDao);    // 저장
		emailSendService.sendMail("lshk955@naver.com",
										userDao.getEmail(),
								 userDao.getName() + "님 회원가입이 정상처리 되었습니다.",
								userDao.getEmail() + "아이디로 회원가입이 정상 처리되었습니다.");    // 회원가입 완료 이메일 전송
	}

}
