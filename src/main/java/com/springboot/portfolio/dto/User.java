package com.springboot.portfolio.dto;

import org.springframework.stereotype.Component;

/**
 * @author 이승환
 * @since 2019/12/08
 *
 * Input, Output 클래스 모음. domain 클래스와 비슷할 수 있지만, View 와 인터페이스 하기 위한 클래스.
 */
@Component
public class User {

    private int id;
    private String password;
    private String passwordConfirm;
    private String loginId;
    private String userName;
    private int active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
