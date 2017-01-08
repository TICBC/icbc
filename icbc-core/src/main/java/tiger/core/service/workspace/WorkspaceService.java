/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.workspace;

import tiger.core.basic.PageResult;
import tiger.core.domain.workspace.*;

import java.util.List;
import java.util.Map;

/**
 * 团队空间服务接口
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:52 AM yiliang.gyl Exp $
 */
public interface WorkspaceService {

    /**
     * 新建一个空间
     *
     * @param workspaceDomain
     * @return
     */
    WorkspaceDomain create(WorkspaceDomain workspaceDomain);

    /**
     * 删除一个项目空间
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 更新项目空间
     *
     * @param workspaceDomain
     * @return
     */
    Boolean update(WorkspaceDomain workspaceDomain);

    /**
     * 通过id获取一个项目空间
     *
     * @param id
     * @return
     */
    WorkspaceDomain read(Long id);

    /**
     * 是否是项目所有者
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    Boolean isOwner(Long workspaceId, Long accountId);

    /**
     * 判断项目空间是否
     *
     * @param id
     * @return
     */
    Boolean isExist(Long id);

    /**
     * 获取所有的团队用户列表
     *
     * @param workspaceId
     * @return
     */
    List<WorkspaceMemberDomain> getAllWorkspaceMember(Long workspaceId);

    /**
     * 获取所有的团队用户id列表
     *
     * @param workspaceId
     * @return
     */
    List<Long> getAllWorkspaceMemberIds(Long workspaceId);

    /**
     * 移除用户
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    Boolean removeUser(Long workspaceId, Long accountId);

    /**
     * 添加用户
     *
     * @param accountWorkspace
     * @return
     */
    Boolean addUser(AccountWorkspaceDomain accountWorkspace);

    /**
     * 修改用户角色
     *
     * @param accountWorkspaceDomain
     * @return
     */
    Boolean changeUserRole(AccountWorkspaceDomain accountWorkspaceDomain);

    /**
     * 项目移交接口
     *
     * @param workspaceId
     * @param ownerId
     * @param accountId
     * @return
     */
    Boolean transferWorkspace(Long workspaceId, Long ownerId, Long accountId);

    /**
     * 获取用户空间和权限
     * ~ 该接口只限制单个用户使用，切勿循环调用
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    AccountWorkspaceDomain getUserWorkspace(Long workspaceId, Long accountId);

    /**
     * 获取用户所有的空间和权限
     *
     * @param accountId
     * @return
     */
    List<AccountWorkspaceDomain> getUserWorkspaces(Long accountId);

    /**
     * 获取workspace动态
     *
     * @return
     */
    PageResult<ActivityDomain> getWorkspaceActivities();

    /**
     * 获取accountId的所有用户空间
     * @param accountId
     * @return
     */
    List<WorkspaceDomain> getWorkspacesByAccountId(Long accountId);

    /**
     * 判断accountId是否在团队工作空间workspace中国年
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    Boolean isMember(Long workspaceId, Long accountId);

    /**
     * 移除成员角色
     *
     * @param accountWorkspaceDomain
     * @return
     */
    Boolean removeMemberRoles(AccountWorkspaceDomain accountWorkspaceDomain);

    /**
     * 生成一个邀请链接
     *
     * @param workspaceId
     * @param accountId
     * @return
     */
    WorkspaceInvitationDomain generateInvitation(Long workspaceId, Long accountId);

    /**
     * 根据workspaceId获取额外设置信息
     *
     * @param workspaceId
     * @return
     */
    Map<String, String> getExtParamById(Long workspaceId);

    /**
     * 获取workspaceIds的成员计数
     *
     * @param workspaceIds
     * @return
     */
    Map<Long, Integer> countWorkspaceMember(List<Long> workspaceIds);
}
