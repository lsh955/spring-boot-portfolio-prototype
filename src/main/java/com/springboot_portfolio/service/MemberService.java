package com.springboot_portfolio.service;

import com.springboot_portfolio.vo.MemberVo;
import com.springboot_portfolio.mapper.MemberMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 이승환
 * @since 2019-11-27
 */
public class MemberService implements UserDetailsService {
    
    @Transactional
    public Long joinUser(MemberVo memberVo){
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        MemberVo.setMEMBER_PWD(passwordEncoder.encode(memberVo.getMEMBER_EMAIL()));
        
        return MemberMapper.save(MemberVo.toEntity()).getId();
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
