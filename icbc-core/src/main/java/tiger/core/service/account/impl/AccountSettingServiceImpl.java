/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.core.service.account.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.mapper.AccountSettingMapper;
import tiger.common.dal.persistence.account.query.AccountSettingQuery;
import tiger.core.domain.account.AccountSettingDomain;
import tiger.core.domain.account.convert.AccountSettingConvert;
import tiger.core.service.account.AccountSettingService;

import java.util.List;

/**
 * 账户设置相关服务
 *
 * @author alfred_yuan
 * @version v 0.1 19:50 alfred_yuan Exp $
 */
@Service
public class AccountSettingServiceImpl implements AccountSettingService {
    Logger logger = Logger.getLogger(AccountSettingServiceImpl.class);

    @Autowired
    private AccountSettingMapper accountSettingMapper;

    /**
     * @see AccountSettingService#add(AccountSettingDomain)
     */
    @Override
    public boolean add(AccountSettingDomain accountSettingDomain) {
        if (logger.isInfoEnabled()) {
            logger.info("开始插入一条账户设置, 参数为 [" + accountSettingDomain + "]");
        }
        return accountSettingMapper.insert(AccountSettingConvert.convert2DO(accountSettingDomain)) > 0;
    }

    /**
     * @see AccountSettingService#add(AccountSettingDomain)
     */
    @Override
    public boolean delete(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("开始删除一条账户设置, 参数为 [" + id + "]");
        }
        return accountSettingMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * @see AccountSettingService#add(AccountSettingDomain)
     */
    @Override
    public boolean update(AccountSettingDomain accountSettingDomain) {
        if (logger.isInfoEnabled()) {
            logger.info("开始更新一条账户设置, 参数为 [" + accountSettingDomain + "]");
        }
        if (accountSettingDomain == null || accountSettingDomain.getId() == null) {
            return true;
        }

        return accountSettingMapper.updateByPrimaryKeySelective(AccountSettingConvert.convert2DO(accountSettingDomain)) > 0;
    }

    /**
     * @see AccountSettingService#add(AccountSettingDomain)
     */
    @Override
    public List<AccountSettingDomain> query(AccountSettingQuery settingQuery) {
        if (settingQuery == null) {
            return null;
        }

        return AccountSettingConvert.convert2Domains(
                accountSettingMapper.query(settingQuery)
        );
    }
}
