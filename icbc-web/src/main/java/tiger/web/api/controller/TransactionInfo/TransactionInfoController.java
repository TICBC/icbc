package tiger.web.api.controller.TransactionInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tiger.biz.transactioninfo.support.TransactionInfoManager;
import tiger.core.basic.BaseResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.core.domain.TransactionInfo.convert.TransactionInfoConvert;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;

import java.util.Date;
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
        final Boolean aBoolean = transactionInfoManager.updateByPrimaryKey(TransactionInfoConvert.convertDomaintoDo(transactionInfoDomain));
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
    /**
     *  只根据id查找
     */
    @RequestMapping(value = "/Select", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<TransactionInfoDomain> getOnlyTransactionInfoDomain(@RequestParam("id") Integer id){
        System.out.println(transactionInfoManager.selectByPrimaryKey(id).getId());
        TransactionInfoDomain transactionInfoDomain = transactionInfoManager.selectByPrimaryKey(id);
        //transactionInfoManager.updateByPrimaryKey(TransactionInfoConvert.convertDomaintoDo(transactionInfoDomain));
        return new BaseResult<>(transactionInfoDomain);
        //System.out.println("shuchu ");
    }

    /**
     * 查找某个时间段内的交易记录
     */
    @RequestMapping(value = "/period", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<List<TransactionInfoDomain>> getTransactionPeriod(@RequestParam("FromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date begintime,
                                                                        @RequestParam("ToDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endtime){
        List<TransactionInfoDomain> transactionInfoDomainList = transactionInfoManager.selectByPeriod(begintime, endtime);
        return new BaseResult<>(transactionInfoDomainList);
    }

    /**
     * 查找某个卡号的转出交易记录
     */
    @RequestMapping(value = "/outcardnum", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<List<TransactionInfoDomain>> getTransactionOutCardNum(@RequestParam("outcardnum") String outcardnum){
        return new BaseResult<>(transactionInfoManager.selectByOutCardNum(outcardnum));
    }
}
