package tiger.core.service.device;

import tiger.core.domain.device.EquipmentInfoDomain;

import java.util.List;

/**
 * Created by archer on 2017/1/10.
 */
public interface DeviceService {
    List<EquipmentInfoDomain> getAll();
}
