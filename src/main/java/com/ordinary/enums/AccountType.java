package com.ordinary.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 이승환
 * @since 2020-07-24
 * <p>
 * 계정 가입타입
 */
@Getter
@RequiredArgsConstructor
public enum AccountType {

    LOCAL("LOCAL", "직접"),
    GOOGLE("GOOGLE", "구글");

    private final String key;
    private final String title;

}