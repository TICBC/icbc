package tiger.core.service.transactionInfo;

import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 */
public interface TransactionInfoService {
    /**
     *
     *  获取所有交易信息
     */
    //List<TransactionInfoDomain> getAll();
    /**
     *  根据id查找
     */

    TransactionInfoDomain selectByPrimaryKey(Integer id);

    /**
     * find ALL查找所有
     */
    List<TransactionInfoDomain> selectAll();


}
