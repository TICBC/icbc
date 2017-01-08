package tiger.common.dal.persistence.account;

import tiger.common.dal.persistence.BaseDO;

public class AccountParamsDO extends BaseDO {

    /**  */
    private static final long serialVersionUID = 7835124237038038720L;

    private Long accountId;

    private String paramName;

    private String paramValue;

    private Byte isActive;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}