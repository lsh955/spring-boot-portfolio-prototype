package com.springboot.portfolio.dto;

import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * Input, Output 클래스 모음. domain 클래스와 비슷할 수 있지만, View 와 인터페이스 하기 위한 클래스.
 */
@Component
public class User {
    
    private int id;                 // 키값
    private String userType;        // 권한정보
    private String loginId;         // 사용자 아이디
    private String userName;        // 사용자 성함
    private String userEmail;       // 사용자 이메일
    private String userTel;         // 사용자 전화번호
    private String password;        // 패스워드
    private String passwordConfirm; // 패스워드 확인
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getLoginId() {
        return loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserTel() {
        return userTel;
    }
    
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
}
