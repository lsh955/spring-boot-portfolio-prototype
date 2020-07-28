package com.ordinary.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-07-20
 * 
 * 계정 상태
 */
@Getter
@RequiredArgsConstructor
public enum AccountState {

	DELETE("DELETE", "탈퇴"),
	STANDBY("STANDBY", "대기"),
	ACCESS("ACCESS", "승인");

	private final String key;
	private final String title;

}
