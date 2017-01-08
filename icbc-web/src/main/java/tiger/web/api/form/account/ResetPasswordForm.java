/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import tiger.core.domain.account.AccountResetPwdDomain;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;

/**
 * @author zhangbin
 * @version v0.1 2015/9/20 9:49
 */
public class ResetPasswordForm implements FormInterface {

    @NotNull
    /**手机号码*/
    private String mobile;

    @NotNull
    /**短信验证码*/
    private String code;

    @NotNull(message = "密码不能为空")
    /**忘记密码后的，重置的密码*/
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public AccountResetPwdDomain convert2Domain() {

        AccountResetPwdDomain account = new AccountResetPwdDomain();
        account.setMobile(this.mobile);
        account.setPassword(this.password);

        return account;
    }
}
