package com.example.listener;

import com.example.utils.MailUtils;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * 用于处理邮件发送的消息队列监听器
 * @author Doge2077
 */
@Component
@RabbitListener(queues = "mail")
public class MailQueueListener {

    @Resource
    JavaMailSender sender;

    @Value("${spring.mail.username}")
    String username;

    /**
     * 处理邮件发送
     *
     * @param data 邮件信息
     */
    @RabbitHandler
    public void sendMailMessage(Map<String, Object> data) {
        String email = data.get("email").toString();
        Integer code = (Integer) data.get("code");
        SimpleMailMessage message = switch (data.get("type").toString()) {
            case "reset" -> MailUtils.buildMailMessage("【Lite-Monitor】密码重置验证邮件",
                    "尊敬的 Lite-Monitor 用户您好，您正在执行重置密码操作，验证码: " + code + "，有效时间3分钟，如非本人操作，如非本人操作，请登录平台修改密码，以防账号丢失。",
                    email, username);
            case "modify" -> MailUtils.buildMailMessage("【Lite-Monitor】邮件修改验证邮件",
                    "尊敬的 Lite-Monitor 用户您好，您正在绑定新的电子邮件地址，验证码: " + code + "，有效时间3分钟，如非本人操作，请登录平台修改密码，以防账号丢失。",
                    email, username);
            default -> null;
        };
        if (Objects.isNull(message)) {
            return;
        }
        sender.send(message);
    }
}
