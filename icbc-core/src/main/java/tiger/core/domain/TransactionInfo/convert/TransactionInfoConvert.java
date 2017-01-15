package tiger.core.domain.TransactionInfo.convert;

import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

import java.util.ArrayList;
import java.util.List;

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

    /**
     *
     * @param transactionInfoDO
     * @return
     */

    public static TransactionInfoDomain convertDOtoDomain(TransactionInfoDO transactionInfoDO){
        if(null == transactionInfoDO){
            return null;
        }
        TransactionInfoDomain transactionInfoDomain = new TransactionInfoDomain();
        BeanUtil.copyPropertiesWithIgnores(transactionInfoDO, transactionInfoDomain);
        return transactionInfoDomain;
    }
    /**
     * list Do to Domain
     */
    public static List<TransactionInfoDomain> conver2DDomains(List<TransactionInfoDO> source){
        List<TransactionInfoDomain> target = new ArrayList<>(source.size());
        if(source.isEmpty()){
            return null;

        }

        source.forEach(TransactionInfoDomain -> target.add(convertDOtoDomain(TransactionInfoDomain)));
        return target;
    }
    /**Domain to Do
     *
     */
    public static TransactionInfoDO convertDomaintoDo(TransactionInfoDomain transactionInfoDomain){
        if(null==transactionInfoDomain){
            return null;
        }
        TransactionInfoDO transactionInfoDO = new TransactionInfoDO();
        BeanUtil.copyPropertiesWithIgnores(transactionInfoDomain,transactionInfoDO);
        return transactionInfoDO;
    }
}
