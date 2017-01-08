/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.basic.constants;

/**
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 5:23:04 PM yiliang.gyl Exp $
 */
public class SystemConstants {
    /**
     * 字符串 default
     */
    public static final String DEFAULT = "default";

    /**
     * 字符串 true
     */
    public static final String TRUE = "true";

    /**
     * 是否限制注册时候要有邀请码
     */
    public static final String INVITATION_CONSTANT = "INVITATION_CONSTANT";

    /**
     * 数组第一个位置
     */
    public static final int FIRST_INDEX = 0;

    /**
     * 平台最后一个位置
     */
    public static final int PLAT_LAST_INDEX = 255;

    /**
     * 大小为1
     */
    public static final int SIZE_ONE = 1;

    /**
     * 大小为0
     */
    public static final int SIZE_ZERO = 0;

    /**
     * 邀请码默认长度
     */
    public static final int INVITATION_CODE_LENGTH = 8;

    /**
     * 系统消息title
     */
    public static final String SYSTEM_MESSAGE_TITLE = "系统消息";

    /**
     * 系统默认管理员账号
     */
    public static final long ZERO_LONG = 0L;

    /**
     * 用户order by clause, 创建时间逆序排序
     */
    public static final String CREATE_TIME_DESC = "create_time desc";

    /**
     * 默认坏账天数
     */
    public static final String DEFAULT_BAD_LOAN_DAY = "90";

    /**
     * Account extParam中坏账字段
     */
    public static final String BAD_LOAN_DAY = "BAD_LOAN_DAY";

    /**
     * 默认逾期天数
     */
    public static final String DEFAULT_OVER_DUE_DAY = "30";
    /**
     * Account extParam中逾期字段
     */
    public static final String OVER_DUE_DAY = "OVER_DUE_DAY";

    /**
     * 贷款短息通知系统开关
     */
    public static final String LOAN_SMS_OPTION_KEY = "LOAN_SMS_OPTION";

    /**
     * 贷款短信设置模板
     */
    public static final String LOAN_SMS_OPTION_TEMPLATE = "LOAN_SMS_TEMPLATE";

    /**
     * 全局通知开关
     */
    public static final String GLOBAL_NOTIFICATION_OPTION = "GLOBAL_NOTIFICATION_OPTION";

    /**
     * 全局通知内容
     */
    public static final String GLOBAL_NOTIFICATION_TEXT = "GLOBAL_NOTIFICATION_TEXT";

    /**
     * 用户上传附件文件名最大长度
     */
    public static final int MAX_FILENAME_LENGTH = 122;

    /**
     * utf-8编码名称
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 社交认证URL KEY
     */
    public static final String SOCIAL_AUTH_URL = "SOCIAL_AUTH_URL";

    /**
     * 注册码短信限制次数
     */
    public static final String SMS_REGISTER_LIMIT = "SMS_REGISTER_LIMIT";

    /**
     * 注册码短信限制开关
     */
    public static final String SMS_REGISTER_OPTION = "SMS_REGISTER_OPTION";

    /**
     * 重置密码短信限制次数
     */
    public static final String SMS_RESET_PASSWORD_LIMIT = "SMS_RESET_PASSWORD_LIMIT";

    /**
     * 重置密码短信限制开关
     */
    public static final String SMS_RESET_PASSWORD_OPTION = "SMS_RESET_PASSWORD_OPTION";

    /**
     * 重置手机短信限制次数
     */
    public static final String SMS_RESET_MOBILE_LIMIT = "SMS_RESET_MOBILE_LIMIT";

    /**
     * 重置手机短信限制开关
     */
    public static final String SMS_RESET_MOBILE_OPTION = "SMS_RESET_MOBILE_OPTION";

    /**
     * String常量 today
     */
    public static final String TODAY = "today";

    /**
     * String常量 month
     */
    public static final String MONTH = "month";

    /**
     * String常量 overDueNumbers
     */
    public static final String OVERDUE_NUMBERS = "overDueNumbers";

    /**
     * String常量 badLoanNumbers
     */
    public static final String BAD_LOAN_NUMBERS = "badLoanNumbers";

    /**
     * 默认地区设置, 上海市
     */
    public static final String DEFAULT_REGION_CODE = "310000";

    /**
     * String常量 currentPayment
     */
    public static final String CURRENT_PAYMENT = "currentPayment";

    /**
     * String常量 默认配置邀请链接URL
     */
    public static final String INVITATION_URL = "invitationUrl";

    /**
     * String常量 还款总期数
     */
    public static final String PAY_TOTAL_CIRCLE = "payTotalCircle";

    /**
     * int常量 系统默认客户tag数量限制
     */
    public static int CUSTOMER_TAG_LIMIT = 3;

    /**
     * String常量 计数
     */
    public static final String COUNT = "count";

    /**
     * String常量 按月计数
     */
    public static final String PER_MONTH_COUNT = "perMonthCount";
}
