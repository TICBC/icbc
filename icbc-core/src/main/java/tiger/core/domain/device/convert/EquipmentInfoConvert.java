package tiger.core.domain.device.convert;

import tiger.common.dal.persistence.icbc.EquipmentInfoDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.device.EquipmentInfoDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archer on 2017/1/9.
 */
public class EquipmentInfoConvert {
    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<EquipmentInfoDomain> convert2Domains(List<EquipmentInfoDO> source) {
        List<EquipmentInfoDomain> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(equipmentInfoDomain -> target.add(convert2Domain(equipmentInfoDomain)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static EquipmentInfoDomain convert2Domain(EquipmentInfoDO source) {
        if (null == source) {
            return null;
        }
        EquipmentInfoDomain target = new EquipmentInfoDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<EquipmentInfoDO> convert2DOs(List<EquipmentInfoDomain> source) {
        List<EquipmentInfoDO> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(equipmentInfoDO -> target.add(convert2DO(equipmentInfoDO)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static EquipmentInfoDO convert2DO(EquipmentInfoDomain source) {
        if (null == source) {
            return null;
        }
        EquipmentInfoDO target = new EquipmentInfoDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }
}
