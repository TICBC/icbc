package tiger.core.service.socialnet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.mapper.EquipmentInfoDOMapper;
import tiger.common.dal.persistence.mapper.SocialnetMapper;
import tiger.common.dal.persistence.socialnet.Socialnet;
import tiger.common.dal.persistence.socialnet.SocialnetExample;
import tiger.core.domain.socialnet.socialnetDomain;
import tiger.core.domain.socialnet.convert.socialnetConvert;
import tiger.core.service.device.DeviceService;
import tiger.core.service.socialnet.socialnetService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class socialnetServiceImpl implements socialnetService {
//public class socialnetServiceImpl{
    @Autowired
    SocialnetMapper sMapper;

    @Override
    public List<socialnetDomain> getSome(String user1, String user2){
        SocialnetExample example = new SocialnetExample();
        SocialnetExample.Criteria criteria = example.createCriteria();

        criteria.andU1EqualTo(user1);
        criteria.andU2EqualTo(user2);

        return socialnetConvert.listConvert2core(sMapper.selectByExample(example));
    }
}

/**
    public void Some(String user1, String user2){
        int a = sMapper.selectTrust(user1,user2);
        System.out.print(a);

    }
    public static void main(String[] args){
        socialnetServiceImpl s = new socialnetServiceImpl();
        s.Some("39","788");
    }*/
