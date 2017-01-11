package tiger.biz.transactioninfo.support.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    @Override
    public TransactionInfoDomain selectByPrimaryKey(Integer id){
        return transactionInfoService.selectByPrimaryKey(id);
    }

}
