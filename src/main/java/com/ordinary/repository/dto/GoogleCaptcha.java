package com.ordinary.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 이승환
 * @since 2020/01/15
 */
@Getter
@Setter
@AllArgsConstructor
public class GoogleCaptcha {

	private String success;
	private String challenge_ts;
	private String hostname;
	private String score;
	private String action;

}