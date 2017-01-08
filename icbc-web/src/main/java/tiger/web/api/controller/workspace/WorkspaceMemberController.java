/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.web.api.controller.workspace;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tiger.biz.workspace.support.WorkspaceInvitationManager;
import tiger.biz.workspace.support.WorkspaceManager;
import tiger.common.dal.annotation.Permission;
import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.RoleEnum;
import tiger.common.dal.persistence.workspace.query.WorkspaceVerifyQuery;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.domain.workspace.AccountWorkspaceDomain;
import tiger.core.domain.workspace.WorkspaceInvitationDomain;
import tiger.core.domain.workspace.WorkspaceMemberVerifyDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;
import tiger.web.api.form.workspace.WorkspaceRoleUpdateForm;
import tiger.web.api.form.workspace.WorkspaceVerifyForm;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 13:40 alfred_yuan Exp $
 */
@RestController
@RequestMapping(APIConstants.BASE_API_URL +"/workspace")
@ResponseBody
public class WorkspaceMemberController extends BaseController {

    private Logger logger = Logger.getLogger(WorkspaceMemberController.class);

    @Autowired
    private WorkspaceManager workspaceManager;

    @Autowired
    private WorkspaceInvitationManager workspaceInvitationManager;

    /**
     * 生成用户邀请链接
     *
     * @return
     */
    @RequestMapping(value = "/member/invitation", method = RequestMethod.GET)
    @Permission(role = {RoleEnum.ADMIN, RoleEnum.OWNER}, permission = {PermissionEnum.UPDATE_WORKSPACE_INVITE_MEMBER})
    public BaseResult<WorkspaceInvitationDomain> generateInvitation() {
        return new BaseResult<>(workspaceManager.inviteUser(currentAccount().getId(), currentWorkspaceId()));
    }

    /**
     * 获取用户邀请具体信息
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/member/invitation/info", method = RequestMethod.GET)
    public BaseResult<WorkspaceInvitationDomain> getInvitationInfo(@RequestParam("code") String code) {
        return new BaseResult<>(workspaceManager.getInvitationInfo(code));
    }


    /**
     * 添加 accountId 加入团队工作空间 workspaceId
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/member/{accountId}", method = RequestMethod.PUT)
    @Permission(role = {RoleEnum.ADMIN, RoleEnum.OWNER}, permission = {PermissionEnum.UPDATE_WORKSPACE_INVITE_MEMBER})
    public BaseResult<Boolean> addWorkspaceMember(@PathVariable("accountId") Long accountId) {
        Long workspaceId = currentWorkspaceId();

        if (logger.isInfoEnabled()) {
            logger.info("用户 [" + currentAccount().getId() + "] 添加 [" + accountId + "] 到团队工作空间 [" + workspaceId + "] 的成员列表中");
        }

        workspaceManager.checkAndGetGroupWorkspace(workspaceId);

        return workspaceManager.addGroupWorkspaceMember(workspaceId, accountId);
    }

    /**
     * 从团队工作空间 workspaceId 中移除 accountId 成员
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/member/{accountId}", method = RequestMethod.DELETE)
    @Permission(role = {RoleEnum.ADMIN, RoleEnum.OWNER}, permission = {PermissionEnum.DELETE_WORKSPACE_MEMBER})
    public BaseResult<Boolean> deleteWorkspaceMember(@PathVariable("accountId") Long accountId) {
        Long workspaceId = currentWorkspaceId();

        if (logger.isInfoEnabled()) {
            logger.info("用户 [" + currentAccount().getId() + "] 删除 [" + accountId + "] 在团队工作空间 [" + workspaceId + "] 中的成员角色");
        }

        workspaceManager.checkAndGetGroupWorkspace(workspaceId);

        return workspaceManager.deleteGroupWorkspaceMember(workspaceId, accountId);
    }

    /**
     * 获取用户在一个空间的角色列表
     *
     * @return
     */
    @RequestMapping(value = "/member/roles", method = RequestMethod.GET)
    @Permission
    public BaseResult<AccountWorkspaceDomain> getUserAuthInWorkspace() {
        try {
            Long workspaceId = currentWorkspaceId();
            AccountWorkspaceDomain result = workspaceManager.getUserAuthInWorkspace(
                    currentAccount().getId(), workspaceId);
            if (result == null) {
                return new BaseResult<>(ErrorCodeEnum.NOT_FOUND.getCode(), "没有找到用户在该团队的角色信息");
            } else {
                return new BaseResult<>(result);
            }
        } catch (Exception e) {
            return new BaseResult<>(ErrorCodeEnum.NOT_FOUND.getCode(), "没有找到用户在该团队的角色信息");
        }
    }


