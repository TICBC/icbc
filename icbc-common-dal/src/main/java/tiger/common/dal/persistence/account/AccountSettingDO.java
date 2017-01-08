/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.common.dal.persistence.account;

import tiger.common.dal.persistence.BaseDO;
import tiger.common.util.annotation.CopyIgnore;

/**
 * 用户设置表DO
 * @author alfred_yuan
 * @version v 0.1 19:02 alfred_yuan Exp $
 */
public class AccountSettingDO extends BaseDO {

    private static final long serialVersionUID = -7316796243133191697L;

    private Long accountId;

    @CopyIgnore
    private String bizType;

    private Long subjectId;

    @CopyIgnore
    private String settingType;

    private String settingValue;

    private Long workspaceId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
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