package com.example.utils;

/**
 * 一些常量字符串整合
 * @author Doge2077
 */
public final class Const {
    //JWT令牌
    public final static String JWT_BLACK_LIST = "jwt:blacklist:";
    public final static String JWT_FREQUENCY = "jwt:frequency:";
    //用户
    public final static String USER_BLACK_LIST = "user:blacklist:";
    //请求频率限制
    public final static String FLOW_LIMIT_COUNTER = "flow:counter:";
    public final static String FLOW_LIMIT_BLOCK = "flow:block:";
    //邮件验证码
    public final static String VERIFY_EMAIL_LIMIT = "verify:email:limit:";
    public final static String VERIFY_EMAIL_DATA = "verify:email:data:";
    //过滤器优先级
    public final static int ORDER_FLOW_LIMIT = -101;
    public final static int ORDER_CORS = -102;
    //请求自定义属性
    public final static String ATTR_USER_ID = "userId";
    public final static String ATTR_USER_ROLE = "userRole";
    public final static String ATTR_CLIENT = "client";
    //消息队列
    public final static String MQ_MAIL = "mail";
    public final static String MQ_REPORT = "report";
    //用户角色
    public final static String ROLE_ADMIN = "admin";
    public final static String ROLE_NORMAL = "user";

    // 报警频率限制 KEY
    public final static String REPORT_KEY = "report:client:break:";
}
