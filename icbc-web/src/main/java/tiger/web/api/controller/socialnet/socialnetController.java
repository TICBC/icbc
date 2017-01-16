package tiger.web.api.controller.socialnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tiger.biz.socialnet.suport.socialnetManager;
import tiger.core.basic.BaseResult;
import tiger.core.basic.PageResult;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.core.domain.socialnet.socialnetDomain;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;
import tiger.web.api.form.materials.MaterialsUpdateForm;
import tiger.web.api.form.socialnet.socialnetForm;

import javax.validation.Valid;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping(APIConstants.BASE_API_URL + "/socialnet")
public class socialnetController extends BaseController {

    @Autowired
    socialnetManager sManager;

    /**
     * 获取所有的设备信息
     *
     * @return
     */

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<List<socialnetDomain>> getSome(@RequestBody @Valid socialnetForm sForm, BindingResult bindingResult) {

        socialnetDomain sDomain = sForm.convert2Domain();
        return sManager.getSome(sDomain);
    }

    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    @ResponseBody
    public String getPass(@RequestBody @Valid socialnetForm sForm, BindingResult bindingResult) {

        socialnetDomain sDomain = sForm.convert2Domain();

        PageResult<List<socialnetDomain>> resultDomain = sManager.getSome(sDomain);
        socialnetDomain ssDomain = resultDomain.getData().get(0);
        return ssDomain.getPass();
    }

    /**
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<List<socialnetDomain>> getSome() {

        socialnetDomain sDomain = new socialnetDomain();
        sDomain.user1 = "39";
        sDomain.user2 = "788";
        sDomain.time = "2016-05-01 12:31:15";
        sDomain.money = "4892.0";
        return sManager.getSome(sDomain);
    }
    */
}
