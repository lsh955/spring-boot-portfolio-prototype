package com.portfolio.details;

import com.portfolio.mapper.UserMapper;
import com.portfolio.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * DB에서 유저 정보를 가져오는 역할
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {    // 사용자의 정보를 검색하는 역할은 UserDetailsService에서 담당
    
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    // 회원가입
    public void saveUser(User user, HttpServletRequest request) {
        
        String requestIp = request.getHeader("X-Forwarded-For");
        log.info("> X-FORWARDED-FOR : " + requestIp);
        
        if (requestIp == null) {
            requestIp = request.getHeader("Proxy-Client-IP");
            log.info("1. Proxy-Client-IP : " + requestIp);
        }
        if (requestIp == null) {
            requestIp = request.getHeader("WL-Proxy-Client-IP");
            log.info("2.  WL-Proxy-Client-IP : " + requestIp);
        }
        if (requestIp == null) {
            requestIp = request.getHeader("HTTP_CLIENT_IP");
            log.info("3. HTTP_CLIENT_IP : " + requestIp);
        }
        if (requestIp == null) {
            requestIp = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("4. HTTP_X_FORWARDED_FOR : " + requestIp);
        }
        if (requestIp == null) {
            requestIp = request.getRemoteAddr();
            log.info("5. getRemoteAddr : " + requestIp);
        }
        log.info(">> Result : IP Address : " + requestIp);
        user.setUserIpAddress(requestIp);   // 사용자 IP 정보
        
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));     // 패스워드를 암호화 해준다.
        user.setUserType("WAITING");                                            // 기본 사용자 권한은 승인대기
        userMapper.setUserInfo(user);                                           // 데이터베이스에 저장
    }
    
    // 아이디조회
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