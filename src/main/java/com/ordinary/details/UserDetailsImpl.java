package com.ordinary.details;

import com.ordinary.repository.dao.UserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * Spring Security에서 사용자의 정보를 담는 인터페이스 구현체,
 * 구현한 클래스를 사용자 정보로 인식하고 인증 작업 진행.
 */
public class UserDetailsImpl implements UserDetails {

    private final UserDao userDao;

    public UserDetailsImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 부여 된 권한을 반환
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userDao.getType()));
        return authorities;
    }

    @Override
    public String getPassword() {
        // 유저 비밀번호
        return this.userDao.getPassword();
    }

    @Override
    public String getUsername() {
        // 유저 이름 혹은 아이디
        return this.userDao.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 아이디가 만료 되었는지 반환
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 아이디가 Lock 걸렸는지 반환
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호가 만료 되었는지 반환
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화 되었는지 반환
        return true;
    }

}
