package tiger.core.service.transactionInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.common.dal.persistence.icbc.TransactionInfoDOExample;
import tiger.common.dal.persistence.mapper.TransactionInfoDOMapper;
import tiger.common.dal.persistence.materials.MaterialsExample;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.core.domain.TransactionInfo.convert.TransactionInfoConvert;
import tiger.core.service.transactionInfo.TransactionInfoService;

import java.util.Date;
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
    /**
     * 根据id查找
     * @param id
     * @return
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

    /**
     * 直接查找所有
     * @return
     */

    public List<TransactionInfoDomain> selectAll(){
        List<TransactionInfoDO> transactionInfoDOList = transactionInfoDOMapper.selectAll();
        return TransactionInfoConvert.conver2DDomains(transactionInfoDOList);
    }
    /**
     * 更新数据
     */
    public Boolean updateByPrimaryKey(TransactionInfoDO transactionInfoDO){
        return transactionInfoDOMapper.updateByPrimaryKey(transactionInfoDO) > 0;
    }
    /**
     * 只根据id查找
     */
    public TransactionInfoDomain selectOnlyByPrimaryKey(Integer id){
        TransactionInfoDO transDO = transactionInfoDOMapper.selectByPrimaryKey(id);
        if(null!=transDO){
            return TransactionInfoConvert.convertDOtoDomain(transDO);
        }
        return null;
    }

    public List<TransactionInfoDomain> selectPeriod(Date begintime, Date endtime){
        TransactionInfoDOExample example = new TransactionInfoDOExample();
        TransactionInfoDOExample.Criteria criteria = example.createCriteria();

        criteria.andEventDtLessThan(endtime);
        criteria.andEventDtGreaterThanOrEqualTo(begintime);

        return TransactionInfoConvert.conver2DDomains(transactionInfoDOMapper.selectByExample(example));
    }

    public List<TransactionInfoDomain> selectOutCardNum(String outCardNum){
        return TransactionInfoConvert.conver2DDomains(transactionInfoDOMapper.selectByOutCardNum(outCardNum));
    }
}
