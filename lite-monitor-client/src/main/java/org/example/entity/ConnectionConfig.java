package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Doge2077
 */
@Data
@AllArgsConstructor
public class ConnectionConfig {
    String serverAddress;
    String token;
    Integer cron;
}
