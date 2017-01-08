/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace;

import tiger.common.util.DateUtil;
import tiger.core.domain.BaseDomain;
import tiger.core.domain.account.AccountDomain;

import java.util.Date;
import java.util.UUID;

/**
 * 团队公开邀请链接数据类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 6:33 PM yiliang.gyl Exp $
 */
public class WorkspaceInvitationDomain extends BaseDomain {

    private Long fromAccountId;

    private AccountDomain fromAccount;

    private String url;

    private String key;

    private Long workspaceId;

    private WorkspaceDomain workspace;

    private Date expiredTime;

    public WorkspaceInvitationDomain(){
    }

    public WorkspaceInvitationDomain(Long accountId, Long workspaceId) {
        this.fromAccountId = accountId;
        this.workspaceId = workspaceId;
        this.expiredTime = DateUtil.addHours(new Date(), 24);
        this.key = UUID.randomUUID().toString();
    }

    public AccountDomain getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(AccountDomain fromAccount) {
        this.fromAccount = fromAccount;
    }

    public WorkspaceDomain getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceDomain workspace) {
        this.workspace = workspace;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