    /**
     * 从团队工作空间 workspaceId 中更新 accountId 的角色
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/member/{accountId}/role", method = RequestMethod.PUT)
    @Permission(role = {RoleEnum.OWNER, RoleEnum.ADMIN}, permission = {PermissionEnum.UPDATE_WORKSPACE_MEMBER_ROLE})
    public BaseResult<Boolean> updateWorkspaceRoles(@PathVariable("accountId") Long accountId,
                                                    @RequestBody @Valid WorkspaceRoleUpdateForm roleUpdateForm,
                                                    BindingResult bindingResult) {
        Long workspaceId = currentWorkspaceId();
        if (logger.isInfoEnabled()) {
            logger.info("用户 [" + currentAccount().getId() + "] 删除 [" + accountId + "] 在团队工作空间 [" + workspaceId + "] 中的成员角色");
        }

        workspaceManager.checkAndGetGroupWorkspace(workspaceId);
        AccountWorkspaceDomain accountWorkspaceDomain = roleUpdateForm.convert2Domain();
        accountWorkspaceDomain.setWorkspaceId(workspaceId);
        accountWorkspaceDomain.setAccountId(accountId);
        return new BaseResult(workspaceManager.updateMemberRole(accountWorkspaceDomain));
    }

    /**
     * 获取工作空间中邀请成员审核信息
     *
     * @return
     */
    @RequestMapping(value = "/member/verifications", method = RequestMethod.GET, params = "scope=count")
    @Permission(role = {RoleEnum.OWNER}, permission = {PermissionEnum.UPDATE_WORKSPACE_INVITE_MEMBER})
    public BaseResult<Integer> countVerificationsOfWorkspace() {
        return new BaseResult<>(workspaceInvitationManager.countVerificationsOfWorkspace(currentWorkspaceId()));
    }

    /**
     * 获取工作空间中成员加入审核列表
     *
     * @return
     */
    @RequestMapping(value = "/member/verifications", method = RequestMethod.GET)
    @Permission(role = {RoleEnum.OWNER}, permission = {PermissionEnum.UPDATE_WORKSPACE_INVITE_MEMBER})
    public PageResult<List<WorkspaceMemberVerifyDomain>> getVerificationsOfWorkspace(@Valid WorkspaceVerifyQuery query,
                                                                                     BindingResult bindingResult) {
        query.setWorkspaceId(currentWorkspaceId());
        return workspaceInvitationManager.getWorkspaceVerifications(query);
    }

    /**
     * 审核邀请成员添加信息
     *
     * @return
     */
    @RequestMapping(value = "/member/verification", method = RequestMethod.PUT)
    @Permission(role = {RoleEnum.OWNER}, permission = {PermissionEnum.UPDATE_WORKSPACE_INVITE_MEMBER})
    public BaseResult<Boolean> countVerificationOfWorkspace(@RequestBody @Valid WorkspaceVerifyForm verifyForm,
                                                            BindingResult bindingResult) {
        return workspaceInvitationManager.verifyMemberInvitation(verifyForm.getVerifyId(), verifyForm.getIsPermit());
    }


}
