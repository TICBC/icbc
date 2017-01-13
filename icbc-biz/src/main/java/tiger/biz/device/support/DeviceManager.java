package tiger.biz.device.support;

import java.util.List;
import tiger.common.dal.persistence.icbc.EquipmentInfoDO;
import tiger.common.dal.persistence.icbc.SelectedAndroidDO;
import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.core.basic.PageResult;
import tiger.core.domain.device.EquipmentInfoDomain;

/**
 * Created by archer on 2017/1/10.
 */
public interface DeviceManager {
    PageResult<List<SelectedAndroidDO>> getAll();

    String deviceAuth(SelectedAndroidDO authInfo);

    String deviceInterface(TransactionInfoDO transactionInfoDO);
}
