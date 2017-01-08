package tiger.core.domain.account.convert;

import tiger.common.dal.persistence.account.AccountBaseDO;
import tiger.common.util.BeanUtil;
import tiger.common.util.JsonConverterUtil;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.domain.account.AccountBaseDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kris Chan on 4:14 PM 3/27/16 .
 * All right reserved.
 */
public class AccountBaseConvert {
    /**
     * @param accountBaseDO
     * @return
     */
    public static AccountBaseDomain convert2Domain(AccountBaseDO accountBaseDO) {
        if (accountBaseDO == null) {
            return null;
        }
        AccountBaseDomain baseDomain = new AccountBaseDomain();
        BeanUtil.copyPropertiesWithIgnores(accountBaseDO, baseDomain);
        if (StringUtil.isNotBlank(accountBaseDO.getExtParams())) {
            baseDomain.setExtParams(JsonUtil.fromJson(accountBaseDO.getExtParams(), HashMap.class));
        }
        return baseDomain;
    }

    /**
     * @param accountBaseDomain
     * @return
     */
    public static AccountBaseDO convert2DO(AccountBaseDomain accountBaseDomain) {
        if (accountBaseDomain == null) {
            return null;
        }
        AccountBaseDO accountBaseDO = new AccountBaseDO();
        BeanUtil.copyPropertiesWithIgnores(accountBaseDomain, accountBaseDO);
        if (null != accountBaseDomain.getExtParams()) {
            accountBaseDO.setExtParams(JsonConverterUtil.serialize(accountBaseDomain.getExtParams()));
        }
        return accountBaseDO;
    }

    /**
     * @param accountBaseDOs
     * @return
     */
    public static List<AccountBaseDomain> convert2Domain(List<AccountBaseDO> accountBaseDOs) {
        List<AccountBaseDomain> accountBaseDomains = new ArrayList<>();
        for (AccountBaseDO accountBaseDO : accountBaseDOs) {
            accountBaseDomains.add(convert2Domain(accountBaseDO));
        }
        return accountBaseDomains;
    }
}
