/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.controller.account;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tiger.biz.account.support.AccountManager;
import tiger.biz.system.support.SmsManager;
import tiger.biz.workspace.support.WorkspaceInvitationManager;
import tiger.biz.workspace.support.WorkspaceManager;
import tiger.common.dal.annotation.LoginRequired;
import tiger.common.dal.enums.SmsVerifyCodeTypeEnum;
import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.util.PhoneUtil;
import tiger.common.util.StringUtil;
import tiger.core.basic.BaseResult;
import tiger.core.basic.constants.SystemConstants;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountDomain;
import tiger.core.domain.account.AccountLoginLogDomain;
import tiger.core.domain.account.AccountResetPwdDomain;
import tiger.core.service.account.AccountService;
import tiger.core.service.account.LoginLogService;
import tiger.core.service.system.SmsService;
import tiger.core.service.system.SystemParamService;
import tiger.web.api.constants.APIConstants;
import tiger.web.api.controller.BaseController;
import tiger.web.api.form.account.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * 用户类API
 * 这是一个比较古老的类，重构完过后把下面的事项删除
 *
 * @author Domi.hp
 * @version v 0.1 2015年9月10日 下午7:23:26 HuPeng Exp $
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(APIConstants.BASE_API_URL + "/account")
public class AccountController extends BaseController {

    /**
     * 日志类
     */
    private static Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    private SmsManager smsManager;
    @Autowired
    private SmsService smsService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private SystemParamService systemParamService;

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private WorkspaceInvitationManager workspaceInvitationManager;

    @Autowired
    private WorkspaceManager workspaceManager;

    /**
     * 用户登录接口
     *
     * @param request  the request
     * @param response the response
     * @return the string
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<AccountDomain> login(@RequestHeader(APIConstants.HEADER_USERNAME) String mobile,
                                           @RequestHeader(APIConstants.HEADER_PASSWORD) String password,
                                           @RequestHeader(APIConstants.HEADER_USER_AGENT) String userAgent,
                                           @RequestParam(value = APIConstants.PARAM_EXPIRE_DAY, required = false, defaultValue = APIConstants.TOKEN_DEFAULT_EXPIRE_DAY) int expireDay,
                                           @RequestParam(value = "invitationCode", required = false) String invitationCode,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        // 检查参数
        if (StringUtil.isBlank(mobile) || StringUtil.isBlank(password)) {
            return new BaseResult<>(ErrorCodeEnum.PARAMETERS_IS_NULL);
        }

        // 获取用户信息
        AccountDomain account = accountManager.login(mobile, password);

        // 设置登陆信息
        AccountLoginLogDomain loginLogDomain = new AccountLoginLogDomain(
                account.getId(),
                userAgent,
                request.getRemoteAddr()
        );
        String token = accountManager.createToken(loginLogDomain, expireDay);

        // 提出加入团队申请
        if (StringUtil.isNotBlank(invitationCode) && account != null) {
            addToWorkspace(invitationCode, account.getId());
        }

        // 设置团队列表
        account.setWorkspaces(workspaceManager.getUserWorkspaces(account.getId()));

        if (logger.isInfoEnabled()) {
            logger.info("用户登录成功 [" + account.getId() + "]");
        }

        // 设置token
        this.setToken(response, token);

        //因为跨域的问题, 展示将token绑定到account的字段里面
        HashMap map = new HashMap<>();
        map.put("X-Auth-Token",token);
        account.setExtParams(map);
        return new BaseResult<>(account);
    }

    /**
     * 获取登陆用户的基本信息
     *
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    @LoginRequired
    public BaseResult<AccountDomain> getUserProfile() {
        AccountDomain accountDomain = this.currentAccount();

        accountDomain.setExtParams(accountService.getExtParamById(accountDomain.getId()));
        return new BaseResult<>(accountDomain);
    }

    /**
     * 修改自己个人信息
     *
     * @param form
     * @param bindResult
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> updateUserProfile(@RequestBody @Valid AccountUpdateForm form,
                                                 BindingResult bindResult) {
        AccountDomain domain = form.convert2Domain();
        domain.setId(this.currentAccount().getId());
        return new BaseResult<>(accountService.updateAccount(domain));
    }

    /**
     * 忘记密码获取短信验证码
     *
     * @return
     */
    @RequestMapping(value = {"/password/sms"}, method = RequestMethod.GET, params = "operation=reset")
    @ResponseBody
    public BaseResult<Boolean> getMsgCodeForResetPass(@RequestParam(APIConstants.PARAM_MOBILE) String mobile) {
        //如果号码未注册过或者不是正确的手机号码，不应通过
        if (!PhoneUtil.isValidMobile(mobile) || !accountService.isMobileExist(mobile)) {
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE, false);
        }

