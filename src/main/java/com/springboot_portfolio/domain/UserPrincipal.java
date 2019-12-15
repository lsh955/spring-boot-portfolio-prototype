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

    public void setUser(User user) {
        this.user = user;
    }

    public UserPrincipal(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getLoginId();
    }

    public String getName() {
        return user.getUserName();
    }
    
    /**
     * UserDetails 구현체 작성시에는 해당 사용자에게 부여된 역할과 권한을 모두 문자열로
     * getAuthorities()을 통해 획득되도록 구현
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    //유저가 갖고 있는 권한 목록
        return Arrays.asList(new UserGrant());
    }
    
    @Override
    public String getPassword() {   //유저 비밀번호
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {   // 유저 이름 혹은 아이디
        return user.getUserName();
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
    //  return user.getActive() == 1;
    }

}
