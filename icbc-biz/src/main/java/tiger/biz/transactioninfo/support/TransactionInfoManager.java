package tiger.biz.transactioninfo.support;

import tiger.core.basic.PageResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 */
public interface TransactionInfoManager {
    TransactionInfoDomain selectByPrimaryKey(Integer id);
}
