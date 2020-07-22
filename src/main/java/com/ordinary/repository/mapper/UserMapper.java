package com.ordinary.repository.mapper;

import com.ordinary.repository.dao.UserDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Mapper
public interface UserMapper {

	/**
	 * 사용자 조회
	 *
	 * @param loginId
	 * @return id, userType, loginId, userName, password, userTel, userEmail, userFirstDate, userLoginDate, userIpAddress
	 */
	UserDao findUserByLoginId(@Param("loginId") String loginId);

	/**
	 * 아이디 중복체크
	 *
	 * @param loginId
	 * @return 0(없음) 또는 1(있음)
	 */
	int idCheck(@Param("loginId") String loginId);

	/**
	 * 회원가입
	 *
	 * @param userDao
	 * @return
	 */
	int setSignUp(@Param("userDao") UserDao userDao);


	/**
	 * 아이디 찾기
	 * 
	 * @param userEmail
	 * @return loginId
	 */
	String findId(@Param("userEmail") String userEmail);

}