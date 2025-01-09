package com.example.service.impl;

import com.example.entity.dto.Account;
import com.example.entity.dto.Client;
import com.example.entity.vo.request.RuntimeDetailVO;
import com.example.entity.vo.response.ClientDetailsVO;
import com.example.service.AccountService;
import com.example.service.ClientService;
import com.example.service.ReportService;
import com.example.utils.Const;
import com.example.utils.MailUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : liyusen02 22:45 2025/1/5
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    AmqpTemplate rabbitTemplate;

    @Resource
    private AccountService accountService;

    @Resource
    private ClientService clientService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void autoReport(Client client, RuntimeDetailVO runtime) {
        Double cpuUsage = runtime.getCpuUsage();
        Double memoryUsage = runtime.getMemoryUsage();
        ClientDetailsVO clientDetailsVO = clientService.clientDetails(client.getClientId());
        memoryUsage = memoryUsage / clientDetailsVO.getOsMemory();
        Double reportMemory = clientDetailsVO.getReportMemory();
        Double reportCpuUsage = clientDetailsVO.getReportCpuUsage();
        List<Account> users = accountService.getMailByClientId(client.getClientId());
        if (cpuUsage > reportCpuUsage || memoryUsage > reportMemory) {
            String reportKey = stringRedisTemplate.opsForValue().get(Const.REPORT_KEY + clientDetailsVO.getClientId());
            // 如果 Key 不存在，则发送邮件报警
            if (StringUtils.isBlank(reportKey)) {
                for (Account user : users) {
                    SimpleMailMessage simpleMailMessage = MailUtils.reportMessage(user, clientDetailsVO, cpuUsage, memoryUsage);
                    stringRedisTemplate.opsForValue().set(Const.REPORT_KEY + clientDetailsVO.getClientId(), user.getEmail(), 1, TimeUnit.MINUTES);
                    rabbitTemplate.convertAndSend(Const.MQ_REPORT, simpleMailMessage);
                }
            }
        }

    }
}
