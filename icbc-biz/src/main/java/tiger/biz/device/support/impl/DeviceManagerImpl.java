package tiger.biz.device.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tiger.biz.device.support.DeviceManager;
import tiger.common.dal.persistence.icbc.EquipmentInfoDO;
import tiger.common.dal.persistence.icbc.SelectedAndroidDO;
import tiger.common.dal.persistence.icbc.TransactionInfoDO;
import tiger.common.dal.persistence.mapper.EquipmentInfoDOMapper;
import tiger.core.basic.PageResult;
import tiger.core.domain.TransactionInfo.TransactionInfoDomain;
import tiger.core.domain.device.EquipmentInfoDomain;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.core.service.device.DeviceService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by archer on 2017/1/10.
 */
@Service
public class DeviceManagerImpl implements DeviceManager{
    @Autowired
    DeviceService deviceService;

    @Override
    public PageResult<List<SelectedAndroidDO>> getAll() {
        //申请一个RestTemplate对象,用于访问REST API
        RestTemplate restTemplate = new RestTemplate();

        //访问REST API
        ResponseEntity<SelectedAndroidDO[]> entity = restTemplate.getForEntity("http://localhost:5000/devices/all",SelectedAndroidDO[].class);
        SelectedAndroidDO[] alldata = entity.getBody();
        List<SelectedAndroidDO> result = Arrays.asList(alldata);

        return new PageResult<>(result);
    }

    @Override
    public String deviceAuth(SelectedAndroidDO authInfo){
        //申请一个RestTemplate对象,用于访问REST API
        RestTemplate restTemplate = new RestTemplate();

        //访问REST API
        ResponseEntity<SelectedAndroidDO> entity = restTemplate.postForEntity("http://localhost:5000/devices", authInfo, SelectedAndroidDO.class);
        SelectedAndroidDO result = entity.getBody();

        if (result.getLabel()==1)
            return "false";

        return "true";
    }


    public String deviceInterface(TransactionInfoDomain transactionInfoDO){
        SelectedAndroidDO selectedAndroidDO = new SelectedAndroidDO();

        selectedAndroidDO.setId(transactionInfoDO.getId());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> entity = restTemplate.postForEntity("http://localhost:8080/api/device/auth", selectedAndroidDO, String.class);
        String result = entity.getBody();

        return result;
    }
}
