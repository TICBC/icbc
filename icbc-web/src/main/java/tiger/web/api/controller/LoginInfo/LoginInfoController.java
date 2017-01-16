package tiger.web.api.controller.LoginInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tiger.biz.LoginInfo.support.LoginInfoManager;
import tiger.core.basic.PageResult;
import tiger.core.domain.LoginInfo.LoginInfoDomain;
import tiger.web.api.constants.APIConstants;

import java.util.List;

/**
 * Created by AUSA on 2017/1/15.
 */

@RestController
@ResponseBody
@RequestMapping(APIConstants.BASE_API_URL + "/logininfo")
public class LoginInfoController {
    @Autowired
    LoginInfoManager loginInfoManager;

    /**
     * 获取所有的登陆信息
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<List<LoginInfoDomain>> getAll() {

        return loginInfoManager.getAll();
    }
}
