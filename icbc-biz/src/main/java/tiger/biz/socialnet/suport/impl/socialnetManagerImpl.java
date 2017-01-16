package tiger.biz.socialnet.suport.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.socialnet.suport.socialnetManager;
import tiger.common.dal.persistence.socialnet.Socialnet;
import tiger.core.basic.PageResult;
import tiger.core.domain.socialnet.socialnetDomain;
import tiger.core.domain.socialnet.convert.socialnetConvert;
import tiger.core.service.socialnet.socialnetService;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class socialnetManagerImpl implements socialnetManager {
    @Autowired
    socialnetService sService;

    private static final double thred = 0.26;

    /**
    @Override
    public PageResult<List<socialnetDomain>> getSome(String user1, String user2) {

        return new PageResult<>(sService.getSome(user1, user2));
    }
    */
    @Override
    //public PageResult<List<socialnetDomain>> getSome(String user1, String user2, String time, String money) {
    public PageResult<List<socialnetDomain>> getSome(socialnetDomain sDomain) {
        String user1 = sDomain.getUser1();
        String user2 = sDomain.getUser2();
        String time = sDomain.getTime();
        String money = sDomain.getMoney();
        List<socialnetDomain> trustlist = new ArrayList<socialnetDomain>();
        trustlist = sService.getSome(user1,user2);
        if(trustlist==null){
            socialnetDomain result = new socialnetDomain();
            result.setUser1(user1);
            result.setUser2(user2);
            result.setTime(time);
            result.setMoney(money);
            result.setTrust("0");
            result.setPass("0");
            List<socialnetDomain> trustlist_new = new ArrayList<socialnetDomain>();
            trustlist_new.add(result);
            return new PageResult<>(trustlist_new);
        }
        else if(trustlist.isEmpty()){
            socialnetDomain result = new socialnetDomain();
            result.setUser1(user1);
            result.setUser2(user2);
            result.setTime(time);
            result.setMoney(money);
            result.setTrust("0");
            result.setPass("0");
            trustlist.add(result);
            return new PageResult<>(trustlist);
        }
        else {
            for (socialnetDomain item : trustlist) {
                item.setUser1(user1);
                item.setUser2(user2);
                item.setTime(time);
                item.setMoney(money);
                double strust = Double.parseDouble(item.getTrust());
                if (strust < thred) {
                    item.setPass("0");
                } else {
                    item.setPass("1");
                }
            }
            return new PageResult<>(trustlist);
        }
    }

}
