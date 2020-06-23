package com.portfolio.mapper;

import com.portfolio.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Mapper
@Component
public interface UserMapper {
    
    /**
     * 아이디 기준으로 회원이 존재하는지 조회한다.
     *
     * @param loginId (아이디)
     * @return id, userType, loginId, userName, password, userTel, userEmail, userFirstDate, userLoginDate, userIpAddress
     */
    User findUserByLoginId(@Param("loginId") String loginId);
    
    /**
     * 사용자 조회
     *
     * @param param (아이디)
     * @return id (아이디), pwd (패스워드), name (이름), managerRole (권한), managerState (상태)
     */
    int setUserInfo(@Param("param") User param);
    
}