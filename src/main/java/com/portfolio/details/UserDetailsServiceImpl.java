package com.portfolio.details;

import com.portfolio.mapper.UserMapper;
import com.portfolio.dao.UserDao;
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
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		UserDao findUser = userMapper.findUserByLoginId(id);

		if (findUser != null) {
			log.info("loadUserByUsername >> 있음");
			return new UserDetailsImpl(findUser);
		} else {
			log.info("loadUserByUsername >> 없음");
			throw new UsernameNotFoundException("아이디가 존재하지 않거나, 올바르지 않습니다.");
		}

	}

	/**
	 * 회원가입전 아이디 중복확인
	 * 
	 * @param id
	 * @return
	 */
	public String loadIdBySignUp(String id) {

		log.info("회원가입전 아이디 중복확인 >> 진입");

		if (userMapper.findUserByLoginId(id) == null) {
			log.info("loadIdBySignUp >> Success");
			return "Success";
		} else {
			log.info("loadIdBySignUp >> Overlap");
			return "Overlap";
		}

	}

}