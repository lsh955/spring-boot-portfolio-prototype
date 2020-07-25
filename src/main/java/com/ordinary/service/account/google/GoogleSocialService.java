package com.ordinary.service.account.google;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class GoogleSocialService {

	//TODO : 아무것도 활용되지 않는다.

	private UsernamePasswordAuthenticationToken setAuthenticationToken(Object user) {
		return new UsernamePasswordAuthenticationToken(user, null, getAuthorities("ROLE_USERs"));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

}
