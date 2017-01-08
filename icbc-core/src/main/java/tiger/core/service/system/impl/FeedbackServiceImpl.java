/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.system.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.system.FeedbackDO;
import tiger.common.dal.persistence.mapper.FeedbackMapper;
import tiger.common.dal.persistence.system.query.FeedbackQuery;
import tiger.common.util.Paginator;
import tiger.core.basic.PageResult;
import tiger.core.domain.system.FeedbackDomain;
import tiger.core.domain.system.convert.FeedbackConvert;
import tiger.core.service.system.FeedbackService;

import java.util.List;

/**
 * @author alfred_yuan
 * @version v 0.1 16:46 alfred_yuan Exp $
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    Logger logger = Logger.getLogger(FeedbackServiceImpl.class);

    @Autowired
    FeedbackMapper feedbackMapper;

    /**
     * @see FeedbackService#create(FeedbackDomain)
     * @param feedbackDomain
     * @return
     */
    @Override
    public FeedbackDomain create(FeedbackDomain feedbackDomain) {
        if (feedbackDomain == null || feedbackDomain.getAccountId() == null) {
            return null;
        }

        FeedbackDO feedbackDO = FeedbackConvert.convert2DO(feedbackDomain);

        if (feedbackMapper.insertSelective(feedbackDO) > 0) {
            return FeedbackConvert.convert2Domain(feedbackDO);
        }

        return null;
    }

    @Override
    public PageResult<List<FeedbackDomain>> query(FeedbackQuery query) {
        //构建分页查询器
        Paginator paginator = new Paginator();
        int count = feedbackMapper.countFeedback(query);
        paginator.setItems(count);
        paginator.setItemsPerPage(query.getPageSize());
        paginator.setPage(query.getPageNum());

        PageResult<List<FeedbackDomain>> result = new PageResult<>();
        result.setData(FeedbackConvert.convert2Domains(feedbackMapper.query(query, paginator.getOffset(), paginator.getLength())));
        result.setPaginator(paginator);

        return result;
    }
}
