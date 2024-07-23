package com.aegis.util.mail;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailUtil {
    private final Logger logger = LoggerFactory.getLogger(MailUtil.class);
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void send(EmailDetail details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom("ahmadsafei.ridwan@gmail.com");
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            logger.info("Success sending message!");
        } catch (Exception e) {
            logger.error("An error occurred, ", e);
        }
    }

}
