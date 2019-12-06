package com.springboot_portfolio.vo;

/**
 * @author 이승환
 * @since 2019-12-06
 */
public class UserRole {
    
    private int userId;
    private int roleId;
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getRoleId() {
        return roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
