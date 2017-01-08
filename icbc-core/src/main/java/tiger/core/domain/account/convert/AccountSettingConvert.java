/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.core.domain.account.convert;

import org.apache.commons.collections.CollectionUtils;
import tiger.common.dal.enums.AccountSettingBizTypeEnum;
import tiger.common.dal.enums.AccountSettingTypeEnum;
import tiger.common.dal.persistence.account.AccountSettingDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.account.AccountSettingDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 19:18 alfred_yuan Exp $
 */
public class AccountSettingConvert {

    private AccountSettingConvert() {
    }

    public static AccountSettingDomain convert2Domain(AccountSettingDO source) {
        if (source == null) {
            return null;
        }

        AccountSettingDomain target = new AccountSettingDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        target.setBizType(AccountSettingBizTypeEnum.getEnumByCode(source.getBizType()));
        target.setSettingType(AccountSettingTypeEnum.getEnumByCode(source.getSettingType()));

        return target;
    }

    public static List<AccountSettingDomain> convert2Domains(List<AccountSettingDO> sources) {
        List<AccountSettingDomain> targets = new ArrayList<>();

        if (CollectionUtils.isEmpty(sources)) {
            return targets;
        }

        sources.forEach(source -> targets.add(convert2Domain(source)));

        return targets;
    }

    public static AccountSettingDO convert2DO(AccountSettingDomain source) {
        if (source == null) {
            return null;
        }

        AccountSettingDO target = new AccountSettingDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        if (source.getBizType() != null) {
            target.setBizType(source.getBizType().getCode());
        }
        if (source.getSettingType() != null) {
            target.setSettingType(source.getSettingType().getCode());
        }

        return target;
    }
}
