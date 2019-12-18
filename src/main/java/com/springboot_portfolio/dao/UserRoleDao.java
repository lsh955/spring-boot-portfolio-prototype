package com.springboot_portfolio.dao;

import com.springboot_portfolio.dto.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Component
@Mapper
public interface UserRoleDao {
    
    void setUserRoleInfo(@Param("param") UserRole param);
    
}
