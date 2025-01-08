package com.example.entity.vo.response;

import lombok.Data;

@Data
public class ClientDetailsVO {
    Integer clientId;
    String clientName;
    Boolean online;
    String node;
    String location;
    String clientAddress;
    String cpuName;
    String osName;
    String osVersion;
    Double osMemory;
    Integer cpuCores;
    Double diskMemory;
    // 报警内存负载阈值
    Double reportMemory;
    // 报警Cpu负载阈值
    Double reportCpuUsage;
}
