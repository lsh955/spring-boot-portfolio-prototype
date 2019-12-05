package com.springboot_portfolio.vo;

import lombok.*;
import javax.persistence.*;

/**
 * @author 이승환
 * @since 2019-11-27
 */
@Entity
@Table(name = "USERS_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {
    
    /**
     * Id : 테이블의 기본키로 매핑한다.
     * Column : 필드를 컬럼에 매핑한다.
     * GeneratedValue : 기본 키 값을 대한 생성을 지정한다.
     * GenerationType : 기본 키 생성 전략
     * IDENTITY : Mariadb의 경우 AUTO_INCREMENT를 사용하여 기본키를 생성한다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    
    @Column(name = "MEMBER_NAME", nullable = false)
    private String MEMBER_NAME;
    
    @Column(name = "MEMBER_ID", nullable = false)
    private String MEMBER_ID;
    
    @Column(name = "MEMBER_PWD", nullable = false)
    private String MEMBER_PWD;
    
    @Column(name = "MEMBER_EMAIL", nullable = false)
    private String MEMBER_EMAIL;
    
    @Column(name = "MEMBER_TEL", nullable = false)
    private String MEMBER_TEL;
    
    @Column(name = "MEMBER_IP", nullable = false)
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

    public String getMEMBER_NAME() {
        return MEMBER_NAME;
    }

    public String getMEMBER_ID() {
        return MEMBER_ID;
    }

    public String getMEMBER_PWD() {
        return MEMBER_PWD;
    }

    public String getMEMBER_EMAIL() {
        return MEMBER_EMAIL;
    }

    public String getMEMBER_TEL() {
        return MEMBER_TEL;
    }

    public String getMEMBER_IP() {
        return MEMBER_IP;
    }
    
}
