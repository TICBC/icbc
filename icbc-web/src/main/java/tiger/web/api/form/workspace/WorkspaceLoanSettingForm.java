package tiger.web.api.form.workspace;

import tiger.core.basic.constants.SystemConstants;
import tiger.core.domain.workspace.WorkspaceDomain;
import tiger.web.api.form.BaseForm;
import tiger.web.api.form.FormInterface;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * Created by Kris Chan on 10:06 AM 3/21/16 .
 * All right reserved.
 */
public class WorkspaceLoanSettingForm extends BaseForm implements FormInterface {

    @Min(value = 1, message = "逾期天数需至少为1")
    @Max(value = 60, message = "逾期天数需小于或等于60")
    @NotNull(message = "逾期天数不能为空")
    private Integer overDueDay;

    @Min(value = 1, message = "坏账天数需至少为1")
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
    public WorkspaceDomain convert2Domain() {
        WorkspaceDomain workspaceDomain = new WorkspaceDomain();
        HashMap<String, String> extParams = new HashMap<>();
        extParams.put(SystemConstants.BAD_LOAN_DAY, badLoanDay.toString());
        extParams.put(SystemConstants.OVER_DUE_DAY, overDueDay.toString());
        workspaceDomain.setExtParams(extParams);
        return workspaceDomain;
    }
}

