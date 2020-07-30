package com.ordinary.details;

import com.ordinary.repository.mapper.UserMapper;
import com.ordinary.repository.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * DataBase에서 유저 정보를 직접 가져오는 인터페이스 구현체,
 * 구현한 클래스에 사용자 정보를 불러오는 작업 진행.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDao findUser = userMapper.loadAllData(email);
		if (findUser == null) {
			throw new UsernameNotFoundException("아이디가 존재하지 않거나, 올바르지 않습니다.");
		}
		return new UserDetailsImpl(findUser);
	}

	/**
	 * 이메일 데이터 조회
	 *
	 * @param email
	 * @return Success(성공) 또는 Overlap(중복)
	 */
	public boolean loadEmailCheck(String email) {
		return userMapper.loadAllEmail(email) == 0;
	}

}