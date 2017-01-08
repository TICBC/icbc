/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.biz.workspace.support;

import tiger.common.dal.persistence.system.query.ActivityQuery;
import tiger.core.basic.PageResult;
import tiger.core.domain.workspace.ActivityDomain;

import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 15:51 alfred_yuan Exp $
 */
public interface ActivityManager {

    /**
     * 创建一条动态消息
     *
     * @param activity
     */
    ActivityDomain create(ActivityDomain activity);

    /**
     * 一次性创建多条用户动态
     * @param activities
     * @return
     */
    boolean createAll(List<ActivityDomain> activities);

    /**
     * 根据query分页获取所有用户动态
     *
     * @return
     */
    PageResult<List<ActivityDomain>> listActivities(ActivityQuery query);

}
