package tiger.core.service.CustomerInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.icbc.CustomerInfoDO;
import tiger.common.dal.persistence.mapper.CustomerInfoDOMapper;
import tiger.core.domain.CustomerInfo.CustomerInfoDomain;
import tiger.core.domain.CustomerInfo.convert.CustomerInfoConvert;
import tiger.core.service.CustomerInfo.CustomerInfoService;

import java.util.List;

/**
 * Created by AUSA on 2017/1/10.
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private CustomerInfoDOMapper customerInfoDOMapper;


    @Override
    public CustomerInfoDomain getCustomerInfoById(Integer id) {
        return CustomerInfoConvert.convert2Domain(customerInfoDOMapper.selectByPrimaryKey(id));
    }

    @Override
    public Boolean insert(CustomerInfoDO customerInfoDO) {
        return customerInfoDOMapper.insert(customerInfoDO) > 0;
    }

    @Override
    public List<CustomerInfoDomain> getAll() {
        List<CustomerInfoDO> DOs = customerInfoDOMapper.selectAll();
        return CustomerInfoConvert.convert2Domains(DOs);
    }
}
