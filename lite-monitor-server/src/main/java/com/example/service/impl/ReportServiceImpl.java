package com.example.service.impl;

import com.example.entity.dto.Client;
import com.example.entity.vo.request.RuntimeDetailVO;
import com.example.entity.vo.response.ClientDetailsVO;
import com.example.service.AccountService;
import com.example.service.ClientService;
import com.example.service.ReportService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : liyusen02 22:45 2025/1/5
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private AccountService accountService;

    @Resource
    private ClientService clientService;

    @Override
    public void autoReport(Client client, RuntimeDetailVO runtime) {
        Double cpuUsage = runtime.getCpuUsage();
        Double memoryUsage = runtime.getMemoryUsage();
        ClientDetailsVO clientDetailsVO = clientService.clientDetails(client.getClientId());
        List<String> mail = accountService.getMailByClientId(client.getClientId());

    }
}
