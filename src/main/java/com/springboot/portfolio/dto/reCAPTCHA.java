package com.springboot.portfolio.dto;

/**
 * @author 이승환
 * @since 2020/01/15
 */
public class reCAPTCHA {
    private boolean success;
    private Integer number;
    private String action;
    private String challenge_ts;
    private String hostname;
    private String error_codes;

    public reCAPTCHA() {
        super();
    }

    public reCAPTCHA(boolean success, Integer number, String action, String challenge_ts, String hostname,
                     String error_codes) {
        super();
        this.success = success;
        this.number = number;
        this.action = action;
        this.challenge_ts = challenge_ts;
        this.hostname = hostname;
        this.error_codes = error_codes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getError_codes() {
        return error_codes;
    }

    public void setError_codes(String error_codes) {
        this.error_codes = error_codes;
    }
}