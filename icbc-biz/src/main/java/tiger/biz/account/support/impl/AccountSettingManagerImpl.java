/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.account.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.account.support.AccountSettingManager;
import tiger.common.dal.persistence.account.query.AccountSettingQuery;
import tiger.core.domain.account.AccountSettingDomain;
import tiger.core.service.account.AccountSettingService;

import java.util.List;

/**
 * @author mi.li
 * @version v 0.1 16/4/10 下午4:57 mi.li Exp $
 */
@Service
public class AccountSettingManagerImpl implements AccountSettingManager{

    @Autowired
    AccountSettingService accountSettingService;

    @Override
    public List<AccountSettingDomain> query(AccountSettingQuery settingQuery) {
        return accountSettingService.query(settingQuery);
    }
}
