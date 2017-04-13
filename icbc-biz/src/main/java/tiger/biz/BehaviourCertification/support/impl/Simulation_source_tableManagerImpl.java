package tiger.biz.BehaviourCertification.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.BehaviourCertification.support.Simulation_source_tableManager;
import tiger.core.domain.BehaviourCertification.Simulation_source_tableDomain;
import tiger.core.domain.BehaviourCertification.convert.Simulation_source_tableConvert;
import tiger.core.service.BehaviourCertification.Simulation_source_tableService;

/**
 * Created by archer on 2017/1/10.
 */
@Service
public class Simulation_source_tableManagerImpl implements Simulation_source_tableManager {
    @Autowired
    Simulation_source_tableService simulation_source_tableService;

    @Override
    public Boolean insert(Simulation_source_tableDomain simulation_source_tableDomain) {


        return simulation_source_tableService.insert(Simulation_source_tableConvert.convert2DO(simulation_source_tableDomain));
      /*  return false;*/
    }
}
