/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.workspace;

import tiger.common.dal.enums.ActivityOperationTypeEnum;
import tiger.common.dal.enums.BizObjectTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.domain.BaseDomain;

import java.util.Date;

/**
 * 用户操作类领域模型
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:37 AM yiliang.gyl Exp $
 */
public class ActivityDomain extends BaseDomain {

    /**
     * operatorId 在工作空间 workspaceId 中进行了operation, 对象是objectType的objectId
     *
     * @param operatorId
     * @param workspaceId
     * @param operation
     * @param objectType
     * @param objectId
     */
    public ActivityDomain(
            Long operatorId,
            Long workspaceId,
            ActivityOperationTypeEnum operation,
            BizObjectTypeEnum objectType,
            Long objectId,
            String objectName,
            String operatorName,
            Long operatorAvatarId) {
        this.operatorId = operatorId;
        this.operation = operation;
        this.objectId = objectId;
        this.objectType = objectType;
        this.workspaceId = workspaceId;
        this.objectName = objectName;
        this.operatorName = operatorName;
        this.operatorAvatarId = operatorAvatarId;
    }

    public ActivityDomain() {
    }

    /**
     * 操作者id
     **/
    private Long operatorId;

    /**
     * 时间
     */
    private Date createTime;

    /**
     * 操作类型
     */
    @CopyIgnore
    private ActivityOperationTypeEnum operation;

    /**
     * 操作类型参数
     */
    @CopyIgnore
    private String[] operationParams;

    /**
     * 操作类型和操作类型参数拼接而成的message，如"收了5笔款"
     */
    @CopyIgnore
    private String operationMessage;

    /**
     * 备操作对象
     */
    @CopyIgnore
    private BizObjectTypeEnum objectType;

    /**
     * 备操作对象id
     */
    private Long objectId;

    /**
     * 备操作对象名称
     */
    private String objectName;

    /**
     * workspace Id
     */
    private Long workspaceId;

    /**
     * 操作者用户名
     */
    private String operatorName;

    /**
     * 操作者头像附件id
     */
    private Long operatorAvatarId;

    /**
     * 操作者头像附件url
     */
    private String operatorAvatarUrl;

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ActivityOperationTypeEnum getOperation() {
        return operation;
    }

    public void setOperation(ActivityOperationTypeEnum operation) {
        this.operation = operation;
    }

    public String[] getOperationParams() {
        return operationParams;
    }

    public void setOperationParams(String[] operationParams) {
        this.operationParams = operationParams;
    }

    public String getOperationMessage() {
        return operationMessage;
    }

    public void setOperationMessage(String operationMessage) {
        this.operationMessage = operationMessage;
    }

    public BizObjectTypeEnum getObjectType() {
        return objectType;
    }

    public void setObjectType(BizObjectTypeEnum objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getOperatorAvatarId() {
        return operatorAvatarId;
    }

    public void setOperatorAvatarId(Long operatorAvatarId) {
        this.operatorAvatarId = operatorAvatarId;
    }

    public String getOperatorAvatarUrl() {
        return operatorAvatarUrl;
    }

    public void setOperatorAvatarUrl(String operatorAvatarUrl) {
        this.operatorAvatarUrl = operatorAvatarUrl;
    }
}
