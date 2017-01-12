package tiger.web.api.controller.TransactionInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiger.biz.transactioninfo.support.TransactionInfoManager;
import tiger.core.basic.BaseResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;

import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 */
@RestController
@ResponseBody
@RequestMapping(APIConstants.BASE_API_URL + "/TransactionInfo")
public class TransactionInfoController extends BaseController{
    @Autowired
    TransactionInfoManager transactionInfoManager;
    /**
     * 根据id查找
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResult<TransactionInfoDomain> getTransactionInfoDomain(@RequestParam("id") Integer id){
        System.out.println(transactionInfoManager.selectByPrimaryKey(id).getId());
        TransactionInfoDomain transactionInfoDomain = transactionInfoManager.selectByPrimaryKey(id);
        return new BaseResult<>(transactionInfoDomain);
        //System.out.println("shuchu ");
    }
    /**
     * 查找所有交易信息
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<List<TransactionInfoDomain>> getAll(){
        List<TransactionInfoDomain> transactionInfoDomainList = transactionInfoManager.selectAll();
        return new BaseResult<>(transactionInfoDomainList);
    }
}
