/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.account;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:56 PM yiliang.gyl Exp $
 */
public class AccountSocialAuthDomain {

    private Boolean canBindAccount = false;

    private AccountDomain account;

    private AccountBindDomain accountBind;

    public Boolean getCanBindAccount() {
        return canBindAccount;
    }

    public void setCanBindAccount(Boolean canBindAccount) {
        this.canBindAccount = canBindAccount;
    }

    public AccountDomain getAccount() {
        return account;
    }

    public void setAccount(AccountDomain account) {
        this.account = account;
    }

    public AccountBindDomain getAccountBind() {
        return accountBind;
    }

    public void setAccountBind(AccountBindDomain accountBind) {
        this.accountBind = accountBind;
    }
}
