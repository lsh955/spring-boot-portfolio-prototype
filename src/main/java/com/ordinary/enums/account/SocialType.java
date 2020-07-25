package com.ordinary.enums.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-07-24
 */
@Getter
@RequiredArgsConstructor
public enum SocialType {

	GOOGLE("GOOGLE", "구글");

	private final String key;
	private final String title;
	
}