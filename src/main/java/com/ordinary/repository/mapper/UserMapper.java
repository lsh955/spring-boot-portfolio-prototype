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
	 * @param email
	 * @return idx, type, level, state, email, password, name, tel, joinDate, deleteDate, loginDate, logOutDate, ipAddress
	 */
	UserDao getFindUserByLoginEmail(@Param("email") String email);

	/**
	 * 아이디 중복체크
	 *
	 * @param email
	 * @return 0(없음) 또는 1(있음)
	 */
	int getEmailCheck(@Param("email") String email);

	/**
	 * 회원가입
	 *
	 * @param userDao
	 * @return
	 */
	void setSignUp(@Param("userDao") UserDao userDao);


	/**
	 * 아이디 찾기
	 *
	 * @param tel
	 * @return Email
	 */
	String getFindEmail(@Param("tel") String tel);

	/**
	 * 비밀번호 찾기
	 *
	 * @param email
	 * @return password
	 */
	String getFindPassword(@Param("email") String email);

	/**
	 * 로그인 시각 업데이트
	 *
	 * @param email
	 * @return
	 */
	void loginDateUpDate(@Param("email") String email);

	/**
	 * 로그아웃 시각 업데이트
	 *
	 * @param email
	 * @return
	 */
	void logOutDateUpDate(@Param("email") String email);

	/**
	 * 계정삭제 시각 업데이트
	 *
	 * @param email
	 * @return
	 */
	void deletDateUpDate(@Param("email") String email);

}