package tiger.biz.CustomerInfo.support;


import tiger.core.basic.BaseResult;
import tiger.core.domain.CustomerInfo.CustomerInfoDomain;

/**
 * Created by AUSA on 2017/1/10.
 */
public interface CustomerInfoManager {
    Boolean insert(String[][] customerInfo);

    BaseResult<CustomerInfoDomain> read(Integer id);
}
