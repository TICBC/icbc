/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import tiger.common.dal.enums.AccountSocialTypeEnum;

import javax.validation.constraints.NotNull;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 2:21 PM yiliang.gyl Exp $
 */
public class SocialAuthForm {

    @NotNull(message = "code参数不能为空")
    String code;

    @NotNull(message = "state参数不能为空")
    String state;

    String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public AccountSocialTypeEnum getSocialTypeFromState() {
        if (this.state.split("_").length > 0) {
            return AccountSocialTypeEnum.getEnumByCode(this.state.split("_")[0]);
        } else {
            return null;
        }
    }
}
