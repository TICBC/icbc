package tiger.core.domain.CustomerInfo.convert;


import tiger.core.domain.CustomerInfo.CustomerInfoDomain;
import tiger.common.util.BeanUtil;
import tiger.common.dal.persistence.icbc.CustomerInfoDO;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AUSA on 2017/1/10.
 */
public class CustomerInfoConvert {
    public static List<CustomerInfoDomain> convert2Domains(List<CustomerInfoDO> source){
        List<CustomerInfoDomain> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(CustomerInfoDomainDO -> target.add(convert2Domain(CustomerInfoDomainDO)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static CustomerInfoDomain convert2Domain(CustomerInfoDO source) {
        if (null == source) {
            return null;
        }
        CustomerInfoDomain target = new CustomerInfoDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<CustomerInfoDO> convert2DOs(List<CustomerInfoDomain> source) {
        List<CustomerInfoDO> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(CustomerInfoDO -> target.add(convert2DO(CustomerInfoDO)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static CustomerInfoDO convert2DO(CustomerInfoDomain source) {
        if (null == source) {
            return null;
        }
        CustomerInfoDO target = new CustomerInfoDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }
}
