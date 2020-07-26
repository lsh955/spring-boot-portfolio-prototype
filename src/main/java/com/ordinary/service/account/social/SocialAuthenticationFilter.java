package com.ordinary.service.account.social;

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

/**
 * @author 이승환
 * @since 2020-07-26
 */
@Slf4j
public class SocialAuthenticationFilter extends OAuth2ClientAuthenticationProcessingFilter {

	public SocialAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

		OAuth2AccessToken accessToken = restTemplate.getAccessToken();
		log.info("\n accessToken >> " + accessToken);

		OAuth2Authentication auth = (OAuth2Authentication) authResult;
		log.info("\n auth >> " + auth);

		Object details = auth.getUserAuthentication().getDetails();
		log.info("\n details >> " + details);

		Object clientId = auth.getOAuth2Request().getClientId();
		log.info("\n clientId >> " + clientId);

		Object approved = auth.getOAuth2Request().isApproved();
		log.info("\n approved >> " + approved);

		super.successfulAuthentication(request, response, chain, authResult);

	}

}
