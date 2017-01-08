/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace;

import tiger.common.dal.enums.WorkSpaceTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.domain.BaseDomain;
import tiger.core.domain.account.AccountDomain;

import java.util.Map;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:15 AM yiliang.gyl Exp $
 */
public class WorkspaceDomain extends BaseDomain {

    /**
     * 工作空间名字
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 所有者 id
     */
    private Long ownerId;

    /**
     * 所有者
     */
    private AccountDomain owner;

    /**
     * 工作空间类型
     */
    @CopyIgnore
    private WorkSpaceTypeEnum type;

    /**
     * 工作空间配置字段
     */
    @CopyIgnore
    private Map<String, String> extParams;

    /**
     * 是否已经认证
     */
    private Boolean isVerified;

    /**
     * 是否是默认渲染的工作空间
     */
    private Boolean isDefault = false;

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Map<String, String> getExtParams() {
        return extParams;
    }

    public void setExtParams(Map<String, String> extParams) {
        this.extParams = extParams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public AccountDomain getOwner() {
        return owner;
    }

    public void setOwner(AccountDomain owner) {
        this.owner = owner;
    }

    public WorkSpaceTypeEnum getType() {
        return type;
    }

    public void setType(WorkSpaceTypeEnum type) {
        this.type = type;
    }
}
