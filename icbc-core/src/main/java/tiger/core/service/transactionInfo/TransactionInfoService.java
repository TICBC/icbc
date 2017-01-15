package tiger.core.service.transactionInfo;

import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

import java.util.Date;
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
     *  根据id查找，访问三个后台，打标签
     */

    TransactionInfoDomain selectByPrimaryKey(Integer id);

    /**
     * find ALL查找所有
     */
    List<TransactionInfoDomain> selectAll();
    /**
     * 更新数据
     */
    Boolean updateByPrimaryKey(TransactionInfoDO transactionInfoDO);
    /**
     * 只根据id查找
     */
    TransactionInfoDomain selectOnlyByPrimaryKey(Integer id);

    /**
     * 查找某个时间段内的交易记录
     */
    List<TransactionInfoDomain> selectPeriod(Date begintime, Date endtime);

    /**
     * 查找某个用户的转出交易记录
     */
    List<TransactionInfoDomain> selectOutCardNum(String outCardNum);
}
