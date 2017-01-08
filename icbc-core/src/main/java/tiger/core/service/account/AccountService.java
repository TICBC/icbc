/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.account;

import tiger.common.dal.persistence.account.query.AccountQuery;
import tiger.core.basic.PageResult;
import tiger.core.domain.account.AccountBaseDomain;
import tiger.core.domain.account.AccountDomain;
import tiger.core.domain.account.AccountResetPwdDomain;

import java.util.HashMap;
import java.util.List;

/**
 * 账户服务类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:07 PM yiliang.gyl Exp $
 */
public interface AccountService {

    /**
     * 通过id获取用户
     *
     * @param accountId
     * @return
     */
    AccountDomain read(Long accountId);

    /**
     * 获取带权限的用户模型
     * @param accountId
     * @return
     */
    AccountDomain readWithPermissions(Long accountId);

    /**
     * 删除一个账户
     *
     * @param accountId
     * @return
     */
    boolean delete(Long accountId);


    /**
     * 批量获取用户
     *
     * @param accountIds
     * @return
     */
    List<AccountDomain> batchRead(List<Long> accountIds);

    /**
     * 通过手机获取用户
     *
     * @param mobile
     * @return
     */
    AccountDomain readByMobile(String mobile);

    /**
     * 删除用户登录token
     *
     * @param accountId
     * @param loginToken
     * @return
     */
    Boolean deleteLoginToken(Long accountId, String loginToken);

    /**
     * @param accountId
     * @param newPassword
     * @return
     */
    Boolean resetPasswordById(Long accountId, String newPassword);

    /**
     * 更新用户信息
     *
     * @param accountDomain
     * @return
     */
    boolean updateAccount(AccountDomain accountDomain);

    /**
     * 更新用户头像关联
     *
     * @param accountId
     * @param attachId
     * @return
     */
    int updateAccountHeaderIcon(Long accountId, Long attachId);


    /**
     *
     * @param accountId
     * @param mobile
     * @return
     */
    Boolean updateAccountMobile(Long accountId, String mobile);

    /**
     * 新增用户
     *
     * @param domain
     * @return
     */
    AccountDomain addAccount(AccountDomain domain);

    /**
     * 分页查询用户列表
     *
     * @param query
     * @return
     */
    PageResult<List<AccountDomain>> query(AccountQuery query);

    /**
     * 根据query条件获取用户列表
     * @param query
     * @return
     */
    List<AccountDomain> queryAll(AccountQuery query);

    /**
     * 验证手机号码是否存在
     *
     * @param phoneNum
     * @return
     */
    boolean isMobileExist(String phoneNum);

    /**
     * 根据手机号修改用户密码
     *
     * @param resetPassDomain
     * @return
     */
    boolean resetPasswordByMobile(AccountResetPwdDomain resetPassDomain);

    /**
     * 根据老密码修改用户密码
     *
     * @param resetPassDomain
     * @return
     */
    boolean resetPasswordByOldPassword(AccountResetPwdDomain resetPassDomain);

    /**
     * 根据 accountId 获取帐户额外设置
     * 现包括：逾期天数和坏账天数
     *
     * @param accountId
     * @return
     */
    HashMap<String, String> getExtParamById(Long accountId);

    /**
     * 根据 accountDomain现有的设置 获取帐户额外设置
     * @param accountDomain
     * @return
     */
    HashMap<String, String> getExtParamByAccount(AccountDomain accountDomain);

    /**
     * 通过id获取用户基本信息
     *
     * @param accountId
     * @return
     */
    AccountBaseDomain getBaseInfo(Long accountId);

    /**
     * 通过id批量获取用户基本信息
     *
     * @param accountIds
     * @return
     */
    List<AccountBaseDomain> getBaseInfos(List<Long> accountIds);

}
