package tiger.biz.transactioninfo.support;

import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.core.basic.PageResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

import java.util.Date;
import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 */
public interface TransactionInfoManager {
    TransactionInfoDomain selectByPrimaryKey(Integer id);
    List<TransactionInfoDomain> selectAll();
    Boolean updateByPrimaryKey(TransactionInfoDO transactionInfoDO);
    TransactionInfoDomain selectOnlyByPrimaryKey(Integer id);
    List<TransactionInfoDomain> selectByPeriod(Date begintime, Date endtime);
    List<TransactionInfoDomain> selectByOutCardNum(String outCardNum);
}
