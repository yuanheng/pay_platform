package com.bootdo.app.config;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public final static String MERCHANT_NO_KEY = "merchant_no_key:{merchantNo}";

    public final static String ORDER_NO_TIMEOUT_KEY = "order_no:";

    public final static String ORDER_NO_KEY = "order_no:{orderNo}";

    public final static String ORDER_TIMER_KEY = "order_time_key";

    public final static String ORDER_PAY_URL = "order_pay_url";


    public final static String PAYMENTINFO_LIST = "{payType}_code_list";



    public final static String PRODUCT_TASK_INFO_QUEUE = "product_task_info_queue";
    public final static String CURRENT_SYMBOL_PRICE_PRE = "current_symbol_price_pre_";
    public final static String COIN_TASK_STATUS_PRE = "coin_task_status_pre_";
    public final static String MEMBER_TASK_PRE = "member_coin_task_{mid}_pre_";
    public final static String PRODUCT_LOCK_ID = "product_lock_id";
    public final static String MEMBER_HUOBI_SPOT_ACCOUNT_PRE = "member_huobi_spot_account_pre_";

    public final static String MEMBER_HUOBI_SPOT_BALANCE_PRE = "member_huobi_spot_blance_pre_";
    public final static String MEMBER_HUOBI_API_KEY_PRE = "member_huobi_api_key_pre_";
    public final static String MEMBER_HUOBI_SECRET_PRE = "member_huobi_secret_key_pre_";
    public final static String MEMBER_ORDER_PRE = "member_order_{mid}_{symbol}_pre_";
    public final static Integer MEMBER_ORDER_DELAY_TIME = 5;








    public final static String COIN_USDT = "usdt";




    public final static String NYC_PRICE = "nyc_price";

    public final static String MY_MOBILE = "my_mobile";
    public final static String MY_URL="my_url";
    public final static String APP_PAY_TYPE="app_pay_type";
    public final static String APP_VERSION = "app_version";
    public final static String ANDRIOD_VERSION= "android_verison";
    public final static String IOS_URL="ios_url";
    public final static String ANDRIOD_URL="andriod_url";
    public final static String NOTICe_MSG="notice";
    public final static String KEFU_URL="kefu_url";
    public final static String WEB_SITE="website_url";

    public final static String TOKEN_FORMAT = "";

    public final static String PAY_MID_PRE = "PAY_MID_PRE_";

    public final static Integer TRADELOG_NO_TIME = 30 * 60;

    public final static String USER_TOKEN="user_token";

    public final static Long ACTIVE_TIME = 7 * 24 * 60 * 60L;

    public final static Long WEEK_ACITVE_TIME = 7 * 24 * 60 * 60L;

    public final static Long MONTH_ACITVE_TIME = 30 * 24 * 60 * 60L;

    public final static String MID_TOKEN_KEY="mid_token_key";

    public final static String M_AGREEMENT_KEY_PREFIX = "mid_agreement_key_";

    public final static String PROFIT_PERCENT_A = "0.0060";
    public final static String PROFIT_PERCENT_B = "0.0070";
    public final static String PROFIT_PERCENT_C = "0.0080";


    public final static String PROFIT_GRAND= "0.0010";


    public final static String PER_BUY_MIN= "500";

    public final static int NUM_A= 3;

    public final static int NUM_B= 5;

    public final static int NUM_C= 20;

    public final static int NUM_D= 50;

    public static final String BACK_PAY_PRE_KEY="backPay_";
    public static final String POOL_USDT_AMOUNT = "pool_usdt_amount";
    public static final String COOL_WALLET_ADDRESS = "cool_wallet_address";
    public static final String COOL_WALLET_ADDRESS_IMG = "coll_wallet_img";

    public static final String COIN_LIST = "coin_list";

    public static String BACK_AGREEMENT_PRE_KEY="backAgreement_";

    public static int BACK_PAY_TIME_MAX = 17280;

    public static int BACK_PAY_TIME_MIN = 14400;


    //2019-06-22 对应的毫秒数
    public static Long updateDateTime = 1561161600000L;



    public static List<String> masterUsers = Arrays.asList("13530689238","17896090495","17110995611");
    public static Map<String,String> teams;
    public static Map<String,String> teamsProfit;


    static {
        teams = new HashMap<>();
        teams.put("13530689238","53");
        teams.put("17896090495","51");
        teams.put("17110995611","55");

        teamsProfit = new HashMap<>();

        teamsProfit.put("13530689238","435000");
        teamsProfit.put("17896090495","423105");
        teamsProfit.put("17110995611","450200");

    }

    public static String getMerchantNoKey(String merchantNo){
        return MERCHANT_NO_KEY.replace("{merchantNo}", merchantNo);
    }

    public static String getOrderKey(String orderNo) {
       return Constants.ORDER_NO_KEY.replace("{orderNo}", orderNo);
    }
}
