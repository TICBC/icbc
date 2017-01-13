package tiger.core.service.transactionInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.common.dal.persistence.mapper.TransactionInfoDOMapper;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.core.domain.TransactionInfo.convert.TransactionInfoConvert;
import tiger.core.service.transactionInfo.TransactionInfoService;

import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 */
@Service
public class TransactionInfoServiceImpl implements TransactionInfoService{
    @Autowired
    TransactionInfoDOMapper transactionInfoDOMapper;
    /**
    public List<TransactionInfoDomain> getAll(){
        List<Transactioninfo> trans = transactionInfoDOMapper.selec
    }
    */
    public TransactionInfoDomain selectByPrimaryKey(Integer id){
        /*
        if(transactionInfoDOMapper==null){
            System.out.println("111");
        }
        */
        TransactionInfoDO transDO = transactionInfoDOMapper.selectByPrimaryKey(id);
        if(null!=transDO){
            return TransactionInfoConvert.convertDOtoDomain(transDO);
        }
        return null;
    }

    public List<TransactionInfoDomain> selectAll(){
        List<TransactionInfoDO> transactionInfoDOList = transactionInfoDOMapper.selectAll();
        return TransactionInfoConvert.conver2DDomains(transactionInfoDOList);
    }

}
