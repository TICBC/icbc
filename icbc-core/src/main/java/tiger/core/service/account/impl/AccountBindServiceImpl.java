/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.account.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.enums.AccountSocialTypeEnum;
import tiger.common.dal.persistence.account.AccountBindDO;
import tiger.common.dal.persistence.account.example.AccountBindExample;
import tiger.common.dal.persistence.mapper.AccountBindMapper;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountBindDomain;
import tiger.core.domain.account.convert.AccountBindConvert;
import tiger.core.service.account.AccountBindService;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号绑定服务
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 6:23 PM yiliang.gyl Exp $
 */
@Service
public class AccountBindServiceImpl implements AccountBindService {

    private static Logger logger = Logger.getLogger(AccountBindServiceImpl.class);


    @Autowired
    AccountBindMapper accountBindMapper;


    /**
     * @see AccountBindService#read(Long)
     */
    @Override
    public AccountBindDomain read(Long id) {
        return AccountBindConvert.convert2Domain(accountBindMapper.selectByPrimaryKey(id));
    }

    /**
     * @see AccountBindService#delete(Long)
     */
    public Boolean delete(Long id) {
        return accountBindMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * @see AccountBindService#changeBindObject(Long, Long)
     */
    @Override
    public Boolean changeBindObject(Long id, Long objectId) {
        AccountBindDomain accountBindDomain = new AccountBindDomain();
        accountBindDomain.setAccountId(objectId);
        accountBindDomain.setId(id);
        return accountBindMapper.updateByPrimaryKeySelective(AccountBindConvert.convert2DO(accountBindDomain)) > 0;
    }

    /**
     * @see AccountBindService#bindSocialAccount(AccountBindDomain)
     */
    @Override
    public AccountBindDomain bindSocialAccount(AccountBindDomain accountBindDomain) {
        if (findAccountByOpenId(accountBindDomain.getOpenId(),
                accountBindDomain.getAccountType()) == null) {
            AccountBindDO bindDO = AccountBindConvert.convert2DO(accountBindDomain);
            int rc = accountBindMapper.insert(bindDO);
            if (rc > 0) {
                return AccountBindConvert.convert2Domain(bindDO);
            } else {
                throw new TigerException(ErrorCodeEnum.DB_EXCEPTION, "数据插入失败");
            }
        } else {
            throw new TigerException(ErrorCodeEnum.BIZ_DUPLICATIVE, "该账号已经绑定");
        }
    }

    /**
     * @see AccountBindService#updateBind(AccountBindDomain)
     */
    @Override
    public Boolean updateBind(AccountBindDomain accountBindDomain) {
        AccountBindDO bindDO = AccountBindConvert.convert2DO(accountBindDomain);
        if (bindDO.getId() == null) {
            return false;
        }
        return accountBindMapper.updateByPrimaryKeySelective(bindDO) > 0;
    }

    /**
     * @see AccountBindService#findAccountByOpenId(String, AccountSocialTypeEnum)
     */
    @Override
    public AccountBindDomain findAccountByOpenId(String openId, AccountSocialTypeEnum type) {
        AccountBindExample example = new AccountBindExample();
        example.createCriteria().andOpenIdEqualTo(openId).andAccountTypeEqualTo(type.getCode());

        List<AccountBindDO> bindDOs = accountBindMapper.selectByExample(example);
        if (bindDOs.size() == 0) {
            return null;
        } else if (bindDOs.size() > 1) {
            logger.error("[AccountBind-Duplicate] 检查到账号重复绑定 OpenId:[" + openId + "], " +
                    "Type : [" + type.getCode() + "]");
            throw new TigerException(ErrorCodeEnum.BIZ_DUPLICATIVE, "检查到该账号重复绑定");
        } else {
            return AccountBindConvert.convert2Domain(bindDOs.get(0));
        }
    }

    /**
     * @see AccountBindService#removeAccountSocialBind(String, AccountSocialTypeEnum)
     */
    @Override
    public Boolean removeAccountSocialBind(String openId, AccountSocialTypeEnum type) {
        AccountBindExample example = new AccountBindExample();
        example.createCriteria().andOpenIdEqualTo(openId).andAccountTypeEqualTo(type.getCode());

        return accountBindMapper.deleteByExample(example) > 0;
    }

    /**
     * @see AccountBindService#readByAccount(Long)
     */
    @Override
    public List<AccountBindDomain> readByAccount(Long accountId) {
        AccountBindExample example = new AccountBindExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        return AccountBindConvert.convert2Domain(accountBindMapper.selectByExample(example));
    }

    /**
     * @see AccountBindService#readByAccountAndType(Long, List<AccountSocialTypeEnum>)
     */
    @Override
    public List<AccountBindDomain> readByAccountAndType(Long accountId, List<AccountSocialTypeEnum> types) {
        AccountBindExample example = new AccountBindExample();
        List<String> typeStrs = new ArrayList<>();
        types.stream().forEach(p -> typeStrs.add(p.getCode()));
        example.createCriteria().andAccountIdEqualTo(accountId).andAccountTypeIn(typeStrs);
        return AccountBindConvert.convert2Domain(accountBindMapper.selectByExample(example));
    }


}
