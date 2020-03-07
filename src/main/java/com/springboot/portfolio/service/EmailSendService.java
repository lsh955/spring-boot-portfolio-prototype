package com.springboot.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author 이승환
 * @since 2020-03-04
 */
@Service
public class EmailSendService {

    @Autowired
    private MailSender sender;

    public void sendMail(String from, String to, String subject, String text) {
        SimpleMailMessage message = createMail(from, to, subject, text);
        try {
            sender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private SimpleMailMessage createMail(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);                // 보내는사람
        message.setTo(to);                    // 받는사람
        message.setSubject(subject);          // 제목
        message.setText(text);                // 내용
        return message;
    }

}
