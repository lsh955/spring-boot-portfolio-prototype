package com.springboot_portfolio.dao;

import com.springboot_portfolio.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Component
@Mapper
public interface UserDao {
    
    User findUserByLoginId(@Param("loginId") String loginId);
    
    int setUserInfo(@Param("param") User param);
    
}
