package com.example.service;

import com.example.entity.dto.Client;
import com.example.entity.vo.request.RuntimeDetailVO;

/**
 * @author : Doge2077 22:18 2025/1/5
 */
public interface ReportService {
    void autoReport(Client client, RuntimeDetailVO runtime);
}
