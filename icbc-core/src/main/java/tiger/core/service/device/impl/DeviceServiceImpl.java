package tiger.core.service.device.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.icbc.EquipmentInfoDO;
import tiger.common.dal.persistence.mapper.EquipmentInfoDOMapper;
import tiger.core.service.device.DeviceService;
import tiger.core.domain.device.EquipmentInfoDomain;
import tiger.core.domain.device.convert.EquipmentInfoConvert;
;

import java.util.List;

/**
 * Created by archer on 2017/1/10.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    EquipmentInfoDOMapper equipmentInfoDOMapper;

    @Override
    public List<EquipmentInfoDomain> getAll(){
        List<EquipmentInfoDO> DOs = equipmentInfoDOMapper.selectAll();
        return EquipmentInfoConvert.convert2Domains(DOs);
    }

    @Override
    public List<EquipmentInfoDomain> getAllEqu() {
        List<EquipmentInfoDO> DOs = equipmentInfoDOMapper.selectAll();
        return EquipmentInfoConvert.convert2Domains(DOs);
    }
}
