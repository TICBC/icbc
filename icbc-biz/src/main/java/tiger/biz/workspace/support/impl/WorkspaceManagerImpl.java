/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.biz.workspace.support.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tiger.biz.account.support.AccountManager;
import tiger.biz.system.support.CachingManager;
import tiger.biz.workspace.support.WorkspaceManager;
import tiger.common.dal.enums.RoleEnum;
import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.dal.enums.WorkSpaceTypeEnum;
import tiger.common.dal.redis.RedisComponent;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.BaseResult;
import tiger.core.basic.constants.RedisCachePrefixConstants;
import tiger.core.basic.constants.SystemConstants;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountDomain;
import tiger.core.domain.workspace.AccountWorkspaceDomain;
import tiger.core.domain.workspace.WorkspaceDomain;
import tiger.core.domain.workspace.WorkspaceInvitationDomain;
import tiger.core.domain.workspace.WorkspaceMemberDomain;
import tiger.core.service.account.AccountService;
import tiger.core.service.system.SystemParamService;
import tiger.core.service.workspace.WorkspaceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alfred_yuan
 * @version v 0.1 16:07 alfred_yuan Exp $
 */
@Service
public class WorkspaceManagerImpl implements WorkspaceManager {

    private Logger logger = Logger.getLogger(WorkspaceManagerImpl.class);

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private SystemParamService systemParamService;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private CachingManager cachingManager;



    /**
     * @see WorkspaceManager#create(WorkspaceDomain)
     */
    @Override
    public BaseResult<WorkspaceDomain> create(WorkspaceDomain workspaceDomain) {
        if (logger.isInfoEnabled()) {
            logger.info("开始创建工作空间, 参数为 [" + workspaceDomain + "]");
        }
        //1. 判断当前用户拥有的空间是否达到了系统配置的上限值
        String maximum = systemParamService.getValueByTypeAndKey(SystemParamTypeEnum.WORKSPACE_OWNER_MAXIMUM,
                SystemConstants.DEFAULT);
        if (StringUtil.isBlank(maximum)) {
            throw new TigerException(ErrorCodeEnum.SYSTEM_EXCEPTION, "系统错误，参数未配置["
                    + SystemParamTypeEnum.WORKSPACE_OWNER_MAXIMUM.getCode() + "]");
        }
        Long maxNum = Long.parseLong(maximum);
        Integer currentWorkspaceCount = workspaceService.getWorkspacesByAccountId(
                workspaceDomain.getOwnerId()).size();
        if (maxNum.intValue() <= currentWorkspaceCount) {
            throw new TigerException(ErrorCodeEnum.BIZ_PHASE_ERROR, "你已经无法创建更多的空间");
        }

        //2. 整理数据创建空间
        WorkspaceDomain createWorkspace = workspaceService.create(workspaceDomain);

        if (createWorkspace != null) {
            if (logger.isInfoEnabled()) {
                logger.info("成功创建工作空间, 结果为 [" + createWorkspace + "]");
            }
            //移除用户权限
            removeUserCachingPermissions(workspaceDomain.getOwnerId());
        } else {
            throw new TigerException(ErrorCodeEnum.BIZ_FAIL, "创建工作团队失败");
        }

        return new BaseResult<>(createWorkspace);
    }

    /**
     * @see WorkspaceManager#update(WorkspaceDomain)
     */
    @Override
    public BaseResult<WorkspaceDomain> update(WorkspaceDomain workspaceDomain) {
        if (logger.isInfoEnabled()) {
            logger.info("开始更新工作空间, 参数为 [" + workspaceDomain + "]");
        }

        Boolean createWorkspace = workspaceService.update(workspaceDomain);

        if (createWorkspace) {
            if (logger.isInfoEnabled()) {
                logger.info("成功更新工作空间");
            }
        } else {
            logger.error("更新工作空间失败, 参数为: [" + workspaceDomain + "]");
        }

        return new BaseResult<>(workspaceService.read(workspaceDomain.getId()));
    }

