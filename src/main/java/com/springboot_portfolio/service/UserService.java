package com.springboot_portfolio.service;

import com.springboot_portfolio.domain.UserPrincipal;
import com.springboot_portfolio.mapper.RoleMapper;
import com.springboot_portfolio.mapper.UserMapper;
import com.springboot_portfolio.mapper.UserRoleMapper;
import com.springboot_portfolio.vo.Role;
import com.springboot_portfolio.vo.User;
import com.springboot_portfolio.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 이승환
 * @since 2019/12/08
 */

@Service
public class UserService implements UserDetailsService {    // 사용자의 정보를 검색하는 역할은 UserDetailsService에서 담당


    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public User findUserByLoginId(String loginId) {
        return userMapper.findUserByLoginId(loginId);
    }
    
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(2);
        userMapper.setUserInfo(user);
        Role role = roleMapper.getRoleInfo("member");
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRoleMapper.setUserRoleInfo(userRole);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        User user = userMapper.findUserByLoginId(username);

        if(user == null){   // 데이터베이스에 아이가 없을 경우에...(임시조치)
            modelAndView.setViewName("index");
            return (UserDetails) modelAndView;
        }


        // Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new UserPrincipal(user);

    }
    
}
