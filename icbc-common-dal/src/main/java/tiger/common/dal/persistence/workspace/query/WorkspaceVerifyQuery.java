/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.persistence.workspace.query;

import tiger.common.dal.persistence.BaseQuery;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:06 PM yiliang.gyl Exp $
 */
public class WorkspaceVerifyQuery extends BaseQuery {

    private Long workspaceId;

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }
}
