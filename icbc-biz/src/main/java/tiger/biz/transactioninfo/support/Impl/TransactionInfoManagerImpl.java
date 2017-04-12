package tiger.biz.transactioninfo.support.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tiger.biz.device.support.DeviceManager;
import tiger.biz.transactioninfo.support.TransactionInfoManager;
import tiger.common.dal.persistence.icbc.SelectActDO;
import tiger.common.dal.persistence.icbc.SelectedSocialNet;
import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.core.basic.PageResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.core.service.materials.MaterialsService;
import tiger.core.service.transactionInfo.TransactionInfoService;

import javax.transaction.TransactionManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Jian on 2017/1/10.
 * 调用service 提供的服务
 */
@Service
public class TransactionInfoManagerImpl implements TransactionInfoManager{
    @Autowired
    //MaterialsService materialsService;
    TransactionInfoService transactionInfoService;

    @Autowired
    DeviceManager deviceManager;


    @Override
    public TransactionInfoDomain selectByPrimaryKey(Integer id){
        TransactionInfoDomain transactionInfoDomain = transactionInfoService.selectByPrimaryKey(id);
        //暂时分开测试

        String dev = deviceManager.deviceInterface(transactionInfoDomain);
        int devInt;
        if(dev.equals("true")){
            devInt=1;
        }
        else if(dev.equals("false")){
            devInt=0;
        }
        else{
            devInt=0;
        }
        transactionInfoDomain.setEquSign(devInt);
        System.out.println("devInt"+devInt);
        System.out.println("dev"+dev);

        /**
         * 信任关系判定
        */

        SelectedSocialNet selectedSocialNet = new SelectedSocialNet();
        selectedSocialNet.setUser1(transactionInfoDomain.getTranOutCardNum());
        selectedSocialNet.setUser2(transactionInfoDomain.getTranInCardNum());
        System.out.println(" Userq信息："+selectedSocialNet.getUser1()+" Userq信息："+selectedSocialNet.getUser2());
        //写死在对象里面

        selectedSocialNet.setTime("12");
        selectedSocialNet.setMoney("12");
        //System.out.println("所有User信息"+selectedSocialNet);
        //发送http请求到远程服务器
        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<SelectedSocialNet> entity = restTemplate.postForEntity("http://11.0.1.77:8080/api/socialnet/all", selectedSocialNet, SelectedSocialNet.class);
        //ResponseEntity<String> entity = restTemplate.postForEntity("http://10.60.150.105:8080/Socialnet/api/socialnet/pass", selectedSocialNet, String.class);
        //String TraStr = entity.getBody();
        String TraStr = "1";
        //String TraStr = result.getPass();
        //System.out.println(result);
        System.out.println("TraStr返回值："+TraStr);
        int TraInt;
        if(TraStr.equals("1")){
            TraInt=1;
        }
        else if(TraStr.equals("0")){
            TraInt=0;
        }
        else{
            TraInt=0;
        }
        transactionInfoDomain.setTruSign(TraInt);

        /**
         * 行为组后端联调
         */

        SelectActDO selectActDO = new SelectActDO();
        //selectActDO.setTimeStamp(transactionInfoDomain.getEventDt());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /**
         * 由于日期格式不同，需要进行转化，下面是Date to String 和String to Date 两种方法
         * SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
         String dstr="2008-4-24";
         java.util.Date date=sdf.parse(dstr);
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         java.util.Date date=new java.util.Date();
         String str=sdf.format(date);
         */
        System.out.println(sdf.format(transactionInfoDomain.getEventDt()));
        System.out.println(transactionInfoDomain.getTranOutCardNum());
        selectActDO.setTimeStamp(sdf.format(transactionInfoDomain.getEventDt()));
        //selectActDO.setTimeStamp("2015-01-01 03:17:52");
        //selectActDO.setUser("1");
        selectActDO.setUser(transactionInfoDomain.getTranOutCardNum());

        int ActInt;
        try{
            RestTemplate restTemplate3 = new RestTemplate();
            ResponseEntity<Boolean> entity3 = restTemplate3.postForEntity("http://10.60.150.238:8080/api/BehaviourCertification/judge", selectActDO, Boolean.class);
            Boolean ActStr = entity3.getBody();

            System.out.println("ActStr");
            System.out.println(ActStr);

            if(ActStr){
                ActInt=1;
            }
            else{
                ActInt=0;
            }

        }catch(Exception e){
            ActInt=0;
            System.out.println("500");
            System.out.println(e);
        }
        transactionInfoDomain.setActSign(ActInt);
        //transactionInfoDomain.setTruSign(1);
        //transactionInfoDomain.setEquSign(1);
        //transactionInfoDomain.setActSign(1);

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

    @Override
    public List<TransactionInfoDomain> selectByOutCardNum(String outCardNum) {
        return transactionInfoService.selectOutCardNum(outCardNum);
    }
}
