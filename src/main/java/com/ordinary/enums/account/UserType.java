package com.ordinary.enums.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-01-10
 */
@Getter
@RequiredArgsConstructor
public enum UserType {

	ADMIN("ADMIN", "관리자"),
	MEMBER("MEMBER", "사용자");

	private final String key;
	private final String title;

}