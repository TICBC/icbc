package tiger.biz.device.support;

import org.springframework.beans.factory.annotation.Autowired;
import tiger.core.service.socialnet.impl.socialnetServiceImpl;
import tiger.core.service.socialnet.socialnetService;

/**
 * Created by caijitou on 2017/1/12.
 */
public class SocialManager {
    @Autowired
    socialnetService socialnetServic;

    public static void main (String[] args){
            socialnetServiceImpl s = new socialnetServiceImpl();
            System.out.print(s.getSome("39","788"));
    }
}
