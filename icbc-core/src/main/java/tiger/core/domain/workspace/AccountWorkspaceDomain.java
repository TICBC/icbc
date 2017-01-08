/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace;

import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.RoleEnum;
import tiger.core.domain.BaseDomain;
import tiger.core.domain.account.AccountDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录后的workspace领域模型
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:34 AM yiliang.gyl Exp $
 */
public class AccountWorkspaceDomain extends BaseDomain {

    public AccountWorkspaceDomain() {

    }

    public AccountWorkspaceDomain(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public AccountWorkspaceDomain(Long workspaceId, Long accountId) {
        this.workspaceId = workspaceId;
        this.accountId = accountId;
    }

    /**
     * 工作空间 id
     */
    private Long workspaceId;

    /**
     * 工作空间信息
     */
    private WorkspaceDomain workspace;

    /**
     * 用户角色
     */
    private List<RoleEnum> roles = new ArrayList<>();

    /**
     * 用户权限
     */
    private List<PermissionEnum> permissions = new ArrayList<>();

    /**
     * 当前用户对象
     */
    private AccountDomain account;

    /**
     * 当前用户id
     */
    private Long accountId;

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public WorkspaceDomain getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceDomain workspace) {
        this.workspace = workspace;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public List<PermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public AccountDomain getAccount() {
        return account;
    }

    public void setAccount(AccountDomain account) {
        this.account = account;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
