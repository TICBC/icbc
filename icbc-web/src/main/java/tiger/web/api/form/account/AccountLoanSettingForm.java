/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.api.form.account;

import tiger.core.basic.constants.SystemConstants;
import tiger.core.domain.account.AccountDomain;
import tiger.web.api.form.BaseForm;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * @author alfred_yuan
 * @version ${ID}: v 0.1 15:11 alfred_yuan Exp $
 */
public class AccountLoanSettingForm extends BaseForm implements FormInterface {

    @Min(value = 0, message = "逾期天数需至少为0")
    @Max(value = 60, message = "逾期天数需小于或等于60")
    @NotNull(message = "逾期天数不能为空")
    private Integer overDueDay;

    @Min(value = 0, message = "坏账天数需至少为0")
    @Max(value = 180, message = "坏账天数需小于或等于180")
    @NotNull(message = "坏账天数不能为空")
    private Integer badLoanDay;

    public Integer getOverDueDay() {
        return overDueDay;
    }

    public void setOverDueDay(Integer overDueDay) {
        this.overDueDay = overDueDay;
    }

    public Integer getBadLoanDay() {
        return badLoanDay;
    }

    public void setBadLoanDay(Integer badLoanDay) {
        this.badLoanDay = badLoanDay;
    }

    @Override
    public AccountDomain convert2Domain() {
        AccountDomain accountDomain = new AccountDomain();

        HashMap<String, String> extParams = new HashMap<>();
        extParams.put(SystemConstants.BAD_LOAN_DAY, badLoanDay.toString());
        extParams.put(SystemConstants.OVER_DUE_DAY, overDueDay.toString());

        accountDomain.setExtParams(extParams);
        return accountDomain;
    }
}
