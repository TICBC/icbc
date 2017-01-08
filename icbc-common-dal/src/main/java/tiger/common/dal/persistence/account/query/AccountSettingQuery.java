/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.common.dal.persistence.account.query;

import tiger.common.dal.persistence.BaseQuery;

import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 19:41 alfred_yuan Exp $
 */
public class AccountSettingQuery extends BaseQuery {

    private static final long serialVersionUID = -2571857528116393657L;

    private List<Long> ids;

    private String bizType;

    private List<Long> subjectIds;

    private String settingType;

    private Long workspaceId;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public List<Long> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<Long> subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }
}
