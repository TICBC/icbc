package tiger.common.dal.persistence.workspace;

import tiger.common.util.annotation.CopyIgnore;

import java.io.Serializable;
import java.util.Date;

public class ActivityDO implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long operatorId;

    @CopyIgnore
    private String operation;

    @CopyIgnore
    private String objectType;

    private Long objectId;

    private Long workspaceId;

    private String extParams;

    private String objectName;

    private String operatorName;

    private Long operatorAvatarId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getExtParams() {
        return extParams;
    }

    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
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
}