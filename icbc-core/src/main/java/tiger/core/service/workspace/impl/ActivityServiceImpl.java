/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.workspace.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tiger.common.dal.persistence.system.query.ActivityQuery;
import tiger.common.dal.persistence.workspace.ActivityDO;
import tiger.common.dal.persistence.mapper.ActivityMapper;
import tiger.common.util.Paginator;
import tiger.core.basic.PageResult;
import tiger.core.domain.workspace.ActivityDomain;
import tiger.core.domain.workspace.convert.ActivityConvert;
import tiger.core.service.workspace.ActivityService;

import java.util.List;


/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 12:13 PM yiliang.gyl Exp $
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private final Logger logger = Logger.getLogger(ActivityServiceImpl.class);

    @Autowired
    private ActivityMapper activityMapper;


    /**
     * @see ActivityService#insert(ActivityDomain)
     */
    @Override
    public ActivityDomain insert(ActivityDomain activityDomain) {
        ActivityDO activityDO = ActivityConvert.convert2DO(activityDomain);

        if (activityMapper.insert(activityDO) > 0) {
            return read(activityDO.getId());
        }

        logger.error("创建用户动态失败, 参数为 [" + activityDomain + "]");
        return null;
    }

    /**
     * @see ActivityService#batchInsert(List<ActivityDomain>)
     */
    @Override
    public boolean batchInsert(List<ActivityDomain> activityDomains) {
        if (CollectionUtils.isEmpty(activityDomains)) {
            return false;
        }

        List<ActivityDO> activityDOs = ActivityConvert.convert2Dos(activityDomains);

        if (activityDomains.size() != activityMapper.batchInsert(activityDOs)) {
            logger.error("创建的用户动态数量与实际不符合");
        }

        return true;
    }

    /**
     * @see ActivityService#read(Long)
     */
    @Override
    public ActivityDomain read(Long id) {
        ActivityDO activityDO = activityMapper.selectByPrimaryKey(id);
        if (null == activityDO)
            return null;
        ActivityDomain activityDomain = ActivityConvert.convert2Domain(activityDO);
        //activityDomain.setOperator(accountService.read(activityDO.getOperatorId()));
        return activityDomain;
    }

    /**
     * @see ActivityService#delete(Long)
     */
    @Override
    public boolean delete(Long id) {
        return activityMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * @see ActivityService#listActivities(ActivityQuery)
     */
    @Override
    public PageResult<List<ActivityDomain>> listActivities(ActivityQuery query) {
        PageResult<List<ActivityDomain>> results = new PageResult<>();

        int totalItems = activityMapper.countActivities(query);

        // 分页器构建
        Paginator paginator = new Paginator();
        paginator.setItems(totalItems);
        paginator.setItemsPerPage(query.getPageSize());
        paginator.setPage(query.getPageNum()); // 目前选择的页码


        List<ActivityDomain> activityDomains = ActivityConvert.convert2Domain(
                activityMapper.queryActivities(query, paginator.getOffset(), paginator.getLength()));

        //activityDomains = setObjectName(activityDomains);
        results.setData(activityDomains);
        results.setPaginator(paginator);
        return results;
    }

}
