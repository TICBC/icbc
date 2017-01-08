/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import tiger.common.util.StringUtil;
import tiger.core.basic.enums.ErrorCodeEnum;
import tiger.core.basic.exception.TigerException;
import tiger.core.domain.account.AccountResetPwdDomain;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:31 PM yiliang.gyl Exp $
 */
public class SimpleResetPasswordForm implements FormInterface {

    @NotNull(message = "原密码不能为空")
    private String oldPassword;

    @NotNull(message = "密码不能为空")
    /**忘记密码后的，重置的密码*/
    private String password;

    @NotNull(message = "确认密码不能为空")
    /**确认密码*/
    private String confirmPassword;

    @Override
    public AccountResetPwdDomain convert2Domain() {
        AccountResetPwdDomain domain = new AccountResetPwdDomain();
        if (!StringUtil.equals(this.password, this.confirmPassword)) {
            throw new TigerException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }
        domain.setPassword(this.password);
        domain.setOldPassword(this.oldPassword);
        return domain;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
}
