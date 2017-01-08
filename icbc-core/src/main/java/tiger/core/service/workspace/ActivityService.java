/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.workspace;

import tiger.common.dal.persistence.system.query.ActivityQuery;
import tiger.core.basic.PageResult;
import tiger.core.domain.workspace.ActivityDomain;

import java.util.List;


/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 12:12 PM yiliang.gyl Exp $
 */
public interface ActivityService {

    /**
     * 插入用户动态
     *
     * @param activityDomain
     * @return
     */
    ActivityDomain insert(ActivityDomain activityDomain);

    /**
     * 一次创建多条用户动态
     * @param activityDomains
     * @return
     */
    boolean batchInsert(List<ActivityDomain> activityDomains);

    /**
     * 获取用户动态
     *
     * @param id
     * @return
     */
    ActivityDomain read(Long id);

    /**
     * 删除用户动态
     *
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据query分页获取所有用户动态
     *
     * @return
     */
    PageResult<List<ActivityDomain>> listActivities(ActivityQuery query);

}
