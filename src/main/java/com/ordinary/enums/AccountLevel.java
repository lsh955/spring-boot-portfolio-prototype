package com.ordinary.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-01-10
 * 
 * 계정 권한레벨
 */
@Getter
@RequiredArgsConstructor
public enum AccountLevel {

	ADMIN("ADMIN", "관리자"),
	MEMBER("MEMBER", "사용자");

	private final String key;
	private final String title;

}