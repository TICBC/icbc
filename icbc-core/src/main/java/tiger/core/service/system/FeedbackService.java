/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.system;

import tiger.common.dal.persistence.system.query.FeedbackQuery;
import tiger.core.basic.PageResult;
import tiger.core.domain.system.FeedbackDomain;

import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 16:39 alfred_yuan Exp $
 */
public interface FeedbackService {

    /**
     * 创建一个用户反馈
     * @param feedbackDomain
     * @return
     */
    FeedbackDomain create(FeedbackDomain feedbackDomain);

    /**
     * 根据query条件查询用户反馈列表,默认按照create_time你须
     * @param query
     * @return
     */
    PageResult<List<FeedbackDomain>> query(FeedbackQuery query);
}
