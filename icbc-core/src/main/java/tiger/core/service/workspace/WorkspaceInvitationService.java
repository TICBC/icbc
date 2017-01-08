/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.workspace;

import tiger.common.dal.persistence.workspace.query.WorkspaceVerifyQuery;
import tiger.core.basic.PageResult;
import tiger.core.domain.workspace.WorkspaceMemberVerifyDomain;

import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 2:59 PM yiliang.gyl Exp $
 */
public interface WorkspaceInvitationService {

    /**
     * 插入一个申请
     *
     * @param domain
     * @return
     */
    WorkspaceMemberVerifyDomain insert(WorkspaceMemberVerifyDomain domain);

    /**
     * 获取一个申请详情
     *
     * @param id
     * @return
     */
    WorkspaceMemberVerifyDomain read(Long id);

    /**
     * 删除一个申请
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 判断用户是否存在一个workspace下的申请
     *
     * @param accountId
     * @param workspaceId
     * @return
     */
    Boolean isExist(Long accountId, Long workspaceId);

    /**
     * 获取当前工作空间下需要审核的数据
     *
     * @param workspaceId
     * @return
     */
    Integer countWorkspaceVerifies(Long workspaceId);

    /**
     * 获取申请列表
     *
     * @param query
     * @return
     */
    PageResult<List<WorkspaceMemberVerifyDomain>> getByPage(WorkspaceVerifyQuery query);
}
