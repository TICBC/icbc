package tiger.core.domain.LoginInfo.convert;

import tiger.common.dal.persistence.icbc.LoginInfoDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.LoginInfo.LoginInfoDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AUSA on 2017/1/15.
 */
public class LoginInfoConvert {
    public static List<LoginInfoDomain> convert2Domains(List<LoginInfoDO> source){
        List<LoginInfoDomain> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(LoginInfoDomainDO -> target.add(convert2Domain(LoginInfoDomainDO)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static LoginInfoDomain convert2Domain(LoginInfoDO source) {
        if (null == source) {
            return null;
        }
        LoginInfoDomain target = new LoginInfoDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<LoginInfoDO> convert2DOs(List<LoginInfoDomain> source) {
        List<LoginInfoDO> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(LoginInfoDO -> target.add(convert2DO(LoginInfoDO)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static LoginInfoDO convert2DO(LoginInfoDomain source) {
        if (null == source) {
            return null;
        }
        LoginInfoDO target = new LoginInfoDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }
}
