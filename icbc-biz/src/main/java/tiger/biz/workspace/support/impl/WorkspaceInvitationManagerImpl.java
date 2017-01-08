/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.workspace.support.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.system.support.CachingManager;
import tiger.biz.workspace.support.WorkspaceInvitationManager;
import tiger.biz.workspace.support.WorkspaceManager;
import tiger.common.dal.persistence.workspace.query.WorkspaceVerifyQuery;
import tiger.common.util.DateUtil;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.workspace.WorkspaceInvitationDomain;
import tiger.core.domain.workspace.WorkspaceMemberVerifyDomain;
import tiger.core.service.workspace.WorkspaceInvitationService;
import tiger.core.service.workspace.WorkspaceService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 2:58 PM yiliang.gyl Exp $
 */
@Service
public class WorkspaceInvitationManagerImpl implements WorkspaceInvitationManager {

    private static Logger logger = Logger.getLogger(WorkspaceInvitationManagerImpl.class);

    @Autowired
    WorkspaceInvitationService workspaceInvitationService;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    WorkspaceManager workspaceManager;

    @Autowired
    CachingManager cachingManager;

    /**
     * @see WorkspaceInvitationManager#applyJoinWorkSpace(String, Long)
     */
    @Override
    public Boolean applyJoinWorkSpace(String code, Long accountId) {
        WorkspaceInvitationDomain invitationDomain = cachingManager.getInvitationDomain(code);

        if (invitationDomain == null) {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND, "邀请码失效");
        }
        //1. 判断用户是否在团队中
        if (workspaceService.isMember(invitationDomain.getWorkspaceId(), accountId)) {
            logger.error(" 账户 [" + accountId + "] 已经是团队工作空间 [" + invitationDomain.getWorkspaceId() + "] 中的成员, 无需申请加入");
            throw new TigerException(ErrorCodeEnum.BIZ_DUPLICATIVE, "该用户已经是团队成员,无需添加.");
        }
        //2. 判断用户是否已经申请过
        if(workspaceInvitationService.isExist(accountId, invitationDomain.getWorkspaceId())){
            logger.error(" 账户 [" + accountId + "] 已经申请加入工作空间 [" + invitationDomain.getWorkspaceId() + "] 拉");
            throw new TigerException(ErrorCodeEnum.BIZ_DUPLICATIVE, "该用户申请加入该团队了,无需重复申请.");
        }
        //3. 提出申请
        WorkspaceMemberVerifyDomain verifyDomain = new WorkspaceMemberVerifyDomain();
        verifyDomain.setWorkspaceId(invitationDomain.getWorkspaceId());
        verifyDomain.setAccountId(accountId);
        verifyDomain.setIsVerified(false);
        verifyDomain.setExpiredTime(DateUtil.addHours(new Date(), 24));
        verifyDomain = workspaceInvitationService.insert(verifyDomain);
        if(verifyDomain != null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @see WorkspaceInvitationManager#addUserVerifyByCode(String, Long)
     */
    public Boolean addUserVerifyByCode(String code, Long accountId) {
        WorkspaceInvitationDomain invitationDomain = cachingManager.getInvitationDomain(code);

        if (invitationDomain == null) {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND, "邀请码失效");
        }
        //TODO:设置失效时间
        return workspaceManager.addGroupWorkspaceMember(
                invitationDomain.getWorkspaceId(), accountId).getData();
    }

    /**
     * @see WorkspaceInvitationManager#countVerificationsOfWorkspace(Long)
     */
    @Override
    public Integer countVerificationsOfWorkspace(Long workspaceId) {
        return workspaceInvitationService.countWorkspaceVerifies(workspaceId);
    }

    /**
     * @see WorkspaceInvitationManager#getWorkspaceVerifications(WorkspaceVerifyQuery)
     */
    @Override
    public PageResult<List<WorkspaceMemberVerifyDomain>> getWorkspaceVerifications(WorkspaceVerifyQuery query) {
        return workspaceInvitationService.getByPage(query);
    }

    /**
     * @see WorkspaceInvitationManager#verifyMemberInvitation(Long, Boolean)
     */
    @Override
    @Transactional
    public BaseResult<Boolean> verifyMemberInvitation(Long verifyId, Boolean passOrNot) {
        if (passOrNot) {
            WorkspaceMemberVerifyDomain domain = workspaceInvitationService.read(verifyId);
            if (domain == null) {
                throw new TigerException(ErrorCodeEnum.NOT_FOUND, "审核对象不存在");
            }
            //把用户加入到团队里
            Boolean hasInvited = workspaceManager.addGroupWorkspaceMember(
                    domain.getWorkspaceId(), domain.getAccountId()).getData();
            if (!hasInvited) {
                throw new TigerException(ErrorCodeEnum.BIZ_FAIL, "审核失败，服务器错误,请稍后提交!");
            }
        }
        return new BaseResult<>(workspaceInvitationService.delete(verifyId));
    }


}
