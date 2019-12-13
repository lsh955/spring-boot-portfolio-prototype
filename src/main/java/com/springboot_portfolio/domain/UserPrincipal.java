package com.springboot_portfolio.domain;

import com.springboot_portfolio.vo.User;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author 이승환
 * @since 2019/12/08
 */
@ToString
public class UserPrincipal implements UserDetails {
    
    private User user;
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public UserPrincipal(User user) {
        this.user = user;
    }
    
    /**
     * UserDetails 구현체 작성시에는 해당 사용자에게 부여된 역할과 권한을 모두 문자열로
     * getAuthorities()을 통해 획득되도록 구현
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new UserGrant());
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getUserName();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
//    @Override
//    public boolean isEnabled() {
//        return user.getActive() == 1;
//    }
    
    @Override
    public boolean isEnabled() {
        return true;
//      return user.getActive() == 1;
    }
    
    public String getId() {
        return user.getLoginId();
    }
    
    public String getName() {
        return user.getUserName();
    }
    
}
