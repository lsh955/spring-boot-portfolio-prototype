package com.ordinary.provider;

import com.ordinary.details.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 이승환
 * @since 2020-07-17
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthenticationProviderImpl implements AuthenticationProvider {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserDetailsServiceImpl userDetailsService;

	/**
	 * AuthenticationManager.authenticate (Authentication)와 동일한 방식으로 인증을 수행
	 * (Performs authentication with the same contract as AuthenticationManager.authenticate(Authentication))
	 *
	 * @param authentication
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 로그인 페이지에서 넘어온 값
		String email = (String) authentication.getPrincipal();
		String pwd = (String) authentication.getCredentials();

		// 사용자 정보 불러오기
		UserDetails callUser = userDetailsService.loadUserByUsername(email);

		// 비밀번호 검증
		if (passwordEncoder.matches(pwd, callUser.getPassword())) {
			return new UsernamePasswordAuthenticationToken(email, pwd, callUser.getAuthorities());
		} else {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// AuthenticationProvider를 사용할 경우 True를 반환.
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
