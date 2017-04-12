import org.springframework.beans.factory.annotation.Autowired;
import tiger.biz.transactioninfo.support.TransactionInfoManager;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;

import java.math.BigDecimal;

/**
 * Created by CJ on 2017/4/13.
 */
public class insertTest {
    public static void main(String[] args){

        TransactionInfoManager transactionInfoManager;
        System.out.println("hehe");

        TransactionInfoDomain transactionInfoDomain = new TransactionInfoDomain();
        transactionInfoDomain.setCustNum("1");
        transactionInfoDomain.setTranInCardNum("2");
        transactionInfoDomain.setTxAmt(BigDecimal.valueOf(565814));

        //int id = transactionInfoManager.insertDomain(transactionInfoDomain);

    }
}
