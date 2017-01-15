package tiger.biz.CustomerInfo.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.CustomerInfo.support.CustomerInfoManager;
import tiger.common.dal.persistence.icbc.CustomerInfoDO;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.CustomerInfo.CustomerInfoDomain;
import tiger.core.domain.CustomerInfo.convert.CustomerInfoConvert;
import tiger.core.service.CustomerInfo.CustomerInfoService;

import java.util.List;

/**
 * Created by AUSA on 2017/1/10.
 */
@Service
public class CustomerInfoManagerImpl implements CustomerInfoManager {
    @Autowired
    CustomerInfoService customerInfoService;

    @Override
    public Boolean insert(String[][] customerInfo) {
        if (customerInfo == null) {
            return false;
        }
        boolean mark = true;
        for (int i = 1; i < customerInfo.length; i++) {
            CustomerInfoDomain temp = new CustomerInfoDomain();
            temp.setPartyId(customerInfo[i][1]);
            temp.setGenderCd(customerInfo[i][2]);
            mark = mark &&  customerInfoService.insert(CustomerInfoConvert.convert2DO(temp));
        }
        return mark;
    }

    @Override
    public BaseResult<CustomerInfoDomain> read(Integer id) {
        CustomerInfoDomain message = customerInfoService.getCustomerInfoById(id);

        if (message == null) {
            throw new TigerException(ErrorCodeEnum.NOT_FOUND, "不存在的客户信息");
        }

        return new BaseResult<>(message);
    }

    @Override
    public PageResult<List<CustomerInfoDomain>> getAll() {
        return new PageResult<List<CustomerInfoDomain>>(customerInfoService.getAll());
    }
}
