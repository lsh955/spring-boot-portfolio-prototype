package com.ordinary.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-07-22
 *
 * Edit configuration : https://w1555054463-5ay337658.slack.com/services/B017PE2QWAG?added=1
 */
@Getter
@RequiredArgsConstructor
public enum SlackChannel {

	TARGET_CH("https://hooks.slack.com/services/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", "portfolio-bot");

	private final String webHookUrl;
	private final String channel;

}
