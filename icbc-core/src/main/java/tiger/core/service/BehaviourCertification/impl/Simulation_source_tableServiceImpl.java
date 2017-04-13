package tiger.core.service.BehaviourCertification.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.behaviour_authen.Simulation_source_table;
import tiger.common.dal.persistence.mapper.Simulation_source_tableMapper;
import tiger.core.service.BehaviourCertification.Simulation_source_tableService;

/**
 * Created by 王硕买个大葱头 on 2017/1/14.
 */
@Service
public class Simulation_source_tableServiceImpl implements Simulation_source_tableService{
    @Autowired
    Simulation_source_tableMapper simulation_source_tableMapper;


    @Override
    public Boolean insert(Simulation_source_table simulation_source_table) {
        return simulation_source_tableMapper.insert(simulation_source_table) > 0;
    }
}
