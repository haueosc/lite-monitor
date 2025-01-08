package com.example.entity.vo.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author : Doge2077 23:52 2025/1/8
 */
@Data
public class ReportClientVO {
    @NotNull
    Integer clientId;
    @DecimalMin(value = "0.1", inclusive = true, message = "内存负载报警阈值不能低于 0.1")
    @DecimalMax(value = "0.99", inclusive = true, message = "内存负载报警阈值不能高于 0.99")
    private Double reportMemory;

    @DecimalMin(value = "0.1", inclusive = true, message = "cpu负载报警阈值不能低于 0.1")
    @DecimalMax(value = "0.99", inclusive = true, message = "cpu负载报警阈值不能高于 0.99")
    private Double reportCpuUsage;
}
