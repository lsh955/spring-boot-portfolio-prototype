package com.springboot.portfolio.details;

import com.springboot.portfolio.dto.User;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * Security에서 사용자의 정보를 가져오는 역할
 */
@Slf4j
@EqualsAndHashCode(of = "id")
public class UserDetailsImpl implements UserDetails {
    
    private int id;
    
    private User user;
    private List<String> roles;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    // 사용자에게 부여 된 권한을 반환하는 역할
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));  // 객체를 생설할 때, 'ROLE_' 를 앞에 붙여주어서 Spring이 이해할 수 있게 한다.
        }
        return authorities;
    }
    
    @Override
    public String getPassword() {   //유저 비밀번호
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {   // 유저 이름 혹은 아이디
        return user.getUserName();
    }
    
    public UserDetailsImpl(User user) {
        this.user = user;
    }
    
    @Override
    public boolean isAccountNonExpired() {  // 유저 아이디가 만료 되었는지
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {   // 유저 아이디가 Lock 걸렸는지
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {  //비밀번호가 만료 되었는지
        return true;
    }
    
    @Override
    public boolean isEnabled() {    // 계정이 활성화 되었는지
        return true;
    }
    
    public User getUser() {
        return user;
    }
    
    public List<String> getRoles() {
        return roles;
    }
    
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    public String getId() {
        return user.getLoginId();
    }
    
    public String getUserType() {
        return user.getUserType();
    }
    
    public String getLoginId() {
        return user.getLoginId();
    }
    
    public String getUserTel() {
        return user.getUserTel();
    }
    
    public String getUserEmail() {
        return user.getUserEmail();
    }
    
    public String getUserFirstDate() {
        return user.getUserFirstDate();
    }
    
    public String getUserLoginDate() {
        return user.getUserLoginDate();
    }
    
    public String getUserIpAddress() {
        return user.getUserIpAddress();
    }
    
}
