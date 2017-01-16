package tiger.biz.CustomerInfo.support;


import tiger.common.dal.persistence.icbc.CustomerInfoDO;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.domain.CustomerInfo.CustomerInfoDomain;

import java.util.List;

/**
 * Created by AUSA on 2017/1/10.
 */
public interface CustomerInfoManager {
    Boolean insert(String[][] customerInfo);

    //读取一条客户信息
    BaseResult<CustomerInfoDomain> read(Integer id);

    //获取所有客户信息
    PageResult<List<CustomerInfoDomain>> getAll();
}
