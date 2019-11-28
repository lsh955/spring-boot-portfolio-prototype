package com.springboot_portfolio.service;

import com.springboot_portfolio.vo.MemberEntity;
import com.springboot_portfolio.vo.MemberVo;
import com.springboot_portfolio.mapper.MemberMapper;
import com.springboot_portfolio.vo.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 이승환
 * @since 2019-11-27
 */
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    
    private MemberMapper memberMapper;
    
    /**
     * joinUser()
     * 회원가입을 처리
     * 비밀번호를 암호화하여 저장한다.
     */
    @Transactional  // @Transactional:2개이상의 쿼리를 하나의 커넥션으로 묶어 DB에 전송하고, 에러가 발생할 경우 자동으로 모든 과정을 원래대로 되돌려 놓는다.
    public String joinUser(MemberVo memberVo){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVo.setMEMBER_PWD(passwordEncoder.encode(memberVo.getMEMBER_PWD()));
        
        return memberMapper.save(memberVo.toEntity()).getMEMBER_ID();
    }
    
    /**
     * loadUserByUsername()
     * 상세정보를 조회
     * 사용자의 계정정보와 권한을 갖는 UserDetails 인터페이스를 반환 한다.
     */
    @Override
    public UserDetails loadUserByUsername(String MEMBER_EMAIL) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberMapper.findByEmail(MEMBER_EMAIL);
        MemberEntity userEntity = userEntityWrapper.get();
    
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        if(("admin@example.com").equals(MEMBER_EMAIL)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        
        return new User(userEntity.getMEMBER_EMAIL(), userEntity.getMEMBER_PWD(), authorities);
    }
    
}
