/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.controller.system;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.util.CaptchaUtil;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.BaseResult;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.service.system.SystemParamService;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;
import tiger.web.api.form.system.ExceptionForm;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统相关接口
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 4, 2015 4:37:29 PM yiliang.gyl Exp $
 */
@RequestMapping(value = APIConstants.BASE_API_URL +"/system")
@RestController
public class SystemController extends BaseController {

    private static Logger logger = Logger.getLogger(SystemController.class);

    @Autowired
    SystemParamService systemParamService;

    /**
     * 获取系统时间
     *
     * @return the system date time
     */
    @RequestMapping(value = "/dateTime", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Long> getSystemDateTime() {
        if (logger.isInfoEnabled()) {
            logger.info("获取系统时间");
        }
        return new BaseResult<>(System.currentTimeMillis());
    }


    /**
     * 获取系统验证码
     *
     * @param systemParamsType
     * @return
     */
    @RequestMapping(value = "/systemparams", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Map<String, String>> getSystemParams(@RequestParam("type") String systemParamsType) {
        boolean isPublic = false;
        // 检查所传类型是否为可公开访问的参数类型
        for (String type : APIConstants.PUBLIC_SYSTEM_PARAM_TYPES) {
            if (type.equals(systemParamsType)) {
                isPublic = true;
                break;
            }
        }
        if (!isPublic) {
            logger.error("试图获取不能公开的系统参数类型" + systemParamsType);
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
        return new BaseResult<>(systemParamService.getParamsByType(SystemParamTypeEnum.getEnumByCode(systemParamsType)));
    }

    /**
     * 获取图片验证码
     *
     * @return the captcha token and image
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<?> getCaptcha(HttpServletResponse response) {
        try {
            String captchaToken = StringUtil.createToken();
            byte[] image = new CaptchaUtil().getImageChallengeForID(captchaToken);
            response.addHeader(APIConstants.HEADER_CAPTCHA_TOKEN, captchaToken);
            Map result = new HashMap();
            result.put("image", image);
            result.put("prefix", "data:image/jpg;base64,");
            return new BaseResult<>(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResult<>(ErrorCodeEnum.UNKNOW_ERROR);
        }
    }

    /**
     * 验证图片验证码是否有效
     *
     * @param captchaToken
     * @param captcha
     * @return
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResult<Boolean> verifyCaptcha(@RequestHeader(APIConstants.HEADER_CAPTCHA_TOKEN) String captchaToken,
                                             @RequestHeader(APIConstants.HEADER_CAPTCHA) String captcha) {
        try {
            boolean result = new CaptchaUtil().validateImageChallengeForID(captchaToken, captcha);
            return new BaseResult<>(result);
        } catch (Exception e) {
            return new BaseResult<>(ErrorCodeEnum.BIZ_VERY_CODE);
        }
    }

    /**
     * 提交异常
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exception", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<?> collectException(@RequestBody @Valid ExceptionForm exceptionForm){
        return new BaseResult<>(JsonUtil.toJson(exceptionForm));
    }

}
