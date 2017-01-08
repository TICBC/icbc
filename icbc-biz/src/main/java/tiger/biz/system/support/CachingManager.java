/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.system.support;

import tiger.core.domain.workspace.WorkspaceInvitationDomain;

/**
 * Redis 业务相关的服务管理接口
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 1:46 PM yiliang.gyl Exp $
 */
public interface CachingManager {

    /**
     * 移除用户在缓存中的权限Domain
     *
     * @param bizId
     * @return
     */
    Boolean removeAccountCache(Object bizId);


    /**
     * 获取邀请domain
     * @param code
     * @return
     */
    WorkspaceInvitationDomain getInvitationDomain(String code);

    /**
     * 暂时不适用
     * ~ 申请缓存
     *
     * @param workspaceId
     * @param code
     * @param isAdd
     * @return
     */
    Boolean modifyInvitationCodeList(Object workspaceId, String code, Boolean isAdd);
}
