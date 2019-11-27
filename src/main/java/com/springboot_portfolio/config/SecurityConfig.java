package com.springboot_portfolio.config;

import lombok.AllArgsConstructor;
import com.springboot_portfolio.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author 이승환
 * @since 2019-11-27
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private MemberService memberService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리 하위 파일 목록은 인증무시(항상통과)
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .and()
                .formLogin()    // 로그인 설정
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/login/result")
                .permitAll()
            .and()
                .logout()       // 로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logout/result")
                .invalidateHttpSession(true)
            .and()
                .exceptionHandling().accessDeniedPage("/user/denied");  // 403 예외처리
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
    
}
