package com.example.entity.vo.request;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@TableName("db_client_details")
public class ClientDetailVO {

    // 客户端系统信息
    // 客户端 ip 地址
    @NonNull
    String clientAddress;
    // 系统架构
    @NonNull
    String osArch;
    // 操作系统
    @NonNull
    String osName;
    // 版本号
    @NonNull
    String osVersion;
    // 操作系统位数
    @NonNull
    Integer osBit;
    // cpu 名称
    @NonNull
    String cpuName;
    // cpu 核心数
    @NonNull
    Integer cpuCores;
    // 内存容量
    @NonNull
    Double osMemory;
    // 磁盘容量
    @NonNull
    Double diskMemory;
}
