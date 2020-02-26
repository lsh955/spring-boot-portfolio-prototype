package com.springboot.portfolio.service;

import com.springboot.portfolio.details.UserDetailsImpl;
import com.springboot.portfolio.mapper.UserMapper;
import com.springboot.portfolio.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * DB에서 유저 정보를 가져오는 역할
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {    // 사용자의 정보를 검색하는 역할은 UserDetailsService에서 담당

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));     // 패스워드를 암호화 해준다.
        user.setUserType("WAITING");                                            // 기본 사용자 권한은 승인대기
        userMapper.setUserInfo(user);                                           // 데이터베이스에 저장
    }

    public User findUserByLoginId(String loginId) {
        return userMapper.findUserByLoginId(loginId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findUserByLoginId(username);
        if (user.getLoginId() == null) {
            throw new UsernameNotFoundException(username + " 에 대한 계정정보를 찾을 수 없습니다.");
        }
        return createUser(user);
    }

    private UserDetailsImpl createUser(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        userDetails.setRoles(Collections.singletonList(user.getUserType()));
        return userDetails;
    }

}
