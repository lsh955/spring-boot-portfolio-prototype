package com.springboot_portfolio.vo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author 이승환
 * @since 2019-11-27
 */
@ToString
@NoArgsConstructor
public class MemberVo {
    
    private int ID;
    private String MEMBER_NAME;
    private String MEMBER_ID;
    private String MEMBER_PWD;
    private String MEMBER_EMAIL;
    private String MEMBER_TEL;
    private String MEMBER_IP;
    private LocalDateTime MEMBER_FIRSTDATE;
    private LocalDateTime MEMBER_LASTDATE;
    
    @Builder
    public MemberVo(int ID, String MEMBER_NAME, String MEMBER_ID, String MEMBER_PWD, String MEMBER_EMAIL, String MEMBER_TEL, String MEMBER_IP) {
        this.ID = ID;
        this.MEMBER_NAME = MEMBER_NAME;
        this.MEMBER_ID = MEMBER_ID;
        this.MEMBER_NAME = MEMBER_PWD;
        this.MEMBER_EMAIL = MEMBER_EMAIL;
        this.MEMBER_TEL = MEMBER_TEL;
        this.MEMBER_IP = MEMBER_IP;
    }
    
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .ID(ID)
                .MEMBER_NAME(MEMBER_NAME)
                .MEMBER_ID(MEMBER_ID)
                .MEMBER_PWD(MEMBER_PWD)
                .MEMBER_EMAIL(MEMBER_EMAIL)
                .MEMBER_TEL(MEMBER_TEL)
                .MEMBER_IP(MEMBER_IP)
                .build();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMEMBER_NAME() {
        return MEMBER_NAME;
    }

    public void setMEMBER_NAME(String MEMBER_NAME) {
        this.MEMBER_NAME = MEMBER_NAME;
    }

    public String getMEMBER_ID() {
        return MEMBER_ID;
    }

    public void setMEMBER_ID(String MEMBER_ID) {
        this.MEMBER_ID = MEMBER_ID;
    }

    public String getMEMBER_PWD() {
        return MEMBER_PWD;
    }

    public void setMEMBER_PWD(String MEMBER_PWD) {
        this.MEMBER_PWD = MEMBER_PWD;
    }

    public String getMEMBER_EMAIL() {
        return MEMBER_EMAIL;
    }

    public void setMEMBER_EMAIL(String MEMBER_EMAIL) {
        this.MEMBER_EMAIL = MEMBER_EMAIL;
    }

    public String getMEMBER_TEL() {
        return MEMBER_TEL;
    }

    public void setMEMBER_TEL(String MEMBER_TEL) {
        this.MEMBER_TEL = MEMBER_TEL;
    }

    public String getMEMBER_IP() {
        return MEMBER_IP;
    }

    public void setMEMBER_IP(String MEMBER_IP) {
        this.MEMBER_IP = MEMBER_IP;
    }

    public LocalDateTime getMEMBER_FIRSTDATE() {
        return MEMBER_FIRSTDATE;
    }

    public void setMEMBER_FIRSTDATE(LocalDateTime MEMBER_FIRSTDATE) {
        this.MEMBER_FIRSTDATE = MEMBER_FIRSTDATE;
    }

    public LocalDateTime getMEMBER_LASTDATE() {
        return MEMBER_LASTDATE;
    }

    public void setMEMBER_LASTDATE(LocalDateTime MEMBER_LASTDATE) {
        this.MEMBER_LASTDATE = MEMBER_LASTDATE;
    }
}
