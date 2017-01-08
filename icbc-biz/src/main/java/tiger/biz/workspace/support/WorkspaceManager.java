/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.biz.workspace.support;

import tiger.core.basic.BaseResult;
import tiger.core.domain.workspace.AccountWorkspaceDomain;
import tiger.core.domain.workspace.WorkspaceDomain;
import tiger.core.domain.workspace.WorkspaceInvitationDomain;
import tiger.core.domain.workspace.WorkspaceMemberDomain;

import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 16:01 alfred_yuan Exp $
 */
public interface WorkspaceManager {

    /**
     * 创建工作空间
     *
     * @param workspaceDomain
     * @return
     */
    BaseResult<WorkspaceDomain> create(WorkspaceDomain workspaceDomain);

    /**
     * 创建工作空间
     *
     * @param workspaceDomain
     * @return
     */
    BaseResult<WorkspaceDomain> update(WorkspaceDomain workspaceDomain);

    /**
     * 删除一个团队工作空间
     *
     * @param id
     * @return
     */
    BaseResult<Boolean> delete(Long id);

    /**
     * 检查 workspaceId 是否为 团队工作空间
     *
     * @param workspaceId
     */
    WorkspaceDomain checkAndGetGroupWorkspace(Long workspaceId);

    /**
     * 检查 workspaceId 是否存在
     *
     * @param workspaceId
     * @return
     */
    WorkspaceDomain checkAndGetWorkspace(Long workspaceId);


    /*
     * 检查赋予accountId的权限是否合法
     *
     * @param AccountWorkspaceDomain
     * @return
     */
    BaseResult<Boolean> updateMemberRole(AccountWorkspaceDomain accountWorkspaceDomain);

    /**
     * 更新项目空间贷款设置
     *
     * @param workspaceDomain
     * @return
     */
    Boolean updateLoanSetting(WorkspaceDomain workspaceDomain);

    /**
     * 获取workspaceId下的成员列表
     *
     * @param workspaceId
     * @return
     */
    BaseResult<List<WorkspaceMemberDomain>> getWorkspaceMembers(Long workspaceId);

    /**
     * 增加团队工作空间成员成员
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    BaseResult<Boolean> addGroupWorkspaceMember(Long workspaceId, Long accountId);

    /**
     * 删除团队工作空间成员
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    BaseResult<Boolean> deleteGroupWorkspaceMember(Long workspaceId, Long accountId);

    /**
     * 将团队工作空间从ownerId转移到accountId
     *
     * @param workspaceId
     * @param ownerId
     * @param accountId
     * @return
     */
    BaseResult<Boolean> transferGroupWorkspace(Long workspaceId, Long ownerId, Long accountId);

    /**
     * 获取用户所有的工作空间
     *
     * @param accountId
     * @return
     */
    List<WorkspaceDomain> getUserWorkspaces(Long accountId);

    /**
     * 获取用户在空间的权限和角色
     *
     * @param accountId
     * @param workspaceId
     * @return
     */
    AccountWorkspaceDomain getUserAuthInWorkspace(Long accountId, Long workspaceId);

    /**
     * 生成邀请链接
     *
     * @param accountId
     * @param workspaceId
     * @return
     */
    WorkspaceInvitationDomain inviteUser(Long accountId, Long workspaceId);

    /**
     * 获取邀请的信息
     *
     * @param code
     * @return
     */
    WorkspaceInvitationDomain getInvitationInfo(String code);

    /**
     * 判断用户ID是否是当前团队成员
     *
     * @param accountId
     * @param workspaceId
     */
    void checkIsWorkspaceMember(Long accountId, Long workspaceId);

    /**
     * 获取workspaceId下的所有成员id
     *
     * @param workspaceId
     * @return
     */
    List<Long> getAllWorkspaceMemberIds(Long workspaceId);

    /**
     * 获取workspcaeId工作空间下成员数量
     *
     * @param workspaceId
     * @return
     */
    BaseResult<Integer> countWorkspaceMembers(Long workspaceId);
}
