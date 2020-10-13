package com.ordinary.service.account;

import com.ordinary.repository.dao.UserDao;
import com.ordinary.details.UserDetailsServiceImpl;
import com.ordinary.repository.dto.UserDto;
import com.ordinary.repository.mapper.UserMapper;
import com.ordinary.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2020-07-20
 * <p>
 * 회원가입 처리
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserMapper userMapper;
    private final EmailSendService emailSendService;

    /**
     * 회원가입전 이메일 중복조회
     *
     * @param userDto
     * @return true(성공) 또는 false(중복)
     */
    public boolean isSignUpEmailCheck(UserDto userDto) {
        if (!userDetailsService.isEmailCheck(userDto.getEmail())) {
            return false;
        }
        inputSignUpSave(userDto);
        return true;
    }

    /**
     * 회원정보 DATABASE 저장
     *
     * @param userDto
     */
    private void inputSignUpSave(UserDto userDto) {
        UserDao userDao = UserDao.fromSendUser(userDto);  // 회원정보 Default값
        userMapper.inputSignUp(userDao);    // 최종 저장

        // 이메일 전송
        emailSendService.sendMail("lshk955@naver.com",
                userDao.getEmail(),
                userDao.getName() + "님 회원가입이 정상처리 되었습니다.",
                userDao.getEmail() + "아이디로 회원가입이 정상 처리되었습니다.");
    }

}
