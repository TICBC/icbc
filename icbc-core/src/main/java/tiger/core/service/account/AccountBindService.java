/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.account;

import tiger.common.dal.enums.AccountSocialTypeEnum;
import tiger.core.domain.account.AccountBindDomain;

import java.util.List;

/**
 * 社交账号服务接口
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 6:07 PM yiliang.gyl Exp $
 */
public interface AccountBindService {

    /**
     * 通过 id 获取绑定对象
     *
     * @param id
     * @return
     */
    AccountBindDomain read(Long id);

    /**
     * 删除社交绑定
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 修改绑定主体
     */
    Boolean changeBindObject(Long id, Long objectId);

    /**
     * 绑定一个社交账号
     *
     * @param accountBindDomain
     * @return
     */
    AccountBindDomain bindSocialAccount(AccountBindDomain accountBindDomain);

    /**
     * 更新绑定信息
     *
     * @param accountBindDomain
     * @return
     */
    Boolean updateBind(AccountBindDomain accountBindDomain);

    /**
     * 根据openId和绑定账号类型查询绑定信息
     *
     * @param openId
     * @param type
     * @return
     */
    AccountBindDomain findAccountByOpenId(String openId, AccountSocialTypeEnum type);


    /**
     * 解除第三方账号绑定
     *
     * @param openId
     * @param type
     * @return
     */
    Boolean removeAccountSocialBind(String openId, AccountSocialTypeEnum type);

    /**
     * 获取一个用户的所有绑定列表
     *
     * @param accountId
     * @return
     */
    List<AccountBindDomain> readByAccount(Long accountId);

    /**
     * 获取一个用户的对应绑定
     *
     * @param accountId
     * @param types
     * @return
     */
    List<AccountBindDomain> readByAccountAndType(Long accountId, List<AccountSocialTypeEnum> types);
}
