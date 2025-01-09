package com.example.listener;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author : Doge2077 16:37 2025/1/9
 */
@Component
@RabbitListener(queues = "report")
public class ReportQueueListener {

    @Resource
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String sander;

    /**
     * 处理邮件发送
     */
    @RabbitHandler
    public void sendMailMessage(SimpleMailMessage mailMessage) {
        mailMessage.setFrom(sander);
        javaMailSender.send(mailMessage);
    }
}
