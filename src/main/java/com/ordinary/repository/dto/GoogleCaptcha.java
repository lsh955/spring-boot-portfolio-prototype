package com.ordinary.repository.dto;

/**
 * @author 이승환
 * @since 2020/01/15
 *
 * Google reCAPTCHA v3 또는 Invisible reCAPTCHA 응답
 * https://developers.google.com/recaptcha/docs/v3?hl=ko
 */
public class GoogleCaptcha {

    private String success;     // 이 요청이 해당 사이트에 유효한 reCAPTCHA 토큰인지 여부
    private String score;       // 요청에 대한 점수 (0.0-1.0)
    private String action;      // 요청에 대한 action
    private String challenge_ts;// 챌린지로드의 타임 스탬프 (ISO 형식 yyyy-MM-dd'T'HH : mm : ssZZ)
    private String hostname;    // 요청에 대한 hostname

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(String challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

}