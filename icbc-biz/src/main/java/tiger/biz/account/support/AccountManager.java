/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.account.support;


import tiger.core.basic.BaseResult;
import tiger.core.domain.account.AccountDomain;
import tiger.core.domain.account.AccountLoginLogDomain;
import tiger.core.domain.account.AccountSignUpDomain;

import java.util.List;

/**
 * @author zhangbin
 * @version v0.1 2015/9/27 10:08
 */
public interface AccountManager {

    /**
     * 是否已绑定手机号
     *
     * @param accountId
     * @return
     */
    Boolean hasBindMobile(Long accountId);

    /**
     * 用户登录接口
     *
     * @param mobile
     * @param password
     * @return
     */
    AccountDomain login(String mobile, String password);

    /**
     * 新增加一个用户并添加相关的company
     *
     * @param account
     * @return
     */
    AccountDomain signup(AccountSignUpDomain account);

    /**
     * 通过token获取账户信息
     *
     * @param token
     * @return
     */
    AccountDomain loginByToken(String token);

    /**
     * 为登陆用户创建一个expireDay天后过期的token
     */
    String createToken(AccountLoginLogDomain loginLogDomain, int expireDay);

    /**
     * 更新用户,融合ExtParam
     *
     * @param domain
     * @return
     */
    BaseResult<AccountDomain> updateAccountDomain(AccountDomain domain);


    /**
     * 根据ids查询用户信息,包括公司信息和头像
     *
     * @param ids
     * @return
     */
    List<AccountDomain> getAccountsByIds(List<Long> ids);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    AccountDomain readAccount(Long id);

    /**
     * 检查 accountId 是否存在
     *
     * @param accountId
     */
    AccountDomain checkAndGetAccount(Long accountId);

}
