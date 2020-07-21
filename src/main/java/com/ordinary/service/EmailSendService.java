package com.ordinary.service;

import com.ordinary.repository.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2020-03-04
 */
@Service
@RequiredArgsConstructor
public class EmailSendService {

	private final MailSender sender;

	/**
	 * 회원가입 시 이메일 처리
	 *
	 * @param userDao
	 */
	public void signUpEmail(UserDao userDao) {
		setMail("lshk955@naver.com", userDao.getUserEmail(), userDao.getLoginId() + "님 회원가입이 정상처리 되었습니다.", userDao.getLoginId() + "아이디로 회원가입이 정상 처리되었습니다.");
	}

	/**
	 * SimpleMailMessage 메일발송
	 *
	 * @param from		보내는사람
	 * @param to		받는사람
	 * @param subject	제목
	 * @param text		내용
	 */
	private void setMail(String from, String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		sender.send(message);
	}

}
