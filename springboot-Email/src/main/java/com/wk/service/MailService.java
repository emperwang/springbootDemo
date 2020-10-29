package com.wk.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @author: wk
 * @Date: 2020/10/29 17:22
 * @Description
 */
@Service
@Slf4j
@Getter
public class MailService {
    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.username}")
    private String to;   // 这里设置发送给自己
    @Autowired
    private JavaMailSender mailSender;
    // 发送一封简单的邮件
    public void sendSimpleMail(String to, String title, String content){
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setSentDate(new Date());
        message.setText(content);
        log.info("begin send.");
        mailSender.send(message);
        log.info("mail 发送成功");
    }

    // 发送一封有附件的邮件
    public void sendAttachmentMail(String to, String title, String content, List<File> files){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setText(to);
            helper.setSubject(title);
            helper.setText(content);
            String fileName = null;
            for (File file : files) {
                fileName = MimeUtility.encodeText(file.getParent(),"GB2312","B");
                helper.addAttachment(fileName,file);
            }
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
        log.info("附件邮件发送完成");
    }
}



