    /**
     * @see WorkspaceManager#delete(Long)
     */
    @Override
    @Transactional
    public BaseResult<Boolean> delete(Long workspaceId) {
        checkAndGetGroupWorkspace(workspaceId);

        if (workspaceService.delete(workspaceId)) {
            return new BaseResult<>(true);
        }

        throw new TigerException(ErrorCodeEnum.BIZ_FAIL, "服务器开小差了, 删除团队失败!");
    }

    /**
     * 检查 workspaceId 是否为 团队工作空间
     *
     * @param workspaceId
     */
    public WorkspaceDomain checkAndGetGroupWorkspace(Long workspaceId) {
        WorkspaceDomain workspaceDomain = checkAndGetWorkspace(workspaceId);

        if (workspaceDomain == null) {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND);
        }

        if (workspaceDomain.getType().equals(WorkSpaceTypeEnum.PERSONAL)) {
            throw new TigerException(ErrorCodeEnum.BIZ_UNSUPPORTED, "无法对个人工作空间进行该操作");
        }

        return workspaceDomain;
    }

    /**
     * 检查 workspaceId 是否存在
     *
     * @param workspaceId
     * @return
     */
    public WorkspaceDomain checkAndGetWorkspace(Long workspaceId) {
        WorkspaceDomain workspaceDomain = workspaceService.read(workspaceId);

        if (workspaceDomain == null) {
            logger.error("工作空间 [" + workspaceId + "] 不存在");
            throw new TigerException(ErrorCodeEnum.NOT_FOUND);
        }

        return workspaceDomain;
    }

    /**
     * @param accountWorkspaceDomain
     * @return
     */
    @Override
    public BaseResult<Boolean> updateMemberRole(AccountWorkspaceDomain accountWorkspaceDomain) {
        List<RoleEnum> roles = accountWorkspaceDomain.getRoles();
        Long workspaceId = accountWorkspaceDomain.getWorkspaceId();
        Long accountId = accountWorkspaceDomain.getAccountId();

        // 参数检验
        checkAndGetGroupWorkspace(workspaceId);
        accountManager.checkAndGetAccount(accountId);

        if (!workspaceService.isMember(workspaceId, accountId)) {
            logger.error(" 账户 [" + accountId + "] 不是团队工作空间 [" + workspaceId + "] 中的成员, 无需删除角色");
            throw new TigerException(ErrorCodeEnum.BIZ_DATA_NOT_FOUND, "该成员不存在,无法更改其角色");
        }

        // 检查输入角色是否合法
        if (roles.contains(RoleEnum.OWNER)) {
            throw new TigerException(ErrorCodeEnum.BIZ_FAIL, "不能设置该成员为所有者角色,如需更改请在团队设置中进行转让.");
        }

        // 添加新的角色到对应的accountWorkspaceDomain
        Boolean result = workspaceService.changeUserRole(accountWorkspaceDomain);
        if (result) {
            removeUserCachingPermissions(accountWorkspaceDomain.getAccountId());
        }
        return new BaseResult<>(result);
    }

    /**
     * @see WorkspaceManager#getWorkspaceMembers(Long)
     */
    @Override
    public BaseResult<List<WorkspaceMemberDomain>> getWorkspaceMembers(Long workspaceId) {
        List<WorkspaceMemberDomain> members = workspaceService.getAllWorkspaceMember(workspaceId);

        List<AccountDomain> accountDomainList = new ArrayList<>(members.size());
        members.forEach(member -> accountDomainList.add(member.getAccount()));

        return new BaseResult<>(members);
    }

    /**
     * @see WorkspaceManager#addGroupWorkspaceMember(Long, Long)
     */
    @Override
    public BaseResult<Boolean> addGroupWorkspaceMember(Long workspaceId, Long accountId) {
        // 参数检查
        checkAndGetGroupWorkspace(workspaceId);
        accountManager.checkAndGetAccount(accountId);

        if (workspaceService.isMember(workspaceId, accountId)) {
            logger.error(" 账户 [" + accountId + "] 已经是团队工作空间 [" + workspaceId + "] 中的成员, 无需添加");
            throw new TigerException(ErrorCodeEnum.BIZ_DUPLICATIVE, "该用户已经是团队成员,无需添加.");
        }

        // 设置新的成员默认状态
        AccountWorkspaceDomain accountWorkspaceDomain = new AccountWorkspaceDomain(workspaceId, accountId);
        List<RoleEnum> defaultRole = new ArrayList<>();
        defaultRole.add(RoleEnum.CUSTOMER);
        accountWorkspaceDomain.setRoles(defaultRole);

        Boolean result = workspaceService.addUser(accountWorkspaceDomain);
        if (result) {
            removeUserCachingPermissions(accountId);
        }
        return new BaseResult<>(result);
    }

    /**
     * @see WorkspaceManager#deleteGroupWorkspaceMember(Long, Long)
     */
    @Override
    public BaseResult<Boolean> deleteGroupWorkspaceMember(Long workspaceId, Long accountId) {
        // 参数检查
        checkAndGetGroupWorkspace(workspaceId);
        accountManager.checkAndGetAccount(accountId);

        if (!workspaceService.isMember(workspaceId, accountId)) {
            logger.error(" 账户 [" + accountId + "] 不是团队工作空间 [" + workspaceId + "] 中的成员, 无需删除");
            throw new TigerException(ErrorCodeEnum.BIZ_DATA_NOT_FOUND, "该成员不是团队成员,无需下删除.");
        }
        Boolean result = workspaceService.removeUser(workspaceId, accountId);
        if (result) {
            removeUserCachingPermissions(accountId);
        }
        return new BaseResult<>(result);
    }

    /**
     * @see WorkspaceManager#transferGroupWorkspace(Long, Long, Long)
     */
    @Override
    public BaseResult<Boolean> transferGroupWorkspace(Long workspaceId, Long ownerId, Long accountId) {
        // 参数检验
        checkAndGetGroupWorkspace(workspaceId);
        accountManager.checkAndGetAccount(ownerId);
        accountManager.checkAndGetAccount(accountId);


        if (!workspaceService.isOwner(workspaceId, ownerId)) {
            logger.error("团队工作空间 [" + workspaceId + "] 的非所有者成员 [" + ownerId + "] 试图转让工作空间给 [" + accountId + "]");
            throw new TigerException(ErrorCodeEnum.UNAUTHORIZED, "只有团队工作空间的所有者才能转让团队");
        }
        if (!workspaceService.isMember(workspaceId, accountId)) {
            logger.error("团队工作空间 [" + workspaceId + "] 的所有者 [" + ownerId + "] 试图转让工作空间给非团队成员 [" + accountId + "]");
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE, "只能转让团队给团队成员");
        }

        Boolean result = workspaceService.transferWorkspace(workspaceId, ownerId, accountId);

        //如果转换成功，清除用户缓存中的带权限数据
        if (result) {
            removeUserCachingPermissions(ownerId);
            removeUserCachingPermissions(accountId);
        }
        return new BaseResult<>(result);
    }

    /**
     * @see WorkspaceManager#getUserWorkspaces(Long)
     */
    @Override
    public List<WorkspaceDomain> getUserWorkspaces(Long accountId) {
        List<WorkspaceDomain> workspaceDomains = workspaceService.getWorkspacesByAccountId(accountId);
        if (CollectionUtils.isEmpty(workspaceDomains)) {
            return workspaceDomains;
        }
        for (WorkspaceDomain workspace : workspaceDomains) {
            if (workspace.getType() == WorkSpaceTypeEnum.PERSONAL) {
                workspace.setIsDefault(true);
                break;
            }
        }
        return workspaceDomains;
    }

    /**
     * @see WorkspaceManager#updateLoanSetting(WorkspaceDomain)
     */
    @Override
    public Boolean updateLoanSetting(WorkspaceDomain workspaceDomain) {
        if (workspaceDomain.getId() == null) {
            return false;
        }

        WorkspaceDomain oldWorkspace = workspaceService.read(workspaceDomain.getId());
        Map<String, String> oldExtParams = oldWorkspace.getExtParams();

        if (null == oldExtParams) {
            oldExtParams = new HashMap<>();
        }
        oldExtParams.putAll(workspaceDomain.getExtParams());

        WorkspaceDomain newWorkSpace = new WorkspaceDomain();
        newWorkSpace.setId(workspaceDomain.getId());
        newWorkSpace.setExtParams(oldExtParams);

        return workspaceService.update(newWorkSpace);
    }

    /**
     * @see WorkspaceManager#getUserAuthInWorkspace(Long, Long)
     */
    @Override
    public AccountWorkspaceDomain getUserAuthInWorkspace(Long accountId, Long workspaceId) {
        if (workspaceService.isExist(workspaceId)) {
            AccountWorkspaceDomain accountWorkspaceDomain = workspaceService.
                    getUserWorkspace(accountId, workspaceId);
            if (accountWorkspaceDomain == null) {
                throw new TigerException(ErrorCodeEnum.BIZ_STATUS_ERROR, "没有获取任何该用户在团队空间的信息");
            }
            return accountWorkspaceDomain;
        } else {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND, "没有找到制定的空间");
        }
    }

    /**
     * @see WorkspaceManager#inviteUser(Long, Long)
     */
    @Override
    public WorkspaceInvitationDomain inviteUser(Long accountId, Long workspaceId) {
        return workspaceService.generateInvitation(workspaceId, accountId);
    }

    /**
     * @see WorkspaceManager#getInvitationInfo(String)
     */
    @Override
    public WorkspaceInvitationDomain getInvitationInfo(String code) {
        String content = redisComponent.getObject(RedisCachePrefixConstants.concreteKey(
                RedisCachePrefixConstants.WORKSPACE_VERIFY_PREFIX, code));
        if (StringUtil.isBlank(content)) {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND);
        }
        WorkspaceInvitationDomain invitation = JsonUtil.fromJson(content, WorkspaceInvitationDomain.class);
        invitation.setFromAccount(accountService.read(invitation.getFromAccountId()));
        invitation.setWorkspace(workspaceService.read(invitation.getWorkspaceId()));
        return invitation;
    }

    /**
     * @see WorkspaceManager#checkIsWorkspaceMember(Long, Long)
     */
    @Override
    public void checkIsWorkspaceMember(Long accountId, Long workspaceId) {
        if (!workspaceService.isMember(workspaceId, accountId)) {
            throw new TigerException(ErrorCodeEnum.BIZ_UNSUPPORTED, "该账户非本团队成员,不能直行相关操作");
        }
    }

    /**
     * @see WorkspaceManager#getWorkspaceMembers(Long)
     */
    @Override
    public List<Long> getAllWorkspaceMemberIds(Long workspaceId) {
        if (workspaceId == null) {
            return new ArrayList<>();
        }

        return workspaceService.getAllWorkspaceMemberIds(workspaceId);
    }

    /**
     * @see WorkspaceManager#countWorkspaceMembers(Long)
     */
    @Override
    public BaseResult<Integer> countWorkspaceMembers(Long workspaceId) {
        // 如果workspaceId为空,则返回0
        if (workspaceId == null) {
            return new BaseResult<>(0);
        }

        // 准备查询参数
        List<Long> workspaceIds = new ArrayList<>(SystemConstants.SIZE_ONE);
        workspaceIds.add(workspaceId);

        // 获取查询结果
        Map<Long, Integer> countMap = workspaceService.countWorkspaceMember(workspaceIds);

        return new BaseResult<>(countMap.getOrDefault(workspaceId, 0));
    }

    // ~ private methods

    private Map<String, String> generateDefaultLoanSetting() {
        Map<String, String> loanSetting = new HashMap<>();

        loanSetting.put(SystemConstants.OVER_DUE_DAY, SystemConstants.DEFAULT_OVER_DUE_DAY);
        loanSetting.put(SystemConstants.BAD_LOAN_DAY, SystemConstants.DEFAULT_BAD_LOAN_DAY);

        return loanSetting;
    }

    /**
     * 删除用户在缓存中的包含权限的数据
     *
     * @param accountId
     */
    private Boolean removeUserCachingPermissions(Long accountId) {
        return cachingManager.removeAccountCache(accountId);
    }
}
