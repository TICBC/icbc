/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.system.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tiger.common.dal.enums.SmsVerifyCodeTypeEnum;
import tiger.common.dal.persistence.system.SmsVerifyCodeDO;
import tiger.common.dal.persistence.system.example.SmsVerifyCodeExample;
import tiger.common.dal.persistence.mapper.SmsVerifyCodeMapper;
import tiger.common.util.DateUtil;
import tiger.common.util.component.sms.SmsComponent;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.service.system.SmsService;

import java.util.Date;
import java.util.List;

/**
 * @author zhangbin
 * @version v0.1 2015/10/6 17:38
 */
@Service
public class SmsServiceImpl implements SmsService {

    private Logger logger = Logger.getLogger(SmsServiceImpl.class);

    private static final int FIRST_INDEX = 0;

    @Autowired
    SmsVerifyCodeMapper smsVerifyCodeMapper;



    @Override
    public boolean addSmsVerifyCode(String mobile, String code, SmsVerifyCodeTypeEnum typeEnum, Long accountId) {
        SmsVerifyCodeDO sms = new SmsVerifyCodeDO();

        sms.setExpireTime(DateUtil.addMinutes(new Date(), 15));//15分钟
        sms.setMobile(mobile);
        sms.setCode(code);
        sms.setSmsType(typeEnum.getCode());
        sms.setIsActive(true);
        sms.setAccountId(accountId);

        return smsVerifyCodeMapper.insert(sms) > 0;
    }

    @Override
    public boolean sendSms(String mobile, Long smsTplId) {
        return false;
    }

    @Override
    public boolean sendVerifyCode(String mobile, String code) {
        boolean result = SmsComponent.sendVerifyCode(mobile, code);
        return result;
    }

    @Override
    public boolean sendSmsWithContent(String mobile, String content) {
        boolean result = SmsComponent.SendSms(mobile, content);
        return result;
    }


    /**
     * 根据手机号码取得短信验证码内同
     *
     * @param mobile
     * @return
     */
    @Override
    public String getMsgCodeFromDB(String mobile, SmsVerifyCodeTypeEnum typeEnum) {
        Date now = new Date();

        SmsVerifyCodeExample example = new SmsVerifyCodeExample();
        SmsVerifyCodeExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        criteria.andCreateTimeLessThan(now);
        criteria.andExpireTimeGreaterThan(now);
        criteria.andIsActiveEqualTo(true);
        criteria.andSmsTypeEqualTo(typeEnum.getCode());
        example.setOrderByClause("expire_time desc");

        List<SmsVerifyCodeDO> verifyCodeList = smsVerifyCodeMapper.selectByExample(example);


        if (CollectionUtils.isEmpty(verifyCodeList)) {
            throw new TigerException(ErrorCodeEnum.BIZ_VERY_CODE, "验证码已过期");
        }
        String verifyCode = verifyCodeList.get(FIRST_INDEX).getCode();

        return verifyCode;
    }

    /**
     * 把一个号码的一个类型的所有短信设为失效
     *
     * @param mobile
     * @param typeEnum
     * @return
     */
    @Override
    public boolean setExpired(String mobile, SmsVerifyCodeTypeEnum typeEnum) {
        SmsVerifyCodeDO verifyCodeDO = new SmsVerifyCodeDO();
        verifyCodeDO.setIsActive(false);

        SmsVerifyCodeExample example = new SmsVerifyCodeExample();
        SmsVerifyCodeExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile.trim());
        criteria.andSmsTypeEqualTo(typeEnum.getCode());

        int count = smsVerifyCodeMapper.updateByExampleSelective(verifyCodeDO, example);
        return (count > 0);
    }

    @Override
    public int countVerifySmsesInOneDayByAccountId(Long accountId, SmsVerifyCodeTypeEnum smsVerifyCodeTypeEnum, Date day) {
        if (accountId == null || smsVerifyCodeTypeEnum == null || day == null) {
            return -1;
        }

        Date begin = DateUtil.getDayBegin(day);
        Date end = DateUtil.getDayEnd(day);

        return smsVerifyCodeMapper.countVerifySmses(accountId, smsVerifyCodeTypeEnum.getCode(), begin, end);
    }

    @Override
    public int countVerifySmsesInOneDayByMobile(String mobile, SmsVerifyCodeTypeEnum smsVerifyCodeTypeEnum, Date day) {
        if (mobile == null || smsVerifyCodeTypeEnum == null || day == null) {
            return -1;
        }

        SmsVerifyCodeExample example = new SmsVerifyCodeExample();
        SmsVerifyCodeExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile).andSmsTypeEqualTo(smsVerifyCodeTypeEnum.getCode())
                .andCreateTimeBetween(DateUtil.getDayBegin(day), DateUtil.getDayEnd(day));

        return smsVerifyCodeMapper.countByExample(example);
    }
}
