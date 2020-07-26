package com.ordinary.config;

import com.ordinary.enums.account.UserType;
import com.ordinary.service.account.social.SocialAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 이승환
 * @since 2019/12/08
 * <p>
 * 설정 클래스 모음. (Web Security, Controller Advice 등)
 */
@EnableOAuth2Client
@EnableWebSecurity      // @EnableWebSecurity : Spring Security 설정할 클래스라고 재정의(이걸 입력하는 순간 기본적인 "Security"설정은 날아간다.)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final OAuth2ClientContext oauth2ClientContext;


	@Override
	public void configure(WebSecurity web) {
		// 특정 요청을 무시(인증이 필요없는 허용하는 경로)
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {                  // 로그인 URL, 권한분리, Logout URL  설정
		// @formatter:off

		http.csrf().disable();                                                      // CSRF[사이트 간 요청 위조] 프로텍션(사용하지 않음)

		http.authorizeRequests()                                                    // 요청에 대한 권한을 지정
			.antMatchers("/**").permitAll()  // 접근을 전부 허용
			.antMatchers("/home").hasAnyRole(UserType.ADMIN.name(), UserType.MEMBER.name()) // 관리자 또는 사용자만 접근
			.antMatchers("/manager").hasRole(UserType.ADMIN.name())                            // 관리자만 접근
			.anyRequest().authenticated();                                                       // 인증된 사용자 만 접근

		http.addFilterBefore(doFilter(), BasicAuthenticationFilter.class);

		http.formLogin()                                                            // 폼을 통한 로그인을 이용
			.loginPage("/")                                                         // 로그인 뷰 페이지를 연결
			.usernameParameter("loginId")                                           // 로그인 페이지에서 "name태그"파라메터로 전송된 값
			.passwordParameter("password")                                          // 로그인 페이지에서 "name태그"파라메터로 전송된 값
			.defaultSuccessUrl("/");

		http.logout()                                                               // 로그아웃 처리
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))     // 로그아웃이 성공했을 경우 이동할 페이지
			.logoutSuccessUrl("/?state=logout")                                     // 로그아웃 성공 후 반환하는 URI
			.invalidateHttpSession(true)                                            // 로그아웃시 인증정보를 지우하고 세션을 무효화 시킨다는 설정
			.clearAuthentication(true)                                              // 로그아웃 시 인증이 초기화
			.deleteCookies("JSESSIONID");                                           // JSESSIONID으로 생성된 쿠키삭제

		http.sessionManagement()                                                    // 세션 정책 설정
			.maximumSessions(1)                                                     // 세션허용 인원
			.maxSessionsPreventsLogin(false)                                        // 로그인중일 경우 로그인이 안된다.(false일 경우 기존 사용자의 세션이 종료된다.)
			.expiredUrl("/sessionfailed");                                          // 중복 로그인이 발생했을 경우 이동할 주소(원인을 알려줄 주소)

		http.exceptionHandling()                                                    // 예외처리 핸들링
			.accessDeniedPage("/accessdenied");                                     // 예외가 발생했을때의 페이지 경로
		// @formatter:on
	}

	/**
	 * 비밀번호 암호화 클래스 Bean등록
	 *
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// BCrypt 해시 함수 이용하여, 비밀번호 저장하는 방법 사용
		return new BCryptPasswordEncoder();
	}

	/**
	 * 인증 요청에 따른 리다이렉션을 위한 빈을 등록
	 *
	 * @param filter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		// Spring Security 필터 보다 우선순위를 낮게 설정
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("google")
	public SocialResources google() {
		return new SocialResources();
	}

	private Filter doFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(doFilter(google(), new SocialAuthenticationFilter("/account/google")));
		filter.setFilters(filters);
		return filter;
	}

	private Filter doFilter(SocialResources client, OAuth2ClientAuthenticationProcessingFilter filter) {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(restTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
		filter.setTokenServices(tokenServices);
		tokenServices.setRestTemplate(restTemplate);
		return filter;
	}

}
