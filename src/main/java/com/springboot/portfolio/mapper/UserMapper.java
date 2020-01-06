package com.springboot.portfolio.mapper;

import com.springboot.portfolio.dto.User;
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

    User findUserByLoginId(@Param("loginId") String loginId);

    int setUserInfo(@Param("param") User param);

}
