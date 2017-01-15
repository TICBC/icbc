package tiger.web.api.controller.CustomerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tiger.biz.CustomerInfo.support.CustomerInfoManager;
import tiger.common.dal.annotation.LoginRequired;
import tiger.common.dal.persistence.icbc.CustomerInfoDO;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.domain.CustomerInfo.CustomerInfoDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;

import java.util.List;

/**
 * Created by AUSA on 2017/1/10.
 */

@RestController
@ResponseBody
@RequestMapping(APIConstants.BASE_API_URL + "/customerinfo")
public class CustomerInfoController extends BaseController{

    @Autowired
    CustomerInfoManager customerInfoManager;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Boolean> insertCustomerInfo(@RequestBody String[][] customerInfo,
                                               BindingResult bindingResult) {
//        System.out.println("hah");
        return new BaseResult<>(customerInfoManager.insert(customerInfo));
    }

    /**
     * 获取客户信息
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<CustomerInfoDomain> getCustomerInfoById(@RequestParam("id") Integer id) {
//        System.out.println("hah");
        return customerInfoManager.read(id);

    }

    /**
     * 获取所有的设备信息
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<List<CustomerInfoDomain>> getAll() {

        return customerInfoManager.getAll();
    }
}
