package com.springboot.portfolio.mapper;

import com.springboot.portfolio.dto.Roles;
import com.springboot.portfolio.dto.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@Mapper
@Component
public interface RoleMapper {
    
    Roles getRoleInfo(@Param("role") String role);
    
    void setUserRoleInfo(@Param("param") UserRole param);
    
}
