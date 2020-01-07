package com.springboot.portfolio.details;

import com.springboot.portfolio.dto.User;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 데이터베이스 테이블과 실제로 1:1 매핑되어 정확하게 관리.
 */
@ToString
@EqualsAndHashCode(of = "id")
public class UserDetailsImpl implements UserDetails {
    
    @Autowired
    private User user;
    
    private String id;
    
    public User getUser() {
        return user;
    }
    
    public String getId() {
        return user.getLoginId();
    }
    
    public String getName() {
        return user.getUserName();
    }
    
    public UserDetailsImpl(User user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    //유저가 갖고 있는 권한 목록
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));
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
    
}
