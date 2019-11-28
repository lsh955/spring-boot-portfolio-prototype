package com.springboot_portfolio.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * @author 이승환
 * @since 2019-11-27
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USERS_MEMBER")
public class MemberEntity {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    
    @Column
    private String MEMBER_NAME;
    
    @Column
    private String MEMBER_ID;
    
    @Column
    private String MEMBER_PWD;
    
    @Column
    private String MEMBER_EMAIL;
    
    @Column
    private String MEMBER_TEL;
    
    @Column
    private String MEMBER_IP;
    
    @Builder
    public MemberEntity(int ID, String MEMBER_NAME, String MEMBER_ID, String MEMBER_PWD, String MEMBER_EMAIL, String MEMBER_TEL, String MEMBER_IP) {
        this.ID = ID;
        this.MEMBER_NAME = MEMBER_NAME;
        this.MEMBER_ID = MEMBER_ID;
        this.MEMBER_NAME = MEMBER_PWD;
        this.MEMBER_EMAIL = MEMBER_EMAIL;
        this.MEMBER_TEL = MEMBER_TEL;
        this.MEMBER_IP = MEMBER_IP;
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
    
}
