package com.ordinary.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-07-23
 */
@Getter
@RequiredArgsConstructor
public enum SocialType {

	GOOGLE("Google", "구글");

	private final String key;
	private final String title;

}
