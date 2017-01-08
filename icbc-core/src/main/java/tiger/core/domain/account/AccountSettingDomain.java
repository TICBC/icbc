/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.core.domain.account;

import tiger.common.dal.enums.AccountSettingBizTypeEnum;
import tiger.common.dal.enums.AccountSettingTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.domain.BaseDomain;

/**
 * 用户设置领域模型
 * @author alfred_yuan
 * @version v 0.1 19:02 alfred_yuan Exp $
 */
public class AccountSettingDomain extends BaseDomain {

    private static final long serialVersionUID = 6514536393067984988L;

    private Long accountId;

    @CopyIgnore
    private AccountSettingBizTypeEnum bizType;

    private Long subjectId;

    @CopyIgnore
    private AccountSettingTypeEnum settingType;

    private String settingValue;

    private Long workspaceId;

    public AccountSettingDomain() {}

    public AccountSettingDomain(Long accountId, Long workspaceId, AccountSettingBizTypeEnum bizType, Long subjectId, AccountSettingTypeEnum settingType, String settingValue) {
        this.accountId = accountId;
        this.workspaceId = workspaceId;
        this.bizType = bizType;
        this.subjectId = subjectId;
        this.settingType = settingType;
        this.settingValue = settingValue;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public AccountSettingBizTypeEnum getBizType() {
        return bizType;
    }

    public void setBizType(AccountSettingBizTypeEnum bizType) {
        this.bizType = bizType;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public AccountSettingTypeEnum getSettingType() {
        return settingType;
    }

    public void setSettingType(AccountSettingTypeEnum settingType) {
        this.settingType = settingType;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }
}
