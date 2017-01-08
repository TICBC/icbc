/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.account.convert;

import tiger.common.dal.enums.AccountSocialTypeEnum;
import tiger.common.dal.persistence.account.AccountBindDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.account.AccountBindDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 6:16 PM yiliang.gyl Exp $
 */
public class AccountBindConvert {

    /**
     * @param source
     * @return
     */
    public static AccountBindDomain convert2Domain(AccountBindDO source) {
        if (source == null) {
            return null;
        }
        AccountBindDomain domain = new AccountBindDomain();
        BeanUtil.copyPropertiesWithIgnores(source, domain);

        domain.setAccountType(AccountSocialTypeEnum.getEnumByCode(source.getAccountType()));

        return domain;
    }

    /**
     * @param sources
     * @return
     */
    public static List<AccountBindDomain> convert2Domain(List<AccountBindDO> sources) {
        List<AccountBindDomain> target = new ArrayList<>();
        for (AccountBindDO bindDO : sources) {
            target.add(convert2Domain(bindDO));
        }
        return target;
    }

    /**
     * @param source
     * @return
     */
    public static AccountBindDO convert2DO(AccountBindDomain source) {
        if (source == null) {
            return null;
        }
        AccountBindDO target = new AccountBindDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        if (source.getAccountType() != null) {
            target.setAccountType(source.getAccountType().getCode());
        }
        return target;
    }

}
