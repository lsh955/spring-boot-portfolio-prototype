package com.springboot.portfolio.service;

import com.springboot.portfolio.details.UserDetailsImpl;
import com.springboot.portfolio.mapper.RoleMapper;
import com.springboot.portfolio.mapper.UserMapper;
import com.springboot.portfolio.dto.Roles;
import com.springboot.portfolio.dto.User;
import com.springboot.portfolio.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 비즈니스 혹은 사용자 로직을 구현한 클래스. 비즈니스와 연관있는 로직을 표현.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {    // 사용자의 정보를 검색하는 역할은 UserDetailsService에서 담당
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public User findUserByLoginId(String loginId) {
        return userMapper.findUserByLoginId(loginId);
    }
    
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));     // 패스워드를 암호화 해준다.
        user.setActive(2);                                                      // 기본 권한은 2번(member)권한 설정
        userMapper.setUserInfo(user);                                           // 데이터베이스에 저장
        Roles role = roleMapper.getRoleInfo("member");
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        roleMapper.setUserRoleInfo(userRole);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findUserByLoginId(username);
        
        if (user == null) {
            throw new UsernameNotFoundException(username + " 으로 시작된 아이디는 가입내역이 존재하지 않거나 가입내역이 없습니다.");
        }
        return new UserDetailsImpl(user);
    }
    
}
