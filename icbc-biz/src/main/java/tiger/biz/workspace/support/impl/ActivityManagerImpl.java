/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.biz.workspace.support.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tiger.biz.workspace.support.ActivityManager;
import tiger.common.dal.persistence.system.query.ActivityQuery;
import tiger.core.basic.PageResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.workspace.ActivityDomain;
import tiger.core.service.workspace.ActivityService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alfred_yuan
 * @version v 0.1 15:53 alfred_yuan Exp $
 */
@Service
public class ActivityManagerImpl implements ActivityManager {

    private final Logger logger = Logger.getLogger(ActivityManagerImpl.class);

    @Autowired
    private ActivityService activityService;

    /**
     * @see ActivityManager#create(ActivityDomain)
     */
    @Override
    public ActivityDomain create(ActivityDomain activity) {
        if (logger.isInfoEnabled()) {
            logger.info("创建一条用户操作动态, 参数为 [" + activity + "]");
        }

        return activityService.insert(activity);
    }

    /**
     * @see ActivityManager#createAll(List)
     */
    @Override
    public boolean createAll(List<ActivityDomain> activities) {
        if (logger.isInfoEnabled()) {
            logger.info("创建一条用户操作动态, 参数为 [" + activities + "]");
        }

        return activityService.batchInsert(activities);
    }

    /**
     * @see ActivityManager#listActivities(ActivityQuery)
     */
    @Override
    public PageResult<List<ActivityDomain>> listActivities(ActivityQuery query) {
        if (query == null) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }

        PageResult<List<ActivityDomain>> activityPage = activityService.listActivities(query);

        return activityPage;
    }

}
