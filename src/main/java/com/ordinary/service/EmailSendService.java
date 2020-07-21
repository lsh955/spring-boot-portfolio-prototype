package com.ordinary.service;

import lombok.RequiredArgsConstructor;
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
	private final SimpleMailMessage simpleMailMessage;

	/**
	 * SimpleMailMessage 메일발송
	 *
	 * @param from		보내는사람
	 * @param to		받는사람
	 * @param subject	제목
	 * @param contents	내용
	 */
	public void sendMail(String from, String to, String subject, String contents) {
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(contents);
		sender.send(simpleMailMessage);
	}

}
