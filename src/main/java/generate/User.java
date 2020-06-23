package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    /**
     * 사용자 권한
     */
    private String userType;
    /**
     * 사용자 성함
     */
    private String userName;
    /**
     * 사용자 아이디
     */
    private String loginId;
    /**
     * 사용자 전화번호
     */
    private String userTel;
    /**
     * 사용자 이메일
     */
    private String userEmail;
    /**
     * 사용자 패스워드
     */
    private String password;
    /**
     * 최초 회원가입 일자
     */
    private Date userFirstdate;
    /**
     * 최초 로그인일자
     */
    private Date userLogindate;
    /**
     * 사용자 로그인아이피
     */
    private String userIpaddress;
}