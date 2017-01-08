/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import javax.validation.constraints.NotNull;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:14 PM yiliang.gyl Exp $
 */
public class MobileChangeForm {

    /**
     * 用户手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String phoneNum;

    /**
     * 短信验证码
     */
    @NotNull(message = "短信验证码不能为空")
    private String msgCode;

    /**
     * 用户密码
     */
    @NotNull(message="密码不能为空")
    private String password;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
