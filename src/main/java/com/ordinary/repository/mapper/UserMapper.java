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
    UserDao loadAllData(@Param("email") String email);

    /**
     * 아이디 중복조회
     *
     * @param email
     * @return 0(없음) 또는 1(있음)
     */
    int loadAllEmail(@Param("email") String email);

    /**
     * 회원가입 저장
     *
     * @param userDao
     * @return
     */
    void inputSignUp(@Param("userDao") UserDao userDao);

    /**
     * 아이디 찾기
     *
     * @param tel
     * @return Email
     */
    String findEmail(@Param("tel") String tel);

    /**
     * 비밀번호 찾기
     *
     * @param email
     * @return password
     */
    String findPassword(@Param("email") String email);

    /**
     * 로그인 시각
     *
     * @param email
     * @return
     */
    void inputLoginDate(@Param("email") String email);

    /**
     * 로그아웃 시각
     *
     * @param email
     * @return
     */
    void inputLogOutDate(@Param("email") String email);

    /**
     * 계정삭제 시각
     *
     * @param email
     * @return
     */
    void inputDeletDate(@Param("email") String email);

}