/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.workspace.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.workspace.WorkspaceVerifyDO;
import tiger.common.dal.persistence.workspace.example.WorkspaceVerifyExample;
import tiger.common.dal.persistence.mapper.WorkspaceVerifyMapper;
import tiger.common.dal.persistence.workspace.query.WorkspaceVerifyQuery;
import tiger.common.util.Paginator;
import tiger.core.basic.PageResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountDomain;
import tiger.core.domain.workspace.WorkspaceMemberVerifyDomain;
import tiger.core.domain.workspace.convert.WorkspaceMemberVerifyConvert;
import tiger.core.service.account.AccountService;
import tiger.core.service.workspace.WorkspaceInvitationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 团队邀请数据服务
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:00 PM yiliang.gyl Exp $
 */
@Service
public class WorkspaceInvitationServiceImpl implements WorkspaceInvitationService {

    @Autowired
    private WorkspaceVerifyMapper workspaceVerifyMapper;

    @Autowired
    private AccountService accountService;

    /**
     * @see WorkspaceInvitationService#insert(WorkspaceMemberVerifyDomain)
     */
    @Override
    public WorkspaceMemberVerifyDomain insert(WorkspaceMemberVerifyDomain domain) {
        WorkspaceVerifyDO verifyDO = WorkspaceMemberVerifyConvert.convert2DO(domain);
        int rc = workspaceVerifyMapper.insert(verifyDO);
        if (rc > 0) {
            return read(verifyDO.getId());
        } else {
            throw new TigerException(ErrorCodeEnum.DB_EXCEPTION, "后台数据库错误");
        }
    }

    /**
     * 获取一个申请详情
     *
     * @param id
     * @return
     */
    @Override
    public WorkspaceMemberVerifyDomain read(Long id) {
        WorkspaceMemberVerifyDomain domain = WorkspaceMemberVerifyConvert.
                convert2Domain(workspaceVerifyMapper.selectByPrimaryKey(id));
        if (domain.getAccountId() != null) {
            domain.setAccount(accountService.read(domain.getAccountId()));
        }
        return domain;
    }

    /**
     * 删除一个申请
     *
     * @param id
     * @return
     */
    @Override
    public Boolean delete(Long id) {
        return workspaceVerifyMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 判断用户是否存在一个workspace下的申请
     *
     * @param accountId
     * @param workspaceId
     * @return
     */
    @Override
    public Boolean isExist(Long accountId, Long workspaceId) {
        WorkspaceVerifyExample example = new WorkspaceVerifyExample();
        example.createCriteria().andAccountIdEqualTo(accountId).andWorkspaceIdEqualTo(workspaceId);
        return workspaceVerifyMapper.countByExample(example) > 0;
    }

    /**
     * 获取当前工作空间下需要审核的数据
     *
     * @param workspaceId
     * @return
     */
    @Override
    public Integer countWorkspaceVerifies(Long workspaceId) {
        WorkspaceVerifyExample example = new WorkspaceVerifyExample();
        example.createCriteria().andWorkspaceIdEqualTo(workspaceId);
        return workspaceVerifyMapper.countByExample(example);
    }

    /**
     * 获取申请列表
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<List<WorkspaceMemberVerifyDomain>> getByPage(WorkspaceVerifyQuery query) {

        PageResult<List<WorkspaceMemberVerifyDomain>> results = new PageResult<>();

        int totalItems = workspaceVerifyMapper.countVerify(query);

        // 分页器构建
        Paginator paginator = new Paginator();
        paginator.setItems(totalItems);
        paginator.setItemsPerPage(query.getPageSize());
        paginator.setPage(query.getPageNum()); // 目前选择的页码

        List<WorkspaceMemberVerifyDomain> list = WorkspaceMemberVerifyConvert.convert2Domain(
                workspaceVerifyMapper.queryVerify(query, paginator.getOffset(), paginator.getLength()));

        if (CollectionUtils.isNotEmpty(list)) {
            List<Long> accountIds = new ArrayList<>();
            list.stream().forEach(p -> {
                if (!accountIds.contains(p.getAccountId())) {
                    accountIds.add(p.getAccountId());
                }
            });
            List<AccountDomain> accountDomains = accountService.batchRead(accountIds);
            HashMap<Long, AccountDomain> map = new HashMap<>();
            accountDomains.stream().forEach(p -> map.put(p.getId(), p));

            for (WorkspaceMemberVerifyDomain domain : list) {
                if (map.containsKey(domain.getAccountId())) {
                    domain.setAccount(map.get(domain.getAccountId()));
                }
            }
        }
        results.setData(list);
        results.setPaginator(paginator);
        return results;
    }
}
