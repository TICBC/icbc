package tiger.core.domain.BehaviourCertification.convert;

import tiger.common.dal.persistence.behaviour_authen.Simulation_source_table;
import tiger.common.util.BeanUtil;
import tiger.core.domain.BehaviourCertification.Simulation_source_tableDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archer on 2017/1/9.
 */
public class Simulation_source_tableConvert {
    /**
     * List之间的转换
     *
     * @param source
     * @return
     */

    public static List<Simulation_source_tableDomain> convert2Domains(List<Simulation_source_table> source) {
        List<Simulation_source_tableDomain> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }
        source.forEach(simulation_source_tableDomain -> target.add(convert2Domain(simulation_source_tableDomain)));

        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static Simulation_source_tableDomain convert2Domain(Simulation_source_table source) {
        if (null == source) {
            return null;
        }
        Simulation_source_tableDomain target = new Simulation_source_tableDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }

    /**
     * List之间的转换
     *
     * @param source
     * @return
     */
    public static List<Simulation_source_table> convert2DOs(List<Simulation_source_tableDomain> source) {
        List<Simulation_source_table> target = new ArrayList<>(source.size());

        if (source.isEmpty()) {
            return null;
        }

        source.forEach(Simulation_source_table -> target.add(convert2DO(Simulation_source_table)));
        return target;
    }

    /**
     * 单个转换
     *
     * @param source
     * @return
     */
    public static Simulation_source_table convert2DO(Simulation_source_tableDomain source) {
        if (null == source) {
            return null;
        }
        Simulation_source_table target = new Simulation_source_table();
        BeanUtil.copyPropertiesWithIgnores(source, target);

        return target;
    }
}
