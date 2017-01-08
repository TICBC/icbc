/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.workspace.support;

import tiger.common.dal.persistence.workspace.query.WorkspaceVerifyQuery;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.domain.workspace.WorkspaceMemberVerifyDomain;

import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 2:57 PM yiliang.gyl Exp $
 */
public interface WorkspaceInvitationManager {

    /**
     * 通过邀请码提交加入团队申请
     *
     * @param code
     * @param accountId
     * @return
     */
    Boolean applyJoinWorkSpace(String code , Long accountId);


    /**
     * 通过邀请码加入团队
     *
     * @param code
     * @return
     */
    Boolean addUserVerifyByCode(String code, Long accountId);

    /**
     * 获取需要审核的数目
     *
     * @param workspaceId
     * @return
     */
    Integer countVerificationsOfWorkspace(Long workspaceId);

    /**
     * 分页获取审核列表
     *
     * @param query
     * @return
     */
    PageResult<List<WorkspaceMemberVerifyDomain>> getWorkspaceVerifications(WorkspaceVerifyQuery query);

    /**
     *
     *
     * @param verifyId
     * @param passOrNot
     * @return
     */
    BaseResult<Boolean> verifyMemberInvitation(Long verifyId, Boolean passOrNot);

}
