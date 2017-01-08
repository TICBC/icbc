/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace;

import tiger.core.domain.BaseDomain;
import tiger.core.domain.account.AccountDomain;

import java.util.Date;

/**
 * 用户提出的申请
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 2:45 PM yiliang.gyl Exp $
 */
public class WorkspaceMemberVerifyDomain extends BaseDomain{

    private Long workspaceId;

    private WorkspaceDomain workspace;

    private Long accountId;

    private AccountDomain account;

    private Date expiredTime;

    private Boolean isVerified = false;

    public WorkspaceDomain getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceDomain workspace) {
        this.workspace = workspace;
    }

    public AccountDomain getAccount() {
        return account;
    }

    public void setAccount(AccountDomain account) {
        this.account = account;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
