/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.core.domain.materials.convert;

import tiger.common.dal.persistence.materials.Materials;
import tiger.common.util.BeanUtil;
import tiger.core.domain.materials.MaterialsDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/8 08:15]
 */
public class MaterialsConvert {

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<MaterialsDomain> convert2Domains(List<Materials> source) {
        List<MaterialsDomain> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(materialDomain -> target.add(convert2Domain(materialDomain)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static MaterialsDomain convert2Domain(Materials source) {
        if (null == source) {
            return null;
        }
        MaterialsDomain target = new MaterialsDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        target.setDiscountprice(source.getDiscount() * source.getMarketprice());

        return target;
    }

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<Materials> convert2DOs(List<MaterialsDomain> source) {
        List<Materials> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(materialDO -> target.add(convert2DO(materialDO)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static Materials convert2DO(MaterialsDomain source) {
        if (null == source) {
            return null;
        }
        Materials target = new Materials();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }
}
