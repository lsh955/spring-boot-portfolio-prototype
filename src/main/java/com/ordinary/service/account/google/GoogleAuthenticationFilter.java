package com.ordinary.service.account.google;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class GoogleAuthenticationFilter extends OAuth2ClientAuthenticationProcessingFilter {

	private GoogleSocialService googleSocialService;

	public GoogleAuthenticationFilter(GoogleSocialService googleSocialService) {
		super("/account/google");
		this.googleSocialService = googleSocialService;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

		final OAuth2AccessToken accessToken = restTemplate.getAccessToken();
		log.info("accessToken >> " + accessToken);

		final OAuth2Authentication auth = (OAuth2Authentication) authResult;
		log.info("auth >> " + auth);

		final Object details = auth.getUserAuthentication().getDetails();
		log.info("details >> " + details);

		super.successfulAuthentication(request, response, chain, authResult);

	}

}
