package tiger.core.domain.socialnet.convert;

import tiger.common.dal.persistence.socialnet.Socialnet;
import tiger.common.util.BeanUtil;
import tiger.core.domain.socialnet.socialnetDomain;

import java.util.ArrayList;
import java.util.List;

public class socialnetConvert{
    /**
     * 将list Socialnet的数据转换成socialnetDomain
     */
    public static List<socialnetDomain> listConvert2core(List<Socialnet> source) {
        List<socialnetDomain> target = new ArrayList<>(source.size());
        if (source.isEmpty()) {
            return null;
        }
        source.forEach(sDomain -> target.add(convert2core(sDomain)));
        return target;
    }

    /**
     *将list SocialnetDomain的数据转换成Socialnet
     */
    public static List<Socialnet> listConvert2dal(List<socialnetDomain> source) {
        List<Socialnet> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(s -> target.add(convert2dal(s)));
        return target;
    }

    /**
     * 将单个Socialnet的数据转换成socialnetDomain
     */
    public static socialnetDomain convert2core(Socialnet source) {
        if (null == source) {
            return null;
        }
        socialnetDomain target = new socialnetDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        target.setTrust(source.getTrust());
        return target;
    }

    /**
     * 将单个socialnetDomain的数据转换成Socialnet
     */
    public static Socialnet convert2dal(socialnetDomain source){
        if (null == source) {
            return null;
        }
        Socialnet target = new Socialnet();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        target.setTrust(source.getTrust());
        return target;
    }
}