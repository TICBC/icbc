package tiger.web.api.controller.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tiger.biz.device.support.DeviceManager;
import tiger.core.basic.PageResult;
import tiger.core.domain.device.EquipmentInfoDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;

import java.util.List;

/**
 * Created by archer on 2017/1/10.
 */
@RestController
@ResponseBody
@RequestMapping(APIConstants.BASE_API_URL + "/device")
public class DeviceController extends BaseController {

    @Autowired
    DeviceManager deviceManager;

    /**
     * 获取所有的设备信息
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<List<EquipmentInfoDomain>> getAll() {

        return deviceManager.getAll();
    }
}
