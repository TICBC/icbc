package tiger.core.service.CustomerInfo;

import tiger.common.dal.persistence.icbc.CustomerInfoDO;
import tiger.core.domain.CustomerInfo.CustomerInfoDomain;

import java.util.List;

/**
 * Created by AUSA on 2017/1/10.
 */
public interface CustomerInfoService {
    /**
     * 查找一条数据
     *
     * @return
     */
    CustomerInfoDomain getCustomerInfoById(Integer id);

    /**
     * 插入一条信息
     *
     * @param customerInfoDO
     * @return
     */
    Boolean insert(CustomerInfoDO customerInfoDO);

    /**
     * 获取所有的客户信息
     *
     * @return
     */
    List<CustomerInfoDomain> getAll();
}
