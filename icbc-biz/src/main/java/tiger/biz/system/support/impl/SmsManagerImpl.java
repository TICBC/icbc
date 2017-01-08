/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.system.support.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.system.support.SmsManager;
import tiger.common.dal.enums.SmsVerifyCodeTypeEnum;
import tiger.common.util.RandomCodeUtil;
import tiger.core.service.account.AccountService;
import tiger.core.service.system.SmsService;

/**
 * @author zhangbin
 * @version v0.1 2015/10/5 21:16
 */
@Service
public class SmsManagerImpl implements SmsManager {

    private static final int CODE_LENGTH = 6;

    private Logger logger = Logger.getLogger(SmsManagerImpl.class);

    @Autowired
    SmsService smsService;

    @Autowired
    AccountService accountService;

    /**
     * 发送验短信证码
     * ~ 根君短信验证码类型发送
     *
     * @param mobile
     * @param smsVerifyCodeType
     * @param accountId
     * @return
     */
    @Override
    public boolean sendVerifyCode(String mobile, String smsVerifyCodeType, Long accountId) {
        SmsVerifyCodeTypeEnum codeTypeEnum = SmsVerifyCodeTypeEnum.getEnumByCode(smsVerifyCodeType);
        // 0. 将之前的验证码设置为失效
        smsService.setExpired(mobile, codeTypeEnum);

        String smsCode = RandomCodeUtil.getRandomNumer(CODE_LENGTH);
        // 1. 插入生成的验证码到数据
        if (!smsService.addSmsVerifyCode(mobile, smsCode, codeTypeEnum, accountId)) {
            logger.error("短信验证码插入数据库失败");
            return false;
        }
        // 2. 发送验证码
        if (!smsService.sendVerifyCode(mobile, smsCode)) {
            logger.error("发送短信验证码失败");
            // 3. 发送失败，主动失效
            smsService.setExpired(mobile, codeTypeEnum);
            return false;
        }
        return true;
    }

}
