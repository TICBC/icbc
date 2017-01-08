/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.persistence.system.query;

import tiger.common.dal.persistence.BaseQuery;

/**
 * @author mi.li
 * @version v 0.1 16/3/16 下午6:50 mi.li Exp $
 */
public class ActivityQuery extends BaseQuery {

    private static final long serialVersionUID = 1646951617172020800L;

    /**
     * 贷款项目编号
     */
    private Long loanId;

    /**
     * workspace编号
     */
    private Long workspaceId;

    /**
     * 操作者id
     */
    private Long operatorId;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
