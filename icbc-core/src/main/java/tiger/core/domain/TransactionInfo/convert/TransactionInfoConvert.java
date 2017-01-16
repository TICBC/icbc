package tiger.core.domain.TransactionInfo.convert;

import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

/**
 * Created by Jian on 2017/1/10.
 */
public class TransactionInfoConvert {
    /*
    public static TransactionInfoDO convert2DODomain(TransactionInfoDomain source){
        if(null == source){
            return null;
        }
        TransactionInfoDO target = new TransactionInfoDO();
        BeanUtil.copyPropertiesWithIgnores(source,target);
        return target;
    }
    */
    public static TransactionInfoDomain convertDOtoDomain(TransactionInfoDO transactionInfoDO){
        if(null == transactionInfoDO){
            return null;
        }
        TransactionInfoDomain transactionInfoDomain = new TransactionInfoDomain();
        BeanUtil.copyPropertiesWithIgnores(transactionInfoDO, transactionInfoDomain);
        return transactionInfoDomain;
    }
}
