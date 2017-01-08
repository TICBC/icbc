/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.account.support;


import tiger.common.dal.persistence.account.query.AccountSettingQuery;
import tiger.core.basic.BaseResult;
import tiger.core.domain.account.AccountSettingDomain;

import java.util.List;

/**
 * @author mi.li
 * @version v 0.1 16/4/10 下午4:56 mi.li Exp $
 */
public interface AccountSettingManager {

    /**
     * 查询账户设置
     *
     * @param settingQuery
     * @return
     */
    List<AccountSettingDomain> query(AccountSettingQuery settingQuery);

}
