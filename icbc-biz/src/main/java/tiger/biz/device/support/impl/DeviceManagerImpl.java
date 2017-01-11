package tiger.biz.device.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.device.support.DeviceManager;
import tiger.common.dal.persistence.icbc.EquipmentInfoDO;
import tiger.common.dal.persistence.mapper.EquipmentInfoDOMapper;
import tiger.core.basic.PageResult;
import tiger.core.domain.device.EquipmentInfoDomain;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.core.service.device.DeviceService;

import java.util.List;

/**
 * Created by archer on 2017/1/10.
 */
@Service
public class DeviceManagerImpl implements DeviceManager{
    @Autowired
    DeviceService deviceService;

    @Override
    public PageResult<List<EquipmentInfoDomain>> getAll() {

        return new PageResult<>(deviceService.getAll());
    }
}
