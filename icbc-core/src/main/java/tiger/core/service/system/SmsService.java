/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.system;

import tiger.common.dal.enums.SmsVerifyCodeTypeEnum;

import java.util.Date;

/**
 * @author zhangbin
 * @version v0.1 2015/10/6 17:27
 */
public interface SmsService {

    /**
     * 插入要发送到mobile类型为type的验证码code到数据库
     *
     * @param mobile
     * @param code
     * @param typeEnum
     * @param accountId
     * @return
     */
    boolean addSmsVerifyCode(String mobile, String code, SmsVerifyCodeTypeEnum typeEnum, Long accountId);

    /**
     * 发送验证码code到手机mobile
     *
     * @param mobile
     * @param code
     * @return
     */
    boolean sendVerifyCode(String mobile, String code);

    boolean sendSmsWithContent(String mobile, String content);

    boolean sendSms(String mobile, Long smsTplId);

    /**
     * 从数据库中获取最后一次生成的验证码
     */
    String getMsgCodeFromDB(String mobile, SmsVerifyCodeTypeEnum typeEnum);

    /**
     * 将发送到mobile的code验证码设置为过期
     *
     * @param mobile
     * @param typeEnum
     * @return
     */
    boolean setExpired(String mobile, SmsVerifyCodeTypeEnum typeEnum);


    /**
     * 一天发了几次短信
     * @param accountId
     * @param smsVerifyCodeTypeEnum
     * @param day
     * @return
     */
    int countVerifySmsesInOneDayByAccountId(Long accountId, SmsVerifyCodeTypeEnum smsVerifyCodeTypeEnum, Date day);

    /**
     * 一天发送了几条短信
     * @param mobile
     * @param type
     * @param date
     * @return
     */
    int countVerifySmsesInOneDayByMobile(String mobile, SmsVerifyCodeTypeEnum type, Date date);
}