        checkSmsOption(mobile, SmsVerifyCodeTypeEnum.RESET_PASSWORD, SystemConstants.SMS_RESET_PASSWORD_OPTION,
                SystemConstants.SMS_RESET_PASSWORD_LIMIT, "您今日重置密码验证码短信额度已经用光,请明日再次尝试");

        //发送短信到指定的号码
        return new BaseResult<>(smsManager.sendVerifyCode(mobile, SmsVerifyCodeTypeEnum.RESET_PASSWORD.getCode(), null));
    }

    /**
     * 忘记密码重置密码 - 提交验证码
     *
     * @param form
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = {"/password/sms"}, method = RequestMethod.POST, params = "operation=reset")
    @ResponseBody
    public BaseResult<Boolean> verifyMsgCode(@RequestBody @Valid MobileAndCode form,
                                             BindingResult bindingResult) {
        checkSmsVerifyCode(form.getMsgCode(), form.getPhoneNum(), SmsVerifyCodeTypeEnum.RESET_PASSWORD);

        return new BaseResult<>(true);
    }

    /**
     * 忘记密码 - 短信验证码验证成功后提交新密码。
     *
     * @param form
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = {"/password"}, method = RequestMethod.POST, params = "operation=reset")
    @ResponseBody
    public BaseResult<Boolean> resetPassword(@RequestBody @Valid ResetPasswordForm form,
                                             BindingResult bindingResult) {
        checkSmsVerifyCode(form.getCode(), form.getMobile(), SmsVerifyCodeTypeEnum.RESET_PASSWORD);

        boolean isSuccess = accountService.resetPasswordByMobile(form.convert2Domain());
        if (isSuccess) {//如果修改密码成功，则把短信验证码设为失效
            if (!smsService.setExpired(form.getMobile(), SmsVerifyCodeTypeEnum.RESET_PASSWORD)) {
                logger.error("短信验证码设置失效失败,验证码 [" + form.getMobile() + "]");
            }
        }
        return new BaseResult<>(isSuccess);
    }

    /**
     * 用原始密码修改密码
     *
     * @return
     */
    @RequestMapping(value = {"/password"}, method = RequestMethod.PUT)
    @ResponseBody
    @LoginRequired
    public BaseResult<Boolean> changePassword(@RequestBody @Valid SimpleResetPasswordForm form,
                                              BindingResult bindingResult) {
        AccountResetPwdDomain resetPwdDomain = form.convert2Domain();
        resetPwdDomain.setAccountId(this.currentAccount().getId());
        return new BaseResult<>(accountService.resetPasswordByOldPassword(resetPwdDomain));
    }

    /**
     * 用户注册获取验证码
     *
     * @return the string
     */
    @RequestMapping(value = "/sms", method = RequestMethod.GET, params = "operation=register")
    @ResponseBody
    public BaseResult<Boolean> getMsgCodeForRegister(@RequestParam(APIConstants.PARAM_MOBILE) String phoneNum) {

        if (!PhoneUtil.isValidMobile(phoneNum) || accountService.isMobileExist(phoneNum)) {
            //如果号码注册过，不应通过
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER, false);
        }
        checkSmsOption(phoneNum, SmsVerifyCodeTypeEnum.REGISTER, SystemConstants.SMS_REGISTER_OPTION,
                SystemConstants.SMS_REGISTER_LIMIT, "您今日注册验证码短信额度已经用光,请明日再次尝试");
        Boolean isSend = smsManager.sendVerifyCode(phoneNum, SmsVerifyCodeTypeEnum.REGISTER.getCode(), null);
        return new BaseResult<>(isSend);
    }

    /**
     * 用户注册验证码验证
     *
     * @param form
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/sms", method = RequestMethod.POST, params = "operation=register")
    @ResponseBody
    public BaseResult<Boolean> authMsgCodeForRegister(@RequestBody @Valid MobileAndCode form,
                                                      BindingResult bindingResult) {

        checkSmsVerifyCode(form.getMsgCode(), form.getPhoneNum(), SmsVerifyCodeTypeEnum.REGISTER);

        return new BaseResult<>(true);
    }

    /**
     * 用户注册接口
     *
     * @return the string
     */
    @RequestMapping(value = "", method = RequestMethod.POST, params = "operation=register")
    @ResponseBody
    public BaseResult<?> signUp(@Valid @RequestBody AccountAddForm form,
                                BindingResult bindingResult) {
        // 验证图片验证码 TODO:这种能够开关的功能最好有一个配置值，配置能够搞成一个可用性检测的东西
//        if (!CaptchaUtil.validateImageChallengeForID(captchaToken,captcha)){
//            return new BaseResult<>(ErrorCodeEnum.BIZ_VERY_CODE);
//        }
        // 这一步检测手机号是否被注册，是为了防范攻击者。所以返回不用具体。
        if (accountService.isMobileExist(form.getMobile())) {
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }
        // 校验密码与验证密码
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }
        // 校验短信验证码
        checkSmsVerifyCode(form.getMsgCode(), form.getMobile(), SmsVerifyCodeTypeEnum.REGISTER);

        AccountDomain accountDomain = accountManager.signup(form.convert2Domain());
        if (accountDomain.getId() > 0) { //如果创建用户成功，则把短信验证码设为失效
            smsService.setExpired(form.getMobile(), SmsVerifyCodeTypeEnum.REGISTER);
        } else {
            return new BaseResult<>(false);
        }

        //加入团队申请
        if (StringUtil.isNotBlank(form.getInvitationCode())) {
            addToWorkspace(form.getInvitationCode(), accountDomain.getId());
        }
        return new BaseResult<>(accountDomain);
    }

    /**
     * 检查号码是否已被注册(注意此处不对手机号码的合法性做校验，因为没必要)。
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/mobile/{mobile}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Boolean> isMobileExist(@PathVariable String mobile) {
        boolean isExist = accountService.isMobileExist(mobile);
        return new BaseResult<>(isExist);
    }

    /**
     * 通过手机号获取用户
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/mobile/{mobile}", method = RequestMethod.GET, params = "scope=profile")
    @LoginRequired
    @ResponseBody
    public BaseResult<?> readByMobile(@PathVariable("mobile") String mobile) {
        if (!PhoneUtil.isValidMobile(mobile)) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER, "不合法的手机号");
        }
        AccountDomain account = accountService.readByMobile(mobile);
        if (account == null) {
            return new BaseResult<>(ErrorCodeEnum.NOT_FOUND.getCode(), "没有找到相应用户");
        }
        return new BaseResult<>(account);
    }

    /**
     * 修改用户绑定手机
     *
     * @param mobileChangeForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/mobile", method = RequestMethod.PUT)
    @LoginRequired
    @ResponseBody
    public BaseResult<Boolean> changeMobile(@RequestBody MobileChangeForm mobileChangeForm,
                                            BindingResult bindingResult) {
        //如果已经绑定了手机号，那么需要密码
        if (accountManager.hasBindMobile(currentAccount().getId())) {
            //1. 登录校验密码是否正确
            accountManager.login(currentAccount().getMobile(),
                    mobileChangeForm.getPassword());
        } else {
            if (!accountService.resetPasswordById(currentAccount().getId(), mobileChangeForm.getPassword())) {
                return new BaseResult<>(ErrorCodeEnum.BIZ_FAIL.getCode(), "设置密码错误");
            }
        }

        //2. 验证手机号和验证码参数
        if (!PhoneUtil.isValidMobile(mobileChangeForm.getPhoneNum())) {
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER.getCode(), "不正确的手机号格式");
        }
        if (accountService.isMobileExist(mobileChangeForm.getPhoneNum())) {
            return new BaseResult<>(ErrorCodeEnum.BIZ_DUPLICATIVE.getCode(), "手机号码被注册!");
        }

        checkSmsVerifyCode(mobileChangeForm.getMsgCode(), mobileChangeForm.getPhoneNum(), SmsVerifyCodeTypeEnum.RESET_MOBILE);

        //3.修改手机号
        if (accountService.updateAccountMobile(currentAccount().getId(), mobileChangeForm.getPhoneNum())) {
            return new BaseResult<>(true);
        } else {
            return new BaseResult<>(ErrorCodeEnum.BIZ_FAIL.getCode(), "系统错误,修改手机号失败");
        }
    }

    /**
     * 用户更换绑定手机号 获取验证码
     *
     * @return the string
     */
    @RequestMapping(value = "mobile/sms", method = RequestMethod.GET, params = "operation=resetMobile")
    @LoginRequired
    @ResponseBody
    public BaseResult<Boolean> getMsgCodeForResetMobile(@RequestParam(APIConstants.PARAM_MOBILE) String phoneNum) {

        //如果号码已被绑定,不能通过
        if (!PhoneUtil.isValidMobile(phoneNum) || accountService.isMobileExist(phoneNum)) {
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER, false);
        }
        checkSmsOption(phoneNum, SmsVerifyCodeTypeEnum.RESET_MOBILE, SystemConstants.SMS_RESET_MOBILE_OPTION,
                SystemConstants.SMS_RESET_MOBILE_LIMIT, "您今日更换绑定手机号验证码短信额度已经用光,请明日再次尝试");
        Boolean isSend = smsManager.sendVerifyCode(phoneNum, SmsVerifyCodeTypeEnum.RESET_MOBILE.getCode(), null);
        return new BaseResult<>(isSend);
    }

    /**
     * 用户登出接口
     *
     * @return the string
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.DELETE)
    @ResponseBody
    @LoginRequired
    public BaseResult<Object> logout(@RequestHeader(APIConstants.HEADER_TOKEN) String token) {
        boolean rc = accountService.deleteLoginToken(this.currentAccount().getId(), token);
        if (rc) {
            if (logger.isInfoEnabled()) {
                logger.info("用户 [" + this.currentAccount().getMobile() + "] 成功登出..");
            }
        } else {
            logger.error("用户 [" + this.currentAccount().getMobile() + "] 登出失败..");
        }
        return new BaseResult<>(rc);
    }

    /**
     * 检查token是否可用
     *
     * @param token
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Boolean> isValidToken(@RequestParam(APIConstants.PARAM_TOKEN) String token,
                                            @RequestParam(APIConstants.PARAM_MOBILE) String mobile) {
        AccountDomain accountDomain = accountService.readByMobile(mobile);
        long accountId = loginLogService.getAccountIdByToken(token);
        if (null == accountDomain || !accountDomain.getId().equals(accountId)) {
            return new BaseResult<>(false);
        }
        return new BaseResult<>(true);
    }


    /**
     * 通过用户id获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @LoginRequired
    @ResponseBody
    public BaseResult<AccountDomain> getAccountProfileById(@PathVariable Long id) {
        List<Long> ids = new ArrayList<>(SystemConstants.SIZE_ONE);
        ids.add(id);
        List<AccountDomain> accountDomains = accountManager.getAccountsByIds(ids);

        if (CollectionUtils.isEmpty(accountDomains)) {
            return new BaseResult<>(ErrorCodeEnum.NOT_FOUND);
        }
        return new BaseResult<>(accountDomains.get(SystemConstants.FIRST_INDEX));
    }


    //~ private methods

    /**
     * 检查Sms的系统限制条件
     *
     * @param type
     * @param optionSystemParam
     * @param limitSystemParam
     * @param message
     */
    private void checkSmsOption(String mobile, SmsVerifyCodeTypeEnum type, String optionSystemParam, String limitSystemParam, String message) {
        int smsCount = smsService.countVerifySmsesInOneDayByMobile(mobile, type, new Date());
        Map<String, String> smsOption = systemParamService.getParamsByType(SystemParamTypeEnum.ACCOUNT_SMS_OPTION);
        if (Boolean.valueOf(smsOption.get(optionSystemParam))) {
            if (smsCount >= Integer.valueOf(smsOption.get(limitSystemParam))) {
                throw new TigerException(ErrorCodeEnum.BIZ_REQUEST_EXCEEDED, message);
            }
        }
    }

    /**
     * Set the token.
     *
     * @param response the response
     * @param token    the token
     */
    private void setToken(HttpServletResponse response, String token) {
        response.setHeader(APIConstants.HEADER_TOKEN, token);
    }

    private Boolean addToWorkspace(String code, Long accountId) {
        try {
            return workspaceInvitationManager.applyJoinWorkSpace(code, accountId);
        } catch (Exception e) {
            logger.info("通过code [" + code + "] 加入客户 [" + accountId + "] 到团队失败");
            return false;
        }
    }

    /**
     * 检查短信验证码是否有效
     *
     * @param formCode
     * @param phoneNum
     * @param type
     */
    private void checkSmsVerifyCode(String formCode, String phoneNum, SmsVerifyCodeTypeEnum type) {
        String verifyCode = smsService.getMsgCodeFromDB(phoneNum, type);

        if (formCode == null || !formCode.equals(verifyCode)) {
            throw new TigerException(ErrorCodeEnum.BIZ_VERY_CODE, "无效的验证码");
        }

    }

}
