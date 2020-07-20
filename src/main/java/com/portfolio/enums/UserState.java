package com.portfolio.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-07-20
 */
@Getter
@RequiredArgsConstructor
public enum UserState {

	DELETE("DELETE", "탈퇴"),
	STANDBY("STANDBY", "대기");

	private final String key;
	private final String title;

}
