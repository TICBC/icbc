/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.core.domain.results.convert;

import org.springframework.beans.factory.annotation.Autowired;
import tiger.common.dal.persistence.mapper.MaterialsMapper;
import tiger.common.dal.persistence.materials.Materials;
import tiger.common.dal.persistence.results.Results;
import tiger.common.util.BeanUtil;
import tiger.core.domain.results.ResultsDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/11 11:36]
 */
public class ResultsConvert {

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<ResultsDomain> convert2Domains(List<Results> source) {
        List<ResultsDomain> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(resultsDomain -> target.add(convert2Domain(resultsDomain)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static ResultsDomain convert2Domain(Results source) {
        if (null == source) {
            return null;
        }
        ResultsDomain target = new ResultsDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<Results> convert2DOs(List<ResultsDomain> source) {
        List<Results> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(resultsDO -> target.add(convert2DO(resultsDO)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static Results convert2DO(ResultsDomain source) {
        if (null == source) {
            return null;
        }
        Results target = new Results();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }
}
