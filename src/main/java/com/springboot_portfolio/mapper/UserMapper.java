package com.springboot_portfolio.mapper;

import com.springboot_portfolio.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Component
@Mapper
public interface UserMapper {

    User findUserByLoginId(@Param("loginId") String loginId);

    int setUserInfo(@Param("param") User param);

}
