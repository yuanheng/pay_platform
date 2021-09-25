package com.bootdo.common.config;

import java.util.Base64;

public class Constant {
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;

    public static final String SYMBOL_COMMA = ",";
    public static final String SYMBOL_DOT = ".";
    public static final String SYMBOL_Q = "?";
    public static final String SYMBOL_AMPERSAND = "&";
    public static final String SYMBOL_EQUAL = "=";

    /**
     * 常规肯定
     */
    public static final int YES = 1;

    public static final String LOCK_4_STAT_C_INFO = "LOCK_4_STAT_C_";

    public static final String LOCK_4_STAT_M_INFO = "LOCK_4_STAT_M";

    /**
     * redis中的key
     */
    public static final String PREFIX_OF_STAT_MERCHANT = "STAT_M_";

    /**
     * redis中的key
     */
    public static final String PREFIX_OF_STAT_CODER = "STAT_C_";

    /**
     * redis中商户基本信息的key
     */
    public static final String PREFIX_OF_M_BASIC_INFO = "BIZ_M_";

    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS = "redis";

    public static String LOG_ERROR = "error";

    public static final String SYMBOL_OF_QUOTATION = "\"";

    public static final String[] UA = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
            "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};

    public static final String username = "foo";
    public static final String password = "bar";
    public static final String login = username + ":" + password;
    public static final String base64login = new String(Base64.getEncoder().encode(login.getBytes()));



}
