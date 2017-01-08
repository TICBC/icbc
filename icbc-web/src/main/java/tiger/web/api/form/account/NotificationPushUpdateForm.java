/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.web.api.form.account;

import tiger.common.dal.enums.AccountSettingBizTypeEnum;
import tiger.common.dal.enums.AccountSettingTypeEnum;
import tiger.core.domain.account.AccountSettingDomain;
import tiger.web.api.form.BaseForm;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.NotNull;

/**
 * @author alfred_yuan
 * @version v 0.1 20:24 alfred_yuan Exp $
 */
public class NotificationPushUpdateForm extends BaseForm implements FormInterface {

    @NotNull(message = "请正确设置参数")
    private Boolean isPush;

    public Boolean getIsPush() {
        return isPush;
    }

    public void setIsPush(Boolean isPush) {
        this.isPush = isPush;
    }

    @Override
    public AccountSettingDomain convert2Domain() {
        return new AccountSettingDomain(null, null, AccountSettingBizTypeEnum.ACCOUNT,
                null, AccountSettingTypeEnum.NOTIFICATION_PUSH, isPush.toString());
    }

}
