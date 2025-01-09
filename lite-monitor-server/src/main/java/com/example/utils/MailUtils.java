package com.example.utils;

import com.example.entity.dto.Account;
import com.example.entity.vo.response.ClientDetailsVO;
import org.springframework.mail.SimpleMailMessage;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Doge2077 16:39 2025/1/9
 */
public class MailUtils {

    /**
     * 构建邮件实体
     *
     * @param title    标题
     * @param content  内容
     * @param receiver 收件人
     * @param sender   发件人
     */
    public static SimpleMailMessage buildMailMessage(String title, String content, String receiver, String sender) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setFrom(sender);
        message.setText(content);
        message.setTo(receiver);
        return message;
    }

    /**
     * 构建报警信息
     *
     * @param user           用户
     * @param client         主机信息
     * @param reportCpuUsage cpu负载
     * @param reportMemory   内存负载
     */
    public static SimpleMailMessage reportMessage(Account user, ClientDetailsVO client, Double reportCpuUsage, Double reportMemory) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        DecimalFormat numFormat = new DecimalFormat("#.00");
        String cpu = numFormat.format(reportCpuUsage * 100) + "%";
        String memory = numFormat.format(reportMemory * 100) + "%";
        String content = String.format("尊敬的 Lite-Monitor 用户%s您好，您的主机:%s IP: %s 于 %s 检测到CPU负载：%s，内存负载: %s，超过预设报警阈值，可能正在遭受潜在的攻击行为，请您尽快处理！ ",
                user.getUsername(),
                client.getClientName(),
                client.getClientAddress(),
                formattedDate,
                cpu,
                memory);
        return buildMailMessage("【Lite-Monitor】报警提醒邮件", content, user.getEmail(), null);
    }


}
