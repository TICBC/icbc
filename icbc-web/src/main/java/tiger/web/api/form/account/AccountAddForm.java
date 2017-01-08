/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import tiger.common.util.BeanUtil;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountSignUpDomain;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;

/**
 * 用户注册需要填写的信息
 *
 * @author zhangbin
 * @version v0.1 2015/9/14 18:43
 */
public class AccountAddForm implements FormInterface {

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String mobile;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 确认密码
     */
    @NotNull(message = "确认密码不能为空")
    private String confirmPassword;

    /**
     * 用户名字
     */
    @NotNull(message = "姓名不能为空")
    private String userName;

    /**
     * 邀请码，可以不填
     */
    private String invitationCode;
    /**
     * 短信验证码
     */
    @NotNull(message = "短信验证码不能为空")
    private String msgCode;

    /**
     *默认头像码
     */
    private Long iconId;

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    @Override
    public AccountSignUpDomain convert2Domain() {
        if (!password.equals(confirmPassword)) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
        AccountSignUpDomain domain = new AccountSignUpDomain();
        BeanUtil.copyPropertiesWithIgnores(this, domain);

        return domain;
    }
}
