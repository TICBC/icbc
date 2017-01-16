package tiger.biz.transactioninfo.support.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.transactioninfo.support.TransactionInfoManager;
import tiger.core.basic.PageResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.core.service.materials.MaterialsService;
import tiger.core.service.transactionInfo.TransactionInfoService;

import javax.transaction.TransactionManager;
import java.util.List;
import java.util.Random;

/**
 * Created by Jian on 2017/1/10.
 * 调用service 提供的服务
 */
@Service
public class TransactionInfoManagerImpl implements TransactionInfoManager{
    @Autowired
    //MaterialsService materialsService;
    TransactionInfoService transactionInfoService;


    @Override
    public TransactionInfoDomain selectByPrimaryKey(Integer id){

        TransactionInfoDomain transactionInfoDomain = transactionInfoService.selectByPrimaryKey(id);
        //暂时分开测试
        /**
        String dev = deviceManager.deviceInterface(transactionInfoDomain);
        int devInt;
        if(dev=="true"){
            devInt=1;
        }
        else{
            devInt=0;
        }
        transactionInfoDomain.setEquSign(devInt);
        */
        /**
         * 信任关系判定
        */
        /**
        SelectedSocialNet selectedSocialNet = new SelectedSocialNet();
        selectedSocialNet.setUser1(transactionInfoDomain.getTranOutCardNum());
        selectedSocialNet.setUser2(transactionInfoDomain.getTranInCardNum());
        //System.out.println(" Userq信息："+selectedSocialNet.getUser1()+" Userq信息："+selectedSocialNet.getUser2());
        //写死在对象里面

        selectedSocialNet.setTime("12");
        selectedSocialNet.setMoney("12");
        //System.out.println("所有User信息"+selectedSocialNet);
        //发送http请求到远程服务器
        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<SelectedSocialNet> entity = restTemplate.postForEntity("http://11.0.1.77:8080/api/socialnet/all", selectedSocialNet, SelectedSocialNet.class);
        ResponseEntity<String> entity = restTemplate.postForEntity("http://11.0.1.77:8080/api/socialnet/pass", selectedSocialNet, String.class);
        String TraStr = entity.getBody();
        //String TraStr = result.getPass();
        //System.out.println(result);
        System.out.println("返回值："+TraStr);
        int TraInt;
        if(TraStr=="1"){
            TraInt=1;
        }
        else{
            TraInt=0;
        }
        transactionInfoDomain.setTruSign(TraInt);
        */
        Random random=new Random();
        System.out.println(random.nextInt(3));
        transactionInfoDomain.setTruSign(random.nextInt(2));
        transactionInfoDomain.setEquSign(random.nextInt(2));
        transactionInfoDomain.setActSign(random.nextInt(2));

        return transactionInfoDomain;
    }

    @Override
    public List<TransactionInfoDomain> selectAll(){
        return transactionInfoService.selectAll();
    }
    @Override
    public Boolean updateByPrimaryKey(TransactionInfoDO transactionInfoDO){
        if(transactionInfoDO==null){
            return false;
        }
        System.out.println(transactionInfoDO.getEventDt().getTime());
        return transactionInfoService.updateByPrimaryKey(transactionInfoDO);
    }
    @Override
    public TransactionInfoDomain selectOnlyByPrimaryKey(Integer id){
        TransactionInfoDomain transactionInfoDomain = transactionInfoService.selectByPrimaryKey(id);
        return  transactionInfoDomain;
    }

    @Override
    public List<TransactionInfoDomain> selectByPeriod(Date begintime, Date endtime){
        return transactionInfoService.selectPeriod(begintime, endtime);
    }

}
