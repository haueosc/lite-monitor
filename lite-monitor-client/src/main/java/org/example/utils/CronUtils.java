package org.example.utils;

/**
 * @author Doge2077
 */
public class CronUtils {

    /**
     * 生成对应的 CRON 表达式。
     *
     * @param seconds 间隔的秒数 (0-59)
     * @return 对应的 CRON 表达式字符串
     * @throws IllegalArgumentException 如果秒数不在有效范围内，抛出异常
     */
    public static String generateCron(Integer seconds) {
        if (seconds == null || seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("时间非法");
        }
        // 返回 CRON 表达式，每隔 seconds 秒执行一次
        return String.format("*/%d * * * * ?", seconds);
    }
}
