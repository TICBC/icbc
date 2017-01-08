/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.system;

import tiger.common.dal.enums.InvitationCodeTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.domain.BaseDomain;

import java.util.Date;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午8:08 alfred.yx Exp $
 */
public class InvitationCodeDomain extends BaseDomain {

    private static final long serialVersionUID = -1894043011354093509L;

    private String code;

    @CopyIgnore
    private InvitationCodeTypeEnum type;

    @CopyIgnore
    private boolean isExpired;

    private Long accountId;

    private Long inviterId;

    private Date createTime;

    private Date updateTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InvitationCodeTypeEnum getType() {
        return type;
    }

    public void setType(InvitationCodeTypeEnum type) {
        this.type = type;
    }

    public boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
