package com.portfolio.mapper;

import com.portfolio.dao.UserDao;
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
	 * 회원가입
	 *
	 * @param userDao
	 * @return
	 */
	int SetSignUp(@Param("param") UserDao userDao);

}