package com.ordinary.service.account.local;

import com.ordinary.repository.dao.UserDao;
import com.ordinary.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2020-07-29
 *
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DateStateService {

	private final UserMapper userMapper;

	/**
	 * 로그인 시각 업데이트
	 *
	 * @param email
	 */
	public void loginDateUpDate(String email) {
		userMapper.loginDateUpDate(email);
	}

	/**
	 * 로그아웃 시각 업데이트
	 *
	 * @param userDao
	 */
	public void logOutDateUpDate(UserDao userDao) {
		userMapper.logOutDateUpDate(userDao.getEmail());
	}

	/**
	 * 계정삭제 시각 업데이트
	 *
	 * @param userDao
	 */
	public void deletDateUpDate(UserDao userDao) {
		userMapper.deletDateUpDate(userDao.getEmail());
	}

}
