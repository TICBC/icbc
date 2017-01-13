package tiger.biz.transactioninfo.support.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.device.support.DeviceManager;
import tiger.biz.transactioninfo.support.TransactionInfoManager;
import tiger.core.basic.PageResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.core.service.materials.MaterialsService;
import tiger.core.service.transactionInfo.TransactionInfoService;

import javax.transaction.TransactionManager;
import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 * 调用service 提供的服务
 */
@Service
public class TransactionInfoManagerImpl implements TransactionInfoManager{
    @Autowired
    //MaterialsService materialsService;
    TransactionInfoService transactionInfoService;

    @Autowired
    DeviceManager deviceManager;


    @Override
    public TransactionInfoDomain selectByPrimaryKey(Integer id){
        TransactionInfoDomain transactionInfoDomain = transactionInfoService.selectByPrimaryKey(id);
        String dev = deviceManager.deviceInterface(transactionInfoDomain);
        int devInt;
        if(dev=="true"){
            devInt=1;
        }
        else{
            devInt=0;
        }
        transactionInfoDomain.setEquSign(devInt);
        return transactionInfoDomain;
    }

    @Override
    public List<TransactionInfoDomain> selectAll(){
        return transactionInfoService.selectAll();
    }
}
