package tiger.common.dal.persistence.account;

import tiger.common.dal.persistence.BaseDO;

public class AccountBindDO extends BaseDO {

    private Long accountId;

    private String accountType;

    private String accountName;

    private String accessToken;

    private String openId;

    private String iconUrl;

    private Long iconId;

    private String extParams;

    private Boolean hasBind;

    public Boolean getHasBind() {
        return hasBind;
    }

    public void setHasBind(Boolean hasBind) {
        this.hasBind = hasBind;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public String getExtParams() {
        return extParams;
    }

    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }
}