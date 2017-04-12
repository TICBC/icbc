package tiger.core.domain.TransactionInfo.convert;

import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.common.dal.persistence.icbc.TransactioninfocountDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.TransactionInfo.TransactionInfoCountDomain;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 */
public class TransactionInfoCountConvert {
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

    /**
     *
     * @param transactionInfoCountDO
     * @return
     */

    public static TransactionInfoCountDomain convertDOtoDomain(TransactioninfocountDO transactionInfoCountDO){
        if(null == transactionInfoCountDO){
            return null;
        }
        TransactionInfoCountDomain transactionInfoCountDomain = new TransactionInfoCountDomain();
        BeanUtil.copyPropertiesWithIgnores(transactionInfoCountDO, transactionInfoCountDomain);
        return transactionInfoCountDomain;
    }
    /**
     * list Do to Domain
     */

    /**Domain to Do
     *
     */
    public static TransactioninfocountDO convertDomaintoDo(TransactionInfoCountDomain transactionInfoDomain){
        if(null==transactionInfoDomain){
            return null;
        }
        TransactioninfocountDO transactionInfoDO = new TransactioninfocountDO();
        BeanUtil.copyPropertiesWithIgnores(transactionInfoDomain,transactionInfoDO);
        return transactionInfoDO;
    }
}
