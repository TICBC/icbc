package tiger.web.api.controller.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiger.biz.device.support.DeviceManager;
import tiger.common.dal.persistence.icbc.SelectedAndroidDO;
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
    public PageResult<List<SelectedAndroidDO>> getAll() {

        return deviceManager.getAll();
    }
    /**
     * 设备信任计算的入口
     *
     * @return
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public String deviceAuth(@RequestBody SelectedAndroidDO newdevice) {

        return deviceManager.deviceAuth(newdevice);
    }

    /**
     * 获取所有的设备信息(本地数据库)
     *
     * @return
     */
    @RequestMapping(value = "/allequ", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<List<EquipmentInfoDomain>> getAllEqu() {

        return deviceManager.getAllEqu();
    }
}
