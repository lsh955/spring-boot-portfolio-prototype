package com.portfolio.config;

import com.portfolio.details.UserDetailsServiceImpl;
import com.portfolio.handler.AuthFailureHandler;
import com.portfolio.handler.AuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 설정 클래스 모음. (Web Security, Controller Advice 등)
 */
@RequiredArgsConstructor
@EnableWebSecurity      // @EnableWebSecurity : Spring Security 설정할 클래스라고 재정의(이걸 입력하는 순간 기본적인 "Security"설정은 날아간다.)
@Configuration          // @Configuration : Spring Boot를 사용하면서 필요한 설정
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private AuthSuccessHandler authSuccessHandler;          // 로그인이 성공했을 때 핸들러
    @Autowired
    private AuthFailureHandler authFailureHandler;          // 로그인이 실패했을 때 핸들러
    @Autowired
    private UserDetailsServiceImpl userDetailsService;      // 사용자 액세스를위한 서비스 개체
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;    // BCrypt 해시 함수를 이용하여 비밀번호를 저장하여 암호화
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {                      // 로그인 URL, 권한분리, Logout URL  설정
        // @formatter:off
        http.authorizeRequests()                                                        // 요청에 대한 권한을 지정
                .antMatchers("/",
                        "/index",
                        "/signup",
                        "/sessionfailed",
                        "/exception",
                        "/manager",
                        "/ReCAPTCHA",
                        "/google",
                        "/getLoginJson").permitAll()  // 접근을 전부 허용
                .antMatchers("/home").access("hasRole('MEMBER') or hasRole('ADMIN')") // 사용자만 접근
                .antMatchers("/manager").hasRole("ADMIN")                   // 관리자만 접근
                .anyRequest()                                                           // 인증 되어야 하는 부분
                .authenticated();                                                       // 인증된 사용자만 접근
        
        http.csrf().disable();                                                          // CSRF[사이트 간 요청 위조] 프로텍션(사용하지 않음)
        
        http.formLogin()                                                                // 폼을 통한 로그인을 이용
                .loginPage("/")                                                         // 로그인 뷰 페이지를 연결
                .usernameParameter("loginId")                                           // 로그인 페이지에서 "name태그"파라메터로 전송된 값
                .passwordParameter("password")                                          // 로그인 페이지에서 "name태그"파라메터로 전송된 값
                .successHandler(authSuccessHandler)                                     // 로그인이 성공했을 때 핸들러
                .failureHandler(authFailureHandler);                                    // 로그인이 실패했을 때 핸들러
        
        http.logout()                                                                   // 로그아웃 처리
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))     // 로그아웃이 성공했을 경우 이동할 페이지
                .logoutSuccessUrl("/?state=logout")                                     // 로그아웃 성공 후 반환하는 URI
                .invalidateHttpSession(true)                                            // 로그아웃시 인증정보를 지우하고 세션을 무효화 시킨다는 설정
                .clearAuthentication(true);                                             // 로그아웃 시 인증이 초기화
        
        http.sessionManagement()                                                        // 세션 정책 설정
                .maximumSessions(1)                                                     // 세션허용 인원
                .maxSessionsPreventsLogin(false)                                        // 로그인중일 경우 로그인이 안된다.(false일 경우 기존 사용자의 세션이 종료된다.)
                .expiredUrl("/sessionfailed");                                          // 중복 로그인이 발생했을 경우 이동할 주소(원인을 알려줄 주소)
        
        http.exceptionHandling()                                                        // 예외처리 핸들링
                .accessDeniedPage("/accessdenied");                                     // 예외가 발생했을때의 페이지 경로
        // @formatter:on
    }
    
    @Override
    public void configure(WebSecurity web) {
        // 특정 요청을 무시(인증이 필요없는 허용하는 경로)
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider(userDetailsService));        // AuthenticationManagerBuilder를 주입해서 인증에 대한 처리
    }
    
    /**
     * 비밀번호 암호화 클래스 Bean등록
     * BCrypt 해시 함수를 이용하여 비밀번호를 저장하는 방법을 사용
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);          // "UserService"에서 데이터를 가지고와서 DaoAuthenticationProvider에서 인증과정을 거친다.
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);   // "ShaPasswordEncoder"를 사용해서 비밀번호를 인코딩한다.
        return authenticationProvider;
    }
    
}